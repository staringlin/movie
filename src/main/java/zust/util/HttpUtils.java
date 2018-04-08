package zust.util;


import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpResponse;





public class HttpUtils {
	
	   public static HttpResponse getRawHtml(String url) throws ClientProtocolException, IOException {
		   HttpClient client = new DefaultHttpClient();
	        //获取响应文件，即html，采用get方法获取响应数据
	        HttpGet httpGet = new HttpGet(url);
	        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36"); 
	        HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1,
	                HttpStatus.SC_OK, "OK");
	        try {
	            //执行get方法
	            response = client.execute(httpGet);
	        } catch (IOException e) {
	            e.printStackTrace();

	        } finally {
	            // getMethod.abort();
	        }
	        return response;
	    }
	   


}
