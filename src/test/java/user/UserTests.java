package user;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import rapyuta.rapyuta.UserPOJO;

public class UserTests {
	String endpoint = "https://coding-question.ep-r.io";
	String token;
	Response validResponse;
//	RestAssured.get(endpoint + "/get_token");
	RequestSpecification reqspec = RestAssured.given();


	@BeforeTest
	 public void getToken() {
	String endPointGetToken = "/get_token";
	 Response response = reqspec.request(Method.GET,endpoint+ endPointGetToken);
	 Assert.assertEquals(response.getStatusCode(),"200");
	 token = response.jsonPath().get("token");
	 System.out.println(token);
}
	
	@Test
	public void addValidUser(HashMap<Object,Object> map) {
		String endPointAdduser = "/user";
		JsonObject req = new JsonObject();
	    UserPOJO u = new UserPOJO("hitesh","hitesh.28jan@gmail.com");
		
		ObjectMapper obj = new ObjectMapper();
		
		reqspec.header("Content-Type", "application/json");
		reqspec.header("Authorization", "Bearer "+ token);
		 
		
		//reqspec.body(obj.writeValueAsString(value));
		 
		// Post the request and check the response
		Response response = reqspec.post("endpoint+endPointAdduser");
		validResponse = response;
		
		Assert.assertEquals(response.getStatusCode(),"201");
	}
	
	@Test
	public void validateConflictsinAddUser(HashMap<Object,Object> map) {
		String endPointAdduser = "/user";
		JsonObject req = new JsonObject();
	    UserPOJO u = new UserPOJO("hitesh","hitesh.28jan@gmail.com");
		
		ObjectMapper obj = new ObjectMapper();
		
		reqspec.header("Content-Type", "application/json");
		reqspec.header("Authorization", "Bearer "+ token);
		 
		
		//reqspec.body(obj.writeValueAsString(value));
		 
		// Post the request and check the response
		reqspec.post("endpoint+endPointAdduser");
		Response response = reqspec.post("endpoint+endPointAdduser");
		
		Assert.assertEquals(response.getStatusCode(),"409");
	}
	
	
	@Test
	public void addUserWithInvalidRequest(HashMap<Object,Object> map) {
		String endPointAdduser = "/user";
		JsonObject req = new JsonObject();
	    UserPOJO u = new UserPOJO("hitesh","hitesh.28jan@gmail.com");
		
		ObjectMapper obj = new ObjectMapper();
	    
		reqspec.header("Content-Type", "application/json");
		reqspec.header("Authorization", "Bearer "+ token);
		
		//reqspec.body(obj.writeValueAsString(value));
		 
		// Post the request and check the response
		Response response1 = reqspec.post("endpoint+endPointAdduser");
		
		Assert.assertEquals(response.getStatusCode(),"400");
	}
	
	@Test
	public void addUserWithUnauthorizedRequest(HashMap<Object,Object> map) {
		String endPointAdduser = "/user";
		JsonObject req = new JsonObject();
	    UserPOJO u = new UserPOJO("hitesh","hitesh.28jan@gmail.com");
		
		ObjectMapper obj = new ObjectMapper();
	    
		reqspec.header("Content-Type", "application/json");
		reqspec.header("Authorization", "Bearer "+ "somedummyvalue");
		
		//reqspec.body(obj.writeValueAsString(value));
		 
		// Post the request and check the response
		Response response = reqspec.post("endpoint+endPointAdduser");
		
		Assert.assertEquals(response.getStatusCode(),"401");
	}
	
	@Test
	public void validateUser(HashMap<Object,Object> map) {
		String endPointAdduser = "/user";
		JsonObject req = new JsonObject();
	    UserPOJO u = new UserPOJO("hitesh","hitesh.28jan@gmail.com");
		
		ObjectMapper obj = new ObjectMapper();
	    
		 Response response2 = reqspec.request(Method.GET,endpoint+ endPointAdduser+"/"+map.get("UserName"));
		
		Assert.assertEquals(response2.getStatusCode(),"200");
		
		Assert.assertTrue(validResponse == response2);
		
	}
	
	
}
	
	

