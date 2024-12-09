package SeleniumFrameworks.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import SeleniumFrameworks.AbstractComponent.AbstractComponent;

public class LoginPage extends AbstractComponent{
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css="userEmail")
	WebElement userEmail;
	
	@FindBy(css="userPassword")
	WebElement userPassword;
	
	@FindBy(css="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyOut']")
	WebElement errorMessage;
	
	public ProductsPage loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submit.click();
		ProductsPage productsPage = new ProductsPage(driver);
		return productsPage;
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
}
