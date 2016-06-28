package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

@Component
public class SearchService {
	public static final String clientId = "자신의 Client ID";
	public static final String clientSecret = "자신의 Client Secret";

	/* 네이버 검색 API로 가능한 검색 분류 */
	public static final String[] types = { "blog", "news", "book", "adult", "encyc", "movie", "cafearticle", "kin",
			"local", "errata", "webkr", "image", "shop", "doc" };
	/* 검색 분류 인덱스 */
	public static final int[] typeIndex = { 101, 102, 103, 105, 106, 107, 108, 109, 111, 112, 113, 114 };
	/* 한 번에 검색하는 건 수 */
	public static final int display = 10;

	public List<HashMap<String, Object>> parsingXml(int searchType, int page, String keyword) {
		/* 검색 페이지와 관련된 정보를 담을 HashMap */
		/* 검색 결과 외 view에서 사용할 정보들을 담기 위해 준비 */
		HashMap<String, Object> pageInfo = null;

		/* 검색 결과가 담길 HashMap */
		List<HashMap<String, Object>> itemList = new ArrayList<HashMap<String, Object>>();
		try {
			/* Xml 태그를 분석하기 위한 도구 */
			/* Parser를 얻어오는 메소드를 따로 정의 */
			XmlPullParser parser = getXmlParser(searchType, page, keyword);
			
			/* 	Xml 문서의 시작(START_DOCUMENT), 종료(END_DOCUMENT), */
			/*	태그 시작(START_TAG), 태그의 끝(END_TAG) 등을 명시 */
			int eventType = parser.getEventType();
			
			/* 어떤 태그인지 담을 객체 */
			String tag = null;
			
			/* 하나의 검색 결과 정보를 담을 객체, key=태그 이름, value=태그에 저장된 값 */
			HashMap<String, Object> item = null;

			/* 문서가 종료될 때까지 반복 */
			while (eventType != XmlPullParser.END_DOCUMENT) {
				/* 태그의 종류를 알아내고 */
				tag = parser.getName();
				if (eventType == XmlPullParser.START_TAG) {
					/* 검색 결과 수를 확인하여 검색 페이지에서 필요한 정보를 계산 */
					if (tag.equals("total") && (pageInfo == null || pageInfo.isEmpty())) {
						pageInfo = new HashMap<String, Object>();

						int total = Integer.parseInt(parser.nextText());
						pageInfo.put("total", total);
						pageInfo.put("totalPage", total / display + 1);
						
						int endPage = page + 5 > 10 ? page + 5 : 10;
						int lastPage = (total + 1) / display < 100 ? (total + 1) / display : 100;
						pageInfo.put("currentPage", page);
						pageInfo.put("startPage", page - 4 > 1 ? page - 4 : 1);
						pageInfo.put("endPage", endPage < lastPage ? endPage : lastPage);
						pageInfo.put("lastPage", lastPage);					

						pageInfo.put("searchType", searchType);
						pageInfo.put("keyword", keyword);
						pageInfo.put("display", display);
						
						itemList.add(0, pageInfo);
					}
					/* 하나의 검색 결과를 담을 HashMap 생성 */
					else if (tag.equals("item")) {
						item = new HashMap<String, Object>();
					}
					/* item 태그 이하의 정보를 HashMap에 저장 */
					else if (item != null) {
						item.put(tag, parser.nextText());
					}
				}
				/* item 태그가 끝나면 itemList에 item 등록하고 item 객체 초기화 */
				if (eventType == XmlPullParser.END_TAG && tag.equals("item")) {
					itemList.add(item);
					item = null;
				}
				/* 다음 태그 확인 */
				eventType = parser.next();
			}
			return itemList;
		}
		/* 에러 발생시 Null 반환 */
		catch (IOException | XmlPullParserException e) {
			return null;
		}
	}

	/* 네이버 검색 API에서 Xml parser를 얻어오는 메소드 */
	public XmlPullParser getXmlParser(int searchType, int page, String keyword)
			throws IOException, XmlPullParserException {
		URL url;
		url = new URL("https://openapi.naver.com/v1/search/" + types[searchType - 101] + ".xml?query="
				+ URLEncoder.encode(keyword, "UTF-8") + "&display=" + display + "&start=" + ((page - 1) * display + 1));
		URLConnection urlConn = url.openConnection();
		urlConn.setRequestProperty("X-Naver-Client-Id", clientId);
		urlConn.setRequestProperty("X-Naver-Client-Secret", clientSecret);

		XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
		parser.setInput(new BufferedReader(new InputStreamReader(urlConn.getInputStream())));

		return parser;
	}
}