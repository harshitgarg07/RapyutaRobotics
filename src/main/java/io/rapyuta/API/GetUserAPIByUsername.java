package io.rapyuta.API;

import java.util.HashMap;

import io.restassured.response.Response;
import rapyuta.rapyuta.RequestBuilder;

public class GetUserAPIByUsername {
	

	 String baseURI = "https://coding-question.ep-r.io";
	 String endPoint = "/user/";
	 
	HashMap<String, String> headersMap = new HashMap<String, String>();
	
	public  Response getUserDetails(HashMap<String, String> map){
		
		RequestBuilder reqbuilder = new RequestBuilder();
		headersMap.put("Authorization","Bearer "+map.get("token"));
		
		Response response = reqbuilder
		.setEndPoint(baseURI+endPoint+map.get("username"))
		.setHeaders(headersMap).executeGet();
		
		System.out.println("GetUserAPIByUsername");
		System.out.println(response.prettyPrint());
		
		return response;
	}
	

}
