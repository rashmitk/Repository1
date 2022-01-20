package practice_assignment;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CreateContactTest {

	public static void main(String[] args) throws Throwable {
		String filePath="./src/test/resources/VtigercrmCommomData.properties";
		FileInputStream fis=new FileInputStream(filePath);
		Properties property=new Properties();
		property.load(fis);
		String browserName=property.getProperty("browser");
		String chromekey=property.getProperty("chromekey");
		String chromepath=property.getProperty("chromepath");
		String firefoxkey=property.getProperty("firefoxkey");
		String firefoxpath=property.getProperty("firefoxpath");
		String expectedurl=property.getProperty("url");
		String username=property.getProperty("username");
		String password=property.getProperty("password");
		String expectedTitle=property.getProperty("title");
		//how to launch browser
		WebDriver driver=null;
		if(browserName.equals("chrome")) {
			System.setProperty(chromekey, chromepath);
			driver=new ChromeDriver();
			System.out.println("PASS::Chrome Browser launched");
		}else if(browserName.equals("firefox")) {
			System.setProperty(firefoxkey, firefoxpath);
			driver=new FirefoxDriver();
			System.out.println("PASS::Firefox Browser launched");
	}else {
		System.out.println("FAIL::Browser not supported");
	}
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(20 ,TimeUnit.SECONDS);
	//1.navigating to application and verifying Url
	driver.get(expectedurl);
	String actualUrl=driver.getCurrentUrl();
	if(actualUrl.equals(expectedurl)) {
		System.out.println("PASS::Login Page is displayed");
	}else {
		System.out.println("PASS::Login Page is not displayed");
	}
	driver.findElement(By.name("user_name")).sendKeys(username);
	driver.findElement(By.name("user_password")).sendKeys(password);
	driver.findElement(By.id("submitButton")).click();
	//2.verifying title
	String actualTitle = driver.getTitle();
	if(actualTitle.equals(expectedTitle)) {
		System.out.println("PASS::Home Page is displayed");
	}else {
		System.out.println("FAIL::Home Page is not displayed");
	}
	//To click on contacts module
	driver.findElement(By.xpath("//a[@href='index.php?module=Contacts&action=index']")).click();
	driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
	//driver.findElement(By.)

	}

}
