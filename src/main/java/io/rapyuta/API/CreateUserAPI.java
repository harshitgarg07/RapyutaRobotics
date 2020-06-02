package io.rapyuta.API;

import java.util.HashMap;

import com.google.gson.Gson;

import io.rapyuta.POJO.User;
import io.rapyuta.POJO.UserResponse;
import io.restassured.response.Response;
import rapyuta.rapyuta.RequestBuilder;

public class CreateUserAPI {

	String baseURI = "https://coding-question.ep-r.io";
	String endPoint = "/user";
	User userpojo = new User();
	HashMap<String, String> headersMap = new HashMap<String, String>();

	public Response createUser(HashMap<String, String> map) {

		RequestBuilder reqbuilder = new RequestBuilder();
		headersMap.put("Authorization", "Bearer " + map.get("token"));

		if (null != map.get("username"))
			userpojo.setUsername(map.get("username"));
		if (null != map.get("email"))
			userpojo.setEmail(map.get("email"));

		Gson gson = new Gson();
		String json = gson.toJson(userpojo, User.class);
		System.out.println(json);
		Response response = reqbuilder.setEndPoint(baseURI + endPoint).setHeaders(headersMap).setRequestBody(json)
				.executePost();
		System.out.println("CreateUserAPI");
		System.out.println(response.prettyPrint());
		return response;
	}
	
}
