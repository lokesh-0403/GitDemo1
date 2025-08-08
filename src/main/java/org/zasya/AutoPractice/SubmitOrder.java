package org.zasya.AutoPractice;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.Test;

import Abstractpage.OrderPage;
import pageobjects.CartPage;
import pageobjects.CheckoutPage;
import pageobjects.ConfirmationPage;
import pageobjects.LandingPage;
import pageobjects.ProductCatalogue;
import utils.Data;
import utils.JsonData;

public class SubmitOrder extends BaseTest {
	
	
	//@Test(groups= {"ErrorHandling"})
	public void ProductErrorValidation() throws InterruptedException {
	
   
        
        landingPage.loginapplication("lokithor@gmail.com", "Mmpl@2003");
        
        
        
        Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}
	
	//@Test(dataProvider="getData", dataProviderClass = Data.class )
	    public void submitOrder(String name, String pass, String productName,String countryName)
	    		throws InterruptedException, IOException
	
	    		
	    { 
	        landingPage.loginapplication(name,pass);
	        
	        ProductCatalogue pr = new ProductCatalogue(driver);
	       
	        List<WebElement> products = pr.getProductList();
	        System.out.println(products);
	        pr.addProductToCart(productName);
	    
	        CartPage cartPage = pr.goToCartPage();
	        
	      
	        Boolean match = cartPage.VerifyProductDisplay(productName);
	        Assert.assertTrue(match);
	        
	        CheckoutPage checkoutpage = cartPage.goToCheckOut();
	        
	        checkoutpage.selectCountry(countryName);
	        ConfirmationPage confimPage = checkoutpage.SumbitOrder();
	  
	        confimPage.verifyMessage();
	        String line = "Thankyou for the order.";
	        String text = confimPage.verifyMessage();
	        Assert.assertEquals(text, line.toUpperCase());
	        Thread.sleep(3000);
	              
	    
	    }
	
	@Test(dataProvider="jsonData", dataProviderClass = JsonData.class )
	public void submitOrder(HashMap<String,String> input)
    		throws InterruptedException, IOException
	    		
	    { System.out.println(">>>>>json Email :: "+input.get("email"));
	    System.out.println(">>>>>json pasword :: "+input.get("password"));
	        landingPage.loginapplication(input.get("email"), input.get("password"));
	        
	        ProductCatalogue pr = new ProductCatalogue(driver);
	       
	        List<WebElement> products = pr.getProductList();
	        System.out.println(products);
	        pr.addProductToCart(input.get("productName"));
	    
	        CartPage cartPage = pr.goToCartPage();
	        
	      
	        Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 3");
	        Assert.assertTrue(match);
	        
	        CheckoutPage checkoutpage = cartPage.goToCheckOut();
	        
	        checkoutpage.selectCountry(input.get("countryName"));
	        ConfirmationPage confimPage = checkoutpage.SumbitOrder();
	  
	        confimPage.verifyMessage();
	        String line = "Thankyou for the order.";
	        String text = confimPage.verifyMessage();
	        Assert.assertEquals(text, line.toUpperCase());
	        Thread.sleep(3000);
	              
	    
	    }
	
	public void getScreenShot(String testCaseName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source =  ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"\\src\\main\\java\\reports\\"+testCaseName+"\\.png");
		
		FileUtils.copyFile(source,file);
	}
	
	//@Test(dependsOnMethods= {"submitOrder"}, dataProvider = "getData2",dataProviderClass = Data.class)
	public void OrderHistoryTest(HashMap<String,String> input) throws InterruptedException {
		
		LandingPage landingPage = new LandingPage(driver);
		landingPage.loginapplication(input.get("email"),input.get("password"));
		
		 ProductCatalogue pr = new ProductCatalogue(driver);
		 OrderPage orderPage = pr.goToOrdersPage();
		 Thread.sleep(1000);
		 Assert.assertTrue(orderPage.VerifyOrderDisplay(input.get("productName")));
		 Thread.sleep(3000);
	}
}



