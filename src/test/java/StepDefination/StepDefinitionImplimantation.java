package StepDefination;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.zasya.AutoPractice.BaseTest;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.CartPage;
import pageobjects.CheckoutPage;
import pageobjects.ConfirmationPage;
import pageobjects.LandingPage;
import pageobjects.ProductCatalogue;

public class StepDefinitionImplimantation  extends BaseTest {
	public LandingPage landingPage;
	public ProductCatalogue pr;
	public  ConfirmationPage confimPage;
	@Given("I landed on Ecom page")
	public void I_landed_on_ecom_Page() throws IOException{
		landingPage = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username,String password) throws InterruptedException {
		landingPage.loginapplication(username,password);
	}
	
	@When("^When I add product (.+) to car$")
	public void i_add_product_to_cart(String productName) throws InterruptedException {
		  List<WebElement> products = pr.getProductList();
		  pr.addProductToCart(productName);
	}
	@And("^checkout (.+) and submit the order to (.+)$")
	public void checkout_submitOrder(String productName, String countryName ) throws InterruptedException {
		CartPage cartPage = pr.goToCartPage();
        
	      
        Boolean match = cartPage.VerifyProductDisplay(productName);
        Assert.assertTrue(match);
        
        CheckoutPage checkoutpage = cartPage.goToCheckOut();
        
        checkoutpage.selectCountry(countryName);
       checkoutpage.SumbitOrder();
	}
	
	@Then("{string} meesage is displayed on confimation page")
	public void message_displayed_confirmationPage(String string) {
		confimPage.verifyMessage();
        String line = string;
        String text = confimPage.verifyMessage();
        Assert.assertEquals(text, line.toUpperCase());
	}
}
