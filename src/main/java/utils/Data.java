package utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.zasya.AutoPractice.BaseTest;

public class Data{

	@DataProvider
	public  Object[][] getData() {
		return new Object[][] {
			{"lokithor@gmail.com","Mmpl@2025","ADIDAS ORIGINAL","India"},
			//{"hidingthemail@gmail.com","Hello@03","ZARA COAT 3","United"}
			};
	}
	
	@DataProvider
	public Object[][] getData2(){
		HashMap<String, String > map = new HashMap<String,String>();
		map.put("email","lokithor@gmail.com");
		map.put("password","Mmpl@2025");
		map.put("productName", "ADIDAS ORIGINAL");
		map.put("countryName", "India");
		
		HashMap<String, String > map1 = new HashMap<String,String>();
		map1.put("email","hidingthemail@gmail.com");
		map1.put("password","Hello@03");
		map1.put("productName", "ZARA COAT 3");
		map1.put("countryName", "United");
		
		return new Object[][] {
			{map},{map1}
		};
	}		

	
}
