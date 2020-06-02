package io.rapyuta.utils;

import java.util.Random;

import io.rapyuta.POJO.UserResponse;
import io.restassured.response.Response;

public class TestUtils {
	
	public static String generateRandomStringCaps(int length) {
		String alphabet = new String("abcdefghijklmnopqrstuvwxyz");
		int n = alphabet.length();
		String result = new String();
		Random r = new Random();
		for (int i = 0; i < length; i++)
			// 12
			result = result + alphabet.charAt(r.nextInt(n));
		return result;
	}
	
	public static String generateEmail() {
		return generateRandomStringCaps(8)+"@"+generateRandomStringCaps(5)+".com";
	}
	
	public static String generateUserName() {
		return generateRandomStringCaps(8);
	}
	
	public static UserResponse convertResponsetoObj(Response response) {
		UserResponse userResponse = response.getBody().as(UserResponse.class);
		return userResponse;
	}
}
