package zcom.crm.comcast.GenericUtility;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
/**
 * WebDriver Utility
 * @author Rashmi T K
 *
 */

public class WebDriverUtility {
	/**
	 * It will wait 20 seconds till the element load in DOM
	 * @param driver
	 */
	public void waitForPageLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	/**
	 * This method will wait till element is visble
	 * @param driver
	 * @param element
	 */
	public void waitForVisibilityOfElement(WebDriver driver,WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * This method will wait till element is clickable
	 * @param driver
	 * @param element
	 */
	public void waitForElementToBeClick(WebDriver driver,WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}
	
	/**
	 * This method is used to wait for the element and do click operation
	 * @param element
	 * @throws Throwable
	 */
	public void waitAndClick(WebElement element) throws Throwable {
		int count=0;
		while(count<50) {
			try {
				element.click();
			}catch(Throwable e) {
				Thread.sleep(1000);
				count++;
			}
		}
	}
	/**
	 * This method will select the element based on index
	 * @param element
	 * @param index
	 * @return 
	 */
	public void selectOption(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
			
		}
	/**
	 * This method will select the element based on value
	 * @param element
	 * @param value
	 */
	  public void selectOption(WebElement element,String value) {
		Select select = new Select(element);
		select.selectByValue(value);
	}
	/**
	 * This method will select the element based on text
	 * @param element
	 * @param text
	 */
	  public void selectOption(String text,WebElement element) {
		Select select = new Select(element);
		select.selectByValue(text);
	}
	  
	  /**
	   * This method will verify whether expected option is present in dropdown list or not
	   * @param element
	   * @param expectedOptions
	   * @return
	   */
	  public WebElement verifyTheDropDownList(WebElement element,String expectedOptions) {
		  Select select = new Select(element);
		  List<WebElement> options = select.getOptions();
		  for(WebElement eachele:options) {
			  System.out.println(eachele.getText());
			  if(eachele.getText().equals(expectedOptions)) {
				  System.out.println("PASS:: value is present in dropdown");
				  return eachele;
			  }else {
				  System.out.println("FAIL:: value is not present in dropdown");
				  return eachele;
			  }
		  }
		  return null;
		  }
	  
	  /**
	   * This method will verify expected option is selected or not
	   * @param element
	   * @param expectedOptions
	   */
	  public void verifySelectedOptionInDropDownList(WebElement element,String expectedOption) {
	  Select select = new Select(element);
	  WebElement selectedOption = select.getFirstSelectedOption();
	if(selectedOption.getText().equals(expectedOption)) {
		System.out.println("PASS::option is selected");
	}else {
		System.out.println("FAIL::option is not selected");
	}
	  }
	  
	  /**
	   * This method performs mouse hovering action
	   * @param driver
	   * @param element
	   */
	  public void mouseHover(WebDriver driver,WebElement element) {
		  Actions action = new Actions(driver);
		  action.moveToElement(element).perform();
	  }
	  
	  /**
	   * This method performs right click action
	   * @param driver
	   * @param element
	   */
	  public void rightClick(WebDriver driver,WebElement element) {
		  Actions action = new Actions(driver);
		  action.contextClick(element).perform();
	  }
	  
	  /**
	   * This method is used to perform javascript click on element
	   * @param driver
	   * @param element
	   */
	  public void jsClick(WebDriver driver,WebElement element) {
		  JavascriptExecutor js = (JavascriptExecutor)driver;
		  js.executeScript("arguments[0].click", element);
	  }
	  /**
	   * This method is used to send the value using javascript executor
	   * @param driver
	   * @param valueEnter
	   * @param element
	   */
	  public void enterDataUsingJs(WebDriver driver,String valueEnter,WebElement element) {
		  JavascriptExecutor js=(JavascriptExecutor)driver;
		  js.executeScript("arguments[0].value='"+valueEnter+"'", element);
	  }
	  
	  /**
	   * This method will switch to different windows using partial title
	   * @param driver
	   * @param partialWindowTitle
	   */
	  public void switchWindow(WebDriver driver,String partialWindowTitle) {
		 Set<String> winIds = driver.getWindowHandles();
		 Iterator<String> it=winIds.iterator();
		 while(it.hasNext()) {
			 String winId=it.next();
			 String title = driver.switchTo().window(winId).getTitle();
			 if(title.contains(partialWindowTitle)) {
				 break;
			 }
			 
		 }
	  }
	  
	  /**
	   * This method is used to take screenshot in the case of test script failures
	   * @param driver
	   * @param testCaseName
	   * @return
	   */
	  public String screenShot(WebDriver driver,String testCaseName) {
		  JavaUtility jUtil = new JavaUtility();
		  String filePath="./errorshot"+testCaseName+jUtil.getSystemDateAndTime()+".png";
		  TakesScreenshot ts=(TakesScreenshot)driver;
		  File source = ts.getScreenshotAs(OutputType.FILE);
		  File destination = new File(filePath);
		  //Files.copy(source,destination);
		  source.renameTo(destination);
		  return filePath;
	  }
	  
	  /**
	   * This method will switch the frames based on the index passed
	   * @param driver
	   * @param index
	   */
	  public void switchFrames(WebDriver driver,int index) {
		  driver.switchTo().frame(index);
	  }
	  
	  /**
	   * This method will switch the frames based on the name or Id passed
	   * @param driver
	   * @param nameOrId
	   */
	  public void switchFrames(WebDriver driver,String nameOrId) {
		  driver.switchTo().frame(nameOrId);
	  }
	  
	  /**
	   * This method will switch the frames based on the WebElement passed
	   * @param driver
	   * @param element
	   */
	  public void switchFrames(WebDriver driver,WebElement element) {
		  driver.switchTo().frame(element);
	  }
	  
	  /**
	   * This method will accept the alert
	   * @param driver
	   */
	  public void acceptAlert(WebDriver driver) {
		  driver.switchTo().alert().accept();
	  }
	  /**
	   * This method will dismiss the alert
	   * @param driver
	   */
	  public void dismissAlert(WebDriver driver) {
		  driver.switchTo().alert().dismiss();
	  }
	  /**
	   * This method will get the text of the alert popup
	   * @param driver
	   * @return
	   */
	  public String getTheAlertText(WebDriver driver) {
		  Alert alert = driver.switchTo().alert();
		  String alertText = alert.getText();
		  System.out.println("PASS::Error Message displayed ="+alertText);
		  return alertText;  
	  }
	  
	  
	 
}


