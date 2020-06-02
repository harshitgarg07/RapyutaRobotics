package user;

import java.util.HashMap;

import org.testng.annotations.DataProvider;

import io.rapyuta.utils.TestUtils;



public class UserDataProvider {
	static HashMap<String,String> map;
	
	
	
	@DataProvider(name = "randomValidValues")
    public static Object[][] RandomValidValues() {
		map = new HashMap<String, String>();
		map.put("username", TestUtils.generateUserName());
		map.put("email", TestUtils.generateEmail());
		
        return new Object[][] { 
        	{map},
        };
    }
	
	@DataProvider(name = "randomInvalidValues")
    public static Object[][] RandomInvalidValues() {
		map = new HashMap<String, String>();
		map.put("email", TestUtils.generateEmail());
		
        return new Object[][] { 
        	{map},
        };
    }
}

