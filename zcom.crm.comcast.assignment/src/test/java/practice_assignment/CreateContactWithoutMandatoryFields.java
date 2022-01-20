package practice_assignment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import zcom.crm.comcast.GenericUtility.ExcelUtility;
import zcom.crm.comcast.GenericUtility.FileUtility;
import zcom.crm.comcast.GenericUtility.IPathConstants;
import zcom.crm.comcast.GenericUtility.JavaUtility;
import zcom.crm.comcast.GenericUtility.WebDriverUtility;



public class CreateContactWithoutMandatoryFields {

	public static void main(String[] args) throws Throwable {
		//create Object for utilities
				FileUtility fUtil = new FileUtility();
				JavaUtility jUtil = new JavaUtility();
				ExcelUtility eUtil = new ExcelUtility();
				WebDriverUtility wUtil = new WebDriverUtility();
						
		// get the data from property file
				String browserName=fUtil.getPropertyFileData("browser");
				String expectedurl=fUtil.getPropertyFileData("url");
				String username=fUtil.getPropertyFileData("username");
				String password=fUtil.getPropertyFileData("password");
				String expectedTitle=fUtil.getPropertyFileData("title");
				
		//get the data from excel sheet
				String expectedAlertText = eUtil.getStringCellData("Sheet1", 1, 2);
				
		//how to launch browser
				WebDriver driver=null;
				if(browserName.equals("chrome")) {
					System.setProperty(IPathConstants.CHROME_KEY, IPathConstants.CHROME_PATH);
					driver=new ChromeDriver();
					System.out.println("PASS::Chrome Browser launched");
				}else if(browserName.equals("firefox")) {
					System.setProperty(IPathConstants.FIREFOX_KEY, IPathConstants.FIREFOX_PATH);
					driver=new FirefoxDriver();
					System.out.println("PASS::Firefox Browser launched");
			}else {
				System.out.println("FAIL::Browser not supported");
			}
			driver.manage().window().maximize();
			wUtil.waitForPageLoad(driver);
				
		//1.navigating to application 
			driver.get(expectedurl);
			String actualUrl=driver.getCurrentUrl();
		//verify Url
			if(actualUrl.equals(expectedurl)) {
				System.out.println("PASS::Login Page is displayed");
			}else {
				System.out.println("PASS::Login Page is not displayed");
			}
		//Login to application
			driver.findElement(By.name("user_name")).sendKeys(username);
			driver.findElement(By.name("user_password")).sendKeys(password);
			driver.findElement(By.id("submitButton")).click();
			driver.findElement(By.xpath("//a[@href='index.php?module=Contacts&action=index']")).click();
			driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		//saving
			driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();
		//getting Alert Text
			String actualAlertText = wUtil.getTheAlertText(driver);
		//Alert Text verifications
			if(actualAlertText.equals(expectedAlertText)) {
				System.out.println("PASS::AlerText is correct");
			}else {
				System.out.println("PASS::AlerText is incorrect");
			}
		//accept alert popup
			wUtil.acceptAlert(driver);
		//Logout Actions 
			 WebElement profileImage = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
			 wUtil.mouseHover(driver, profileImage);
			 driver.findElement(By.linkText("Sign Out")).click();
		 //Logout verifications
			 String afterLogoutUrl=driver.getCurrentUrl();
			 String expectedafterLogoutURL="http://localhost:8888/index.php?action=Login&module=Users";
				
				if(afterLogoutUrl.equals(expectedafterLogoutURL)) {
					System.out.println("PASS::Login Page is displayed");
				}else {
					System.out.println("PASS::Login Page is not displayed");
				}

				driver.quit();	
				
				
	}

}
