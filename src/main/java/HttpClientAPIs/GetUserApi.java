package HttpClientAPIs;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.sun.tools.javac.parser.ReferenceParser.ParseException;

import junit.framework.Assert;

public class GetUserApi {

	@Test
	public void getUserTest() {
		CloseableHttpResponse response = null;

		CloseableHttpClient httpclient = HttpClientBuilder.create().build();

		// 1.Create a get request with url
		HttpGet getRequest = new HttpGet("https://gorest.co.in/public-api/users");

		// 2. add header
		getRequest.addHeader("Authorization",
				"Bearer _38f36092782bd9ad2357f9c077ddc7be0dd264dcd02bcedf3b048229bf4de7cf");
		try {
			response = httpclient.execute(getRequest);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 3. get the status code

		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, 200);

		// 4. get response body
		HttpEntity httpEntity = response.getEntity();
		String responseBody = null;
		try {
			responseBody = EntityUtils.toString(httpEntity);
			System.out.println(responseBody);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// get Json value using rest assured JsonPath
//		
//		  JsonPath js=new JsonPath(responseBody);
//		  System.out.println(js.getString("_meta.success"));

		// get json value from jayway rest assured api

		Object document = Configuration.defaultConfiguration().jsonProvider().parse(responseBody);
		List<Integer> result = JsonPath.read(document, "$..total");
		System.out.println(result.get(0));
		Assert.assertTrue(true);

	}

}
