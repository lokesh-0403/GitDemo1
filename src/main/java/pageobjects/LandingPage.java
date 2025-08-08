package pageobjects;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



import Abstractpage.AbstractComponent;
public class LandingPage extends AbstractComponent{
	WebDriver driver;
	
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
//	WebElement userEmail = driver.findElement(By.id("userEmail"));

	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessages;
	
	public void loginapplication(String email,String password) throws InterruptedException {
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
//		Thread.sleep(5000);
		
	}

	public String getErrorMessage() {
		waitForWebElem(errorMessages);
		return errorMessages.getText();
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
	}
}
