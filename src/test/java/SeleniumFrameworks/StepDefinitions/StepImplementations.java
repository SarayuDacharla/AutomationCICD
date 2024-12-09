package SeleniumFrameworks.StepDefinitions;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import SeleniumFrameworks.TestComponents.BaseTest;
import SeleniumFrameworks.pageobjects.CartPage;
import SeleniumFrameworks.pageobjects.CheckoutPage;
import SeleniumFrameworks.pageobjects.ConfirmationPage;
import SeleniumFrameworks.pageobjects.LoginPage;
import SeleniumFrameworks.pageobjects.ProductsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepImplementations extends BaseTest{
	public LoginPage loginPage;
	public ProductsPage productsPage;
	ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce Webpage")
	private void landingPage() throws IOException {
		loginPage = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	private void userLogin(String username,String password) {
		productsPage = loginPage.loginApplication(username,password);
	}
	
	@When("^I add the product (.+) to Cart$")
	private void pu(String productName) throws InterruptedException {
		List<WebElement> products = productsPage.getProductList();
		productsPage.addProductToCart(productName);
	}
	
	@When("^Checkout (.+) and Ubmit the order$")
	private void cartConfirmation(String productName) {
		CartPage cartPage = productsPage.goToCartPage();
		
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		confirmationPage = checkoutPage.submitOrder();
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	private void messageConfir(String string) {
		
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("{string} message is displayed")
	private void loginError(String string) {
		
		Assert.assertEquals(string, loginPage.getErrorMessage());
		driver.close();
	}
}
