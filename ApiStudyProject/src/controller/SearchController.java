package controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import service.SearchService;

@Controller
public class SearchController {
	@Autowired
	private SearchService service;

	@RequestMapping("search.do")
	public ModelAndView searching(@RequestParam(required = false) Map<String, Object> keywords) {
		/* 화면에 표시할 자료(Model)와 페이지 정보(View)를 담을 객체 */
		ModelAndView mav = new ModelAndView("searchPage");

		try {
			/* 검색 분류 */
			Integer type = null;
			type = Integer.parseInt((String) keywords.get("searchType"));
			/* 검색 키워드 */
			String keyword = (String) keywords.get("keyword");

			/* 정보가 제대로 입력되어 있지 않을 경우, 메소드 중단 */
			if (type == null || type / 100 != 1)
				return mav;
			if (keyword == null || keyword.isEmpty())
				return mav;
			
			/* 네이버 API와 서비스 이용시 필요한 정보를 사용하여 검색 */
			/* 검색 결과는 ModelAndView 객체에 담음 */
			mav.addObject("itemList", service.parsingXml(type, toInt(keywords.get("page")), keyword));
			return mav;
		}
		/* 에러 발생시, 기존 페이지로 이동 */
		catch (Exception e) {
			return mav;
		}
	}

	public Integer toInt(Object str) {
		if (str == null)
			return 1;

		Integer a = Integer.parseInt((String) str);
		return a;
	}
}