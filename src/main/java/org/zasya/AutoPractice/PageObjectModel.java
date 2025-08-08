package org.zasya.AutoPractice;



import org.openqa.selenium.chrome.ChromeDriver;


import java.time.Duration;
import java.util.List;

import org.testng.Assert;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageobjects.CartPage;
import pageobjects.CheckoutPage;
import pageobjects.ConfirmationPage;
import pageobjects.LandingPage;
import pageobjects.ProductCatalogue;

/**
 * Hello world!
 *
 */


public class PageObjectModel
{
	
    public static void main( String[] args ) throws InterruptedException
    {
    	
    	String productName = "ZARA COAT 3";
    	String countryName = "India";
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        LandingPage landingPage = new LandingPage(driver);
        landingPage.goTo();
        landingPage.loginapplication("lokithor@gmail.com", "Mmpl@2025");
        
  
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
       // driver.close();
    }
}
