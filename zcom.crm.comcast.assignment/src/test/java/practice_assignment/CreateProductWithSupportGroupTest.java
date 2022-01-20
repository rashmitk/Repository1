package practice_assignment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class CreateProductWithSupportGroupTest {

	public static void main(String[] args) throws Throwable {
		// get the data from property file
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
   //3.Select product from quick create drop down
	 WebElement quickCreateDropdown = driver.findElement(By.id("qccombo"));
	 quickCreateDropdown.click();
	 Select select=new Select(quickCreateDropdown);
	 select.selectByIndex(13);
	 WebElement selectedOption = select.getFirstSelectedOption();
		if(selectedOption.getText().equals("New Product")) {
			System.out.println("PASS::New Product option is selected and Product popup page is displayed");
		}else {
			System.out.println("FAIL::New Product option is selected and Product popup page is not displayed");
		}
		//create Product popup page should be displayed
		String expectedpopupPage="Product";
		String actualpopupPage = driver.findElement(By.xpath("//b[text()='Create Product']")).getText();
		if(actualpopupPage.contains(expectedpopupPage)) {
			System.out.println("PASS::Create Product popup page is displayed");
		}else {
			System.out.println("FAIL::Create Product popup page is not displayed");
		}
		
	//4. create a new product with product name-Sony
		FileInputStream fis1=new FileInputStream("./src/test/resources/VtigercrmCreateProduct-TestCaseData.xlsx");
		Workbook workbook=WorkbookFactory.create(fis1);
		String productName=workbook.getSheet("Sheet1").getRow(5).getCell(2).getStringCellValue();
		driver.findElement(By.name("productname")).sendKeys(productName);
		
	//5. a)Select Group radio button beside HAndler 

		WebElement groupRadioButton = driver.findElement(By.xpath("//input[not(@id='assigntype') and @type='radio' and @value='T']"));
		groupRadioButton.click();
		if (groupRadioButton.isSelected()) {
			System.out.println("PASS::Group Radio button is selected ");
		}else {
			System.out.println("FAIL::Group Radio button is not selected");
		}
	//	b)and select ""Marketing Group"" option from the dropdown
		WebElement handlerGroupDropdown = driver.findElement(By.name("assigned_group_id"));
		Select selectt=new Select(handlerGroupDropdown);
		 selectt.selectByIndex(1);
		 WebElement selectedOption1 = selectt.getFirstSelectedOption();
			if(selectedOption1.getText().equals("Support Group")) {
				System.out.println("PASS::Support Group option is selected");
			}else {
				System.out.println("FAIL::Support Group option is not selected");
			}

	//saving
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			
	//5. Sony - Product Information page should be displayed
			String actualProductName = driver.findElement(By.className("lvtHeaderText")).getText();
			if(actualProductName.contains(productName)) {
				System.out.println("PASS::Sony - Product Information page is displayed");
			}else {
				System.out.println("PASS::Sony - Product Information page is not displayed");
			}
			
			WebElement profileImage = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
			Actions action=new Actions(driver);
			action.moveToElement(profileImage).perform();
			driver.findElement(By.linkText("Sign Out")).click();
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
