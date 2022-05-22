package HttpClientAPIs;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.qa.api.gorest.pojo.User;

public class POSTUserAPI {

	@Test
	public void createUserAPI() {
		CloseableHttpResponse response = null;

		CloseableHttpClient httpclient = HttpClientBuilder.create().build();

		// 1. create a post request with URL

		HttpPost postRequest = new HttpPost("https://gorest.co.in/public-api/users");

		// 2. Add header

		postRequest.addHeader("Authorization",
				"Bearer _38f36092782bd9ad2357f9c077ddc7be0dd264dcd02bcedf3b048229bf4de7cf");
		postRequest.setHeader("Content-Type", "application/json");
		postRequest.addHeader("accept", "application/json");

		// 3. add json body

		// convert pojo to json string using jackson api

		User usr = new User("rocky", "handsome", "male", "active");

		ObjectMapper mapper = new ObjectMapper();
		String JsonString = null;
		try {
			JsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(usr);
			System.out.println("JSON body Payload===> " + JsonString);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println(JsonString);
		StringEntity userEntity = null;

		// 4. add json body to the request
		try {
			userEntity = new StringEntity(JsonString);

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		postRequest.setEntity(userEntity);

		// get response body

		// 5. Hit the API

		try {
			response = httpclient.execute(postRequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(response.getStatusLine().getStatusCode());
		Object document = Configuration.defaultConfiguration().jsonProvider().parse(JsonString);
		List<Boolean> result = JsonPath.read(document, "$..success");

		System.out.println(result.get(0));

	}

}
