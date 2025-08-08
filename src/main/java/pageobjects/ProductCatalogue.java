package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Abstractpage.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{
	
  //  WebDriver driver;
    
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		 System.out.println(">>> ProductCatalogue constructor: driver = " + driver);
		//this.driver = driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}

	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
//	@FindBy(xpath="//button[text()=' Add To Cart']")
//	WebElement addtoCart;
	
	By productsBy = By.cssSelector(".mb-3");
	By addtoCart = By.xpath(".//button[normalize-space()='Add To Cart']");
	By toastMessage = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList() {
		waitForElem(productsBy);
		return products;
		
	}
	
	public WebElement getProductByName(String productName){
		WebElement prod = getProductList().stream().filter(p->p.
				findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		String text = prod.getText();
		System.out.println("Text of the item Mapped: "+text);
		return prod;
	}
	
	public void addProductToCart(String productName) throws InterruptedException {

		WebElement prod1 = getProductByName(productName);
		System.out.println("Text of the item Mapped: "+prod1.getText());
		//prod1.findElement(By.xpath(".//button[normalize-space()='Add To Cart']")).click();
			prod1.findElement(addtoCart).click();
//		waitForElem(toastMessage);
		Thread.sleep(3000);//		waitForElementToDisappear(spinner);
	}
}
