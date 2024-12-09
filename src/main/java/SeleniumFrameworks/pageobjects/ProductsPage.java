package SeleniumFrameworks.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFrameworks.AbstractComponent.AbstractComponent;

public class ProductsPage extends AbstractComponent{
	WebDriver driver;
	
	public ProductsPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css=".mb-3")
	List<WebElement> products;

	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-Type");
	By toastMessage = By.cssSelector("#toast-container");
	
	public void addProductToCart(String productName) throws InterruptedException {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementsToDisappear(spinner);
	}

	private WebElement getProductByName(String productName) {
		WebElement prod = getProductList().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}

	public CartPage goToCartPage() {
		// TODO Auto-generated method stub
		return null;
	}

}
