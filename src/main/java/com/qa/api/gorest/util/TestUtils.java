package com.qa.api.gorest.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtils {

	/**
	 * 
	 * This method is used to convert POJO object to JSON String
	 * 
	 * @param obj
	 * @return JsonString
	 */

	public static String getSerialisedJSON(Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		String JsonString = null;
		try {
			JsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
			System.out.println("JSON body Payload===> " + JsonString);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return JsonString;
	}

}
