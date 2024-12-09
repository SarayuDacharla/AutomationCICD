package SeleniumFrameworks.Tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import SeleniumFrameworks.TestComponents.BaseTest;
import SeleniumFrameworks.pageobjects.CartPage;
import SeleniumFrameworks.pageobjects.CheckoutPage;
import SeleniumFrameworks.pageobjects.ConfirmationPage;
import SeleniumFrameworks.pageobjects.ProductsPage;

public class ErrorValidations extends BaseTest{

	@Test//(groups={ErrorHandling},retryAnalyzer=Retry.class)
	private void loginErrorValidations() {

		String productName = "ZARA COAT 3";
		ProductsPage productsPage = loginPage.loginApplication("saneram@gmail.com","Ram@12345");
		Assert.assertEquals("Incorrect email or password", loginPage.getErrorMessage());
	}
	@Test//(groups={ErrorHandling})
	private void productErrorValidations() throws InterruptedException {
		String productName = "ZARA COAT 3";
		ProductsPage productsPage = loginPage.loginApplication("saneram@gmail.com","Ram@1234");
		List<WebElement> products = productsPage.getProductList();
		productsPage.addProductToCart(productName);;
		CartPage cartPage = productsPage.goToCartPage();
		
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
	}
}
