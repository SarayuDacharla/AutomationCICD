package SeleniumFrameworks.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFrameworks.AbstractComponent.AbstractComponent;

public class OrderPage extends AbstractComponent{
	
		WebDriver driver;
		
		public OrderPage(WebDriver driver) {
			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}
		@FindBy(css=".totalRow button")
		WebElement checkoutEle;
		
		@FindBy(css="tr td:nth-child(3)")
		public List<WebElement> productsName;
			
		public Boolean VerifyOrderDisplay(String productName) {
			Boolean match = productsName.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
			return match;
		}
		
	}

//	public static boolean VerifyOrderDisplay(String productName) {
//		// TODO Auto-generated method stub
//		return false;
//	}

