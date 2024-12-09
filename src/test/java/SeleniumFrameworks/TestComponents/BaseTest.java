package SeleniumFrameworks.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import SeleniumFrameworks.pageobjects.LoginPage;

public class BaseTest {

	public WebDriver driver;
	public LoginPage loginPage;
		
	public WebDriver initializeDriver() throws IOException {
		
		//properties class
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\SeleniumFrameworks\\Resources\\GlobalData.properties");
		prop.load(file);
		//String browserName = prop.getProperty("browser");
		//getting maven properties
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		
		//for chrome driver
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		//for forefox driver
		else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		//for edge driver
		else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	@BeforeMethod(alwaysRun = true)//it will run everytime even if we apply grouping also
	public LoginPage launchApplication() throws IOException{
		driver = initializeDriver();
		LoginPage loginPage = new LoginPage(driver);
		loginPage.goTo();
		return loginPage;
	}
	
	public List<HashMap<String, String>> getJsonToMap(String filePath) throws IOException {
		//read json code and convert into string
		String jsonContent = FileUtils.readFileToString(new File(filePath),
				StandardCharsets.UTF_8);
	
		//String to HasMap
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){	
		});
		return data;
	}
	
	@AfterMethod(alwaysRun = true)
	public void close() {
		driver.close();
	}
	
	public String getScreenshot(String testcaseName, WebDriver driver) throws IOException {
		TakesScreenshot ss = (TakesScreenshot)driver;
		File source = ss.getScreenshotAs(OutputType.FILE);
			File file = new File(System.getProperty("user.dir")+"//reports//"+testcaseName+".png");
			FileUtils.copyFile(source, file);
			return System.getProperty("user.dir")+"//reports//"+testcaseName+".png";
		}
}
