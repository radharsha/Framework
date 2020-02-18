package com.training.sanity.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.training.generics.ScreenShot;
import com.training.pom.PropertiesPOM;
import com.training.pom.LoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;



public class PropertiesTest {
	
	public WebDriver driver;
	public String baseUrl;
	public LoginPOM loginPOM;
	public static Properties properties;
	public ScreenShot screenShot;
	public PropertiesPOM propertiesPOM;
	
	
	  
	  public ExtentReports extent; 
	  //helps to generate	  the logs in test report. 
	  public ExtentTest logger;
	 
	

	@BeforeTest
	public void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		loginPOM = new LoginPOM(driver); 
		propertiesPOM = new PropertiesPOM(this.driver);		
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
		
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/MovePropToTrash.html",true);
		extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
			 
	}
	
	
	
	@AfterTest
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
		//extent.close();
		extent.flush();
	}
	
	//Login to the Application
	@Test(priority = 0)
	public void validLoginTest() {
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		loginPOM.clickLoginBtn(); 
		screenShot.captureScreenShot("After Login Screen");
		System.out.println("After Login Screen");
		logger = extent.startTest("Step 1 - Login", "PASSED");
		logger.log(LogStatus.PASS, "Valid LoginTest");
		extent.endTest(logger);
	}
	
	//Click on Properties Button
	@Test(priority = 1)
	public void clickPropBtn() throws InterruptedException {
		Thread.sleep(5000);
		propertiesPOM.clickPropertiesBtn(); 
		screenShot.captureScreenShot("Clicked on Properties Button");
		System.out.println("Clicked on Properties Button");
		logger = extent.startTest("Step 2 - Click on Properties Btn", "PASSED");
		logger.log(LogStatus.PASS, "Valid PropBtn Click");
		extent.endTest(logger);
	}
	
	
	//Click on All Properties Button
	@Test(priority = 2)
	public void clickAllPropBtn() throws InterruptedException {
		
		Thread.sleep(5000);
			
		 boolean ExpectedHeading = driver.findElement(By.xpath("//*[contains(text(),'Properties') and @class='wp-heading-inline']")).isDisplayed();
		Assert.assertTrue(ExpectedHeading);
		
		
		propertiesPOM.clickAllPropertiesBtn(); 
		screenShot.captureScreenShot("Clicked on All Properties Button");
		System.out.println("Clicked on All Properties Button");
		logger = extent.startTest("Step 3 - Click on All Properties Btn", "PASSED");
		logger.log(LogStatus.PASS, "Valid AllProp click");
		extent.endTest(logger);
	}

	
	//Search for the Property to be deleted and select the checkbox against it.
	@Test(priority = 3)
	public void selectPropToBeDeleted() throws InterruptedException {
		
		Thread.sleep(5000);	 
		String propToBeDeleted = "new launch1";
		propertiesPOM.selectPropToBeDeletedCheckBox(propToBeDeleted); 
		screenShot.captureScreenShot("Selected the check box");
		System.out.println("Selected the check box");
		logger = extent.startTest("Step 4 - CheckBox Selection", "PASSED");
		logger.log(LogStatus.PASS, "Valid Checkbox Selection");
		extent.endTest(logger);
	}
	
	
	//Select the Option - 'Move to Trash' from the Dropdown List
	@Test(priority = 4)
	public void selectOptionMoveToTrash() throws InterruptedException {
		
		Thread.sleep(5000);	 
		propertiesPOM.selectDropDown(); 
		screenShot.captureScreenShot("Selected Option - Move To Trash");
		System.out.println("Selected Option - Move To Trash");
		logger = extent.startTest("Step 5 - Select Option from Dropdown", "PASSED");
		logger.log(LogStatus.PASS, "Valid Dropdown optn selection");
		extent.endTest(logger);
		
		
	}
	
	
	//Click on Apply Button and confirm deletion of the selected Property
	@Test(priority = 5, dependsOnMethods = { "selectOptionMoveToTrash" })
	public void clickApplyBtn() throws InterruptedException {
		
		Thread.sleep(5000);	
		propertiesPOM.clickApplyBtn();
		screenShot.captureScreenShot("Clicked on Apply Button");
		System.out.println("Clicked on Apply Button");
		WebElement deleteConfirmationText = driver.findElement(By.xpath("//*[@id='message']"));
		try {
			Assert.assertTrue(deleteConfirmationText.getText().contains("post moved to the Trash"));
			screenShot.captureScreenShot("Post Moved To Trash");
			System.out.println("Post Deleted");
			logger = extent.startTest("Step 6 - Click on Apply Btn", "PASSED");
			logger.log(LogStatus.PASS, "Valid Prop Deletion");
			extent.endTest(logger);
		} catch (AssertionError e) {
			// TODO Auto-generated catch block
			System.out.println("Error Msg " + e +" "+ deleteConfirmationText );
			screenShot.captureScreenShot("Exception After clicking Apply");
			logger = extent.startTest("Step 6 - Click on Apply Btn", "FAILED");
			logger.log(LogStatus.FAIL, "Invalid LoginTest");
			extent.endTest(logger);
		}
	}
	
	
}
