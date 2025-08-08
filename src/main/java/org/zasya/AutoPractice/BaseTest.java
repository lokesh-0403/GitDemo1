package org.zasya.AutoPractice;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import pageobjects.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver initializeDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fls = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resource\\GlobalData.properties");
		prop.load(fls);
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") :prop.getProperty("browser");
//		 prop.getProperty("browser");
		if(browserName.equalsIgnoreCase(browserName)) {
			driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		return driver;
		
	}
	
	public List<HashMap<String,String>> getJsonDataToMap(String filePath) 
			throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(filePath),
		StandardCharsets.UTF_8);
		 
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String,String>>>(){});
		return data;
	
	}	
	
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source =  ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"\\src\\main\\java\\reports\\"+testCaseName+"\\.png");
		
		FileUtils.copyFile(source,file);
		return System.getProperty("user.dir")+"\\src\\main\\java\\reports\\"+testCaseName+"\\.png";
	}
	@BeforeMethod(alwaysRun =true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	@AfterMethod(alwaysRun=true)
	public void close() {
		driver.close();
	}
	
}
