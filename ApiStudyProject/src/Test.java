import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class Test {
	public static final String clientId = "up3RXDdjUQTgomqf4AV0";
	public static final String clientSecret = "ygo1H9S3pF";

	public static void main(String[] args) throws IOException {
		URL url = null;
		url = new URL("https://openapi.naver.com/v1/search/webkr.xml?query=" + URLEncoder.encode("벌레의 소소한 하루", "UTF-8"));
		URLConnection urlConn = url.openConnection();
		urlConn.setRequestProperty("X-Naver-Client-Id", clientId);
		urlConn.setRequestProperty("X-Naver-Client-Secret", clientSecret);

		BufferedReader reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
		String msg = null;
		while ((msg = reader.readLine()) != null)
			System.out.println(msg);
	}
}