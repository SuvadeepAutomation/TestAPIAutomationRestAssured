package com.qa.api.gorest.pojo;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.qa.api.gorest.restclient.RestClient;
import com.qa.api.gorest.util.TestUtils;

import io.restassured.response.Response;

public class UserResults {

	@Test
	public void createUserwithFullJson() {

		Self sf = new Self("http://sf.com");
		Edit ed = new Edit("http://ed.com");
		Avatar av = new Avatar("http://av.com");

		String token = "38f36092782bd9ad2357f9c077ddc7be0dd264dcd02bcedf3b048229bf4de7cf";

		Links ln = new Links(sf, ed, av);

		UserInfo uf = new UserInfo("Tom", "Peter", "male", "09-09-1991", "tom1@gmail.com", "90988988",
				"http://www.tom.com", "test-address", "active", ln);

		String usrJSONPayload = TestUtils.getSerialisedJSON(uf);
		System.out.println(usrJSONPayload);

		Map<String, String> authTokenMap = new HashMap<String, String>();
		authTokenMap.put("Authorization", "Bearer" + token);
		Response response = RestClient.doPost("JSON", "http://gorest.co.in", "/public-api/users", authTokenMap, null,
				true, usrJSONPayload);

		System.out.println(response.prettyPrint());
		System.out.println(response.statusCode());

	}

}
