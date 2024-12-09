package SeleniumFrameworks.Tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SeleniumFrameworks.TestComponents.BaseTest;
import SeleniumFrameworks.pageobjects.CartPage;
import SeleniumFrameworks.pageobjects.CheckoutPage;
import SeleniumFrameworks.pageobjects.ConfirmationPage;
import SeleniumFrameworks.pageobjects.LoginPage;
import SeleniumFrameworks.pageobjects.OrderPage;
import SeleniumFrameworks.pageobjects.ProductsPage;

public class SubmitOrder extends BaseTest {

	String productName = "ZARA COAT 3";
	@Test(dataProvider = "getData",groups = {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		
		//WebDriverManager.chromedriver().setup(); it automatically downloads chromedriver		
		launchApplication();
		ProductsPage productsPage = loginPage.loginApplication("email","password");
		
		List<WebElement> products = productsPage.getProductList();
		productsPage.addProductToCart(input.get("product"));
		CartPage cartPage = productsPage.goToCartPage();
		
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
	}

	@Test(dependsOnMethods = {"submitOrder"})
	public void OrderHistory() {
		ProductsPage productInfo = loginPage.loginApplication("email","password");
		OrderPage ordersPage = productInfo.goToOrderPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "saneram@gmail.com");
//		map.put("password", "Ram@12345");
//		map.put("productName", "ZARA COAT 3");
//
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "saneram@gmail.com");
//		map.put("password", "Ram@1234");
//		map.put("productName", "ZARA COAT 3");
		
		List<HashMap<String, String>> data = getJsonToMap(System.getProperty("user.dir")+"\\src\\test\\java\\SeleniumFrameworks\\Data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
//		return new Object[][] {{"saneram@gmail.com","Ram@12345","ZARA COAT 3"},{"saneram@gmail.com","Ram@1234","ZARA COAT 3"}};
	
	}
}

