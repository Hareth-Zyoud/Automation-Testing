package excelTest;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import excelTest.ReadExcel;

public class LoginTest {
    public String baseUrl = "https://www.frontgate.com/UserLogonView";  
	String driverPath = "C:\\Users\\Hp\\Desktop\\QA\\ChromeWebDriver\\chromedriver.exe";  
	public WebDriver driver; 
	
    @Test(dataProvider = "Authentication")
    public void f(String sUsername, String sPassword) throws InterruptedException, IOException {
		// set the system property for Chrome driver      
		System.setProperty("webdriver.chrome.driver", driverPath);  
		// Create driver object for CHROME browser  
		driver = new ChromeDriver();  
		driver.manage().window().maximize();  
		driver.get(baseUrl);
		// Wait for 5 seconds
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#logonId")));
		//get Login username and password fields
		WebElement userName = driver.findElement(By.cssSelector("#logonId"));
		WebElement password = driver.findElement(By.cssSelector("#logonPassword"));
		WebElement loginButton = driver.findElement(By.cssSelector("#logonButton"));
		userName.clear();
		password.clear();
		userName.sendKeys(sUsername);
		password.sendKeys(sPassword);
	    loginButton.click();
    }
  
    @DataProvider(name = "Authentication")

    public static Object[][] credentials() throws IOException {
	  ReadExcel read = new ReadExcel();
	  String filePath = System.getProperty("user.dir")+"\\src\\test\\java\\excelTest";
	  String[][] creds = read.readExcel(filePath,"LoginData.xlsx","Sheet1");
      return creds;

  }
}
