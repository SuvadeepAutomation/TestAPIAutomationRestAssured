package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.api.gorest.pojo.User;
import com.qa.api.gorest.restclient.RestClient;
import com.qa.api.gorest.util.ExcelUtils;

import io.restassured.response.Response;

public class CreateUserTest {

	static String baseURI = "https://gorest.co.in";
	static String basePath = "/public/v2/users";
	static String token = "38f36092782bd9ad2357f9c077ddc7be0dd264dcd02bcedf3b048229bf4de7cf";

	@DataProvider
	public Object[][] getUserData() {
		Object userdata[][] = ExcelUtils.getTestData("Sheet1");
		return userdata;

	}

	@Test(dataProvider = "getUserData")
	public void createAPIPOSTTest(String name, String email, String gender, String status) {
		Map<String, String> authTokenMap = new HashMap<String, String>();
		authTokenMap.put("Authorization", "Bearer " + token);

		User usr = new User(name, email, gender, status);

		Response response = RestClient.doPost("JSON", baseURI, basePath, authTokenMap, null, true, usr);

		System.out.println(response.statusCode());
		System.out.println(response.asPrettyString());
		System.out.println("***********************************");
	}

}
