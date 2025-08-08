package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import Abstractpage.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{
	
	WebDriver driver;

	
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".hero-primary")
	WebElement confirmMessage;
	
	public String verifyMessage() {
		String text = confirmMessage.getText();
		return text;
	
	}

}
