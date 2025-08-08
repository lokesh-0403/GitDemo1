package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Abstractpage.AbstractComponent;

public class CartPage extends AbstractComponent {
	WebDriver driver;
	
	@FindBy(css = ".totalRow button")
	WebElement checkoutEle;
	
	@FindBy(css = "div[class='cartSection'] h3")
	private List<WebElement> productTitle;
	

	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean VerifyProductDisplay(String productName) throws InterruptedException {

        
	        Boolean match = productTitle.stream().anyMatch(cart-> cart.
	        		getText().equalsIgnoreCase(productName));
	        
	        Thread.sleep(2000);
	        return match;
	}
	
	 public CheckoutPage goToCheckOut() {
		 checkoutEle.click();
		 return new CheckoutPage(driver);
		 
	 } 
	       
		
	}

