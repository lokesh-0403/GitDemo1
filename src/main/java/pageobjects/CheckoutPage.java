package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import Abstractpage.AbstractComponent;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class CheckoutPage extends AbstractComponent{

	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".action__submit")
	WebElement submit;
	
	@FindBy(css = "input[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;
	
	
	
	public void selectCountry(String countryName) throws InterruptedException {
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName ).build().perform();
		waitForElem(By.cssSelector("section[class='ta-results list-group ng-star-inserted']"));
		selectCountry.click();
		Thread.sleep(1000);
		JavascriptExecutor js =(JavascriptExecutor)driver;
		js.executeScript("window,scrollBy(0,400)");
		Thread.sleep(1000);
	}
	
	
	public ConfirmationPage SumbitOrder() {
	
		submit.click();
		return new ConfirmationPage(driver);
	}
}
