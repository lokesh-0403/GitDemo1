package org.zasya.AutoPractice;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.CartPage;
import pageobjects.ProductCatalogue;

public class ErrorHandling extends BaseTest{
	@Test(groups= {"ErrorHandling"})
		public void LoginErrorValidation() throws InterruptedException {
		
	   
	        
	        landingPage.loginapplication("lokithor@gmail.com", "Mmpl@2003");
	        
	        Assert.assertEquals("Incorrect email or passwor", landingPage.getErrorMessage());
	        System.out.println("__-The error is being detected. either of the value is invalid-__");
		}
	
	@Test
	public void ProductErrorValidation() throws InterruptedException {
		String productName = "IPHONE 13 PRO";

		landingPage.loginapplication("lokithor@gmail.com", "Mmpl@2025");

		ProductCatalogue pr = new ProductCatalogue(driver);

		List<WebElement> products = pr.getProductList();
		System.out.println("Products found: " + products.size());
		 pr.addProductToCart(productName);
		CartPage cartPage = pr.goToCartPage();
		 Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 3");		

		

		
		Assert.assertTrue(match, "❌ Product '" + productName + "' not found in Cart Page");

	}

}
