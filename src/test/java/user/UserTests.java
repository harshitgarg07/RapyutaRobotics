package user;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.rapyuta.API.CreateUserAPI;
import io.rapyuta.API.GetUserAPIByUsername;
import io.rapyuta.POJO.UserResponse;
import io.rapyuta.utils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserTests {
	String endpoint = "https://coding-question.ep-r.io";
	String token;
	Response validResponse;
	RequestSpecification reqspec = RestAssured.given();
	CreateUserAPI createUser = new CreateUserAPI();
	GetUserAPIByUsername getUser = new GetUserAPIByUsername();

	@BeforeTest
	public void getToken() {
		String endPointGetToken = "/get_token";
		Response response = reqspec.request(Method.GET, endpoint + endPointGetToken);
		Assert.assertEquals(response.getStatusCode(), 200);
		token = response.jsonPath().get("token");
	}

	@Test(dataProvider = "randomValidValues", dataProviderClass = UserDataProvider.class)
	public void addValidUser(HashMap<String, String> map) {
		map.put("token", token);
		Response response = createUser.createUser(map);
		UserResponse res = TestUtils.convertResponsetoObj(response);
		Assert.assertEquals(response.getStatusCode(), 201);
		Assert.assertEquals(res.getEmail(),map.get("email"));
		Assert.assertEquals(res.getName(),map.get("username"));
	}
	
	@Test(dataProvider = "randomInvalidValues", dataProviderClass = UserDataProvider.class)
	public void addInValidUser(HashMap<String, String> map) {
		map.put("token", token);
		Response response = createUser.createUser(map);
		Assert.assertEquals(response.getStatusCode(), 400);
	}
	
	@Test(dataProvider = "randomValidValues", dataProviderClass = UserDataProvider.class)
	public void validateTokenisAuthenticated(HashMap<String, String> map) {
		map.put("token", "somedummyvalue");
		Response response = createUser.createUser(map);
		Assert.assertEquals(response.getStatusCode(), 401);
	}
		
	@Test(dataProvider = "randomValidValues", dataProviderClass = UserDataProvider.class)
	public void validateConflictsinAddUser(HashMap<String, String> map) {
		map.put("token", token);
		createUser.createUser(map);
		Response response = createUser.createUser(map);

		Assert.assertEquals(response.getStatusCode(), 409);
	}
	
	@Test(dataProvider = "randomValidValues", dataProviderClass = UserDataProvider.class)
	public void validateUserDataIsCorrect(HashMap<String, String> map) {
		map.put("token", token);
		Response response1 = createUser.createUser(map);
		Response response2 = getUser.getUserDetails(map);
		Assert.assertEquals(response2.getStatusCode(), 200);
		Assert.assertTrue(TestUtils.convertResponsetoObj(response1).equals(TestUtils.convertResponsetoObj(response2)));
	}
	

}
