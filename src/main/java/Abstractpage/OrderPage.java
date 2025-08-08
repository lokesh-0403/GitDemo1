package Abstractpage;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;



public class OrderPage extends AbstractComponent {
	WebDriver driver;
	
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		System.out.println("We are on the OrderPage");
	}
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	@FindBy(css="tr td:nth-child(3)")
	private List<WebElement> productNames;
	
	
	public Boolean VerifyOrderDisplay(String productName) throws InterruptedException {
		
		waitForWebElem(productNames);
		Boolean match = productNames.stream().anyMatch(p->p.getText().equalsIgnoreCase(productName));
		return match;
		
	}

}
