package utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.zasya.AutoPractice.BaseTest;

public class JsonData extends BaseTest{

	@DataProvider(name ="jsonData")
	public Object[][] getData3() throws IOException {
		List<HashMap<String,String>> data = getJsonDataToMap(
				System.getProperty("user.dir")+"\\src\\main\\java\\resource\\PurchaseOrder.json");
	
		   Object[][] testData = new Object[data.size()][1];
		    for (int i = 0; i < data.size(); i++) {
		        testData[i][0] = data.get(i);
		    }
		return  testData;
	}
}
