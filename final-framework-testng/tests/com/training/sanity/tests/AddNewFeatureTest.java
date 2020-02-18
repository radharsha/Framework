package com.training.sanity.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.training.generics.ScreenShot;
import com.training.pom.FeaturesPOM;
import com.training.pom.LoginPOM;
import com.training.pom.PropertiesPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class AddNewFeatureTest {
	
	public WebDriver driver;
	public String baseUrl;
	public LoginPOM loginPOM;
	public static Properties properties;
	public ScreenShot screenShot;
	public PropertiesPOM propertiesPOM;
	public FeaturesPOM featuresPOM;
	public String featureName = "AAA Apartments";
	
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
		featuresPOM = new FeaturesPOM(this.driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
		
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentReports/AddNewFeature.html",true);
		extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
		
		
	}
	
	
	
	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		//driver.quit();
		//extent.close();
		//extent.flush();
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
		
		//Click on Features Button
		@Test(priority = 2)
		public void clickFeaturesBtn() throws InterruptedException {
			
			Thread.sleep(5000);
				
			 boolean ExpectedHeading = driver.findElement(By.xpath("//*[contains(text(),'Properties') and @class='wp-heading-inline']")).isDisplayed();
			Assert.assertTrue(ExpectedHeading);
			
			
			featuresPOM.clickFeaturesBtn(); 
			screenShot.captureScreenShot("Clicked on Features Button");
			System.out.println("Clicked on Features Button");
		}
		
		//Add new Feature
				@Test(priority = 3)
				public void addNewFeature() throws InterruptedException {
					
					Thread.sleep(5000);
						
					 boolean ExpectedHeading = driver.findElement(By.xpath("//*[contains(text(),'Features') and @class='wp-heading-inline']")).isDisplayed();
					Assert.assertTrue(ExpectedHeading);
					
					
					featuresPOM.clickFeaturesBtn(); 				
					featuresPOM.sendName(featureName);
					featuresPOM.sendSlug();
					featuresPOM.sendDescription();
					
					screenShot.captureScreenShot("Entered values on Features Screen");
					System.out.println("Entered values on Features Screen");
					
					featuresPOM.clickAddNewBtn();
					Thread.sleep(1000);
					
					screenShot.captureScreenShot("Clicked on Add New Feature Button");
					System.out.println("Clicked on Add New feature Button");
					logger = extent.startTest("Step 3 - Add New feature", "PASSED");
					logger.log(LogStatus.PASS, "Valid Add New feature");
					extent.endTest(logger);
					
					
									
				}
				
				
				@Test(priority = 4)
				public void verifyAddNewFeature() throws InterruptedException {
					
					featuresPOM.searchFeature(featureName);
					featuresPOM.clickSearchBtn();
					
					Thread.sleep(1000);
					boolean verifyNewFeatureAdded = driver.findElement(By.xpath("//td[@data-colname='Name']//a[contains(text(),'"+featureName+"')]")).isDisplayed();
					Assert.assertTrue(verifyNewFeatureAdded);
					
					screenShot.captureScreenShot("New Feature Added Successfully");
					System.out.println("New Feature Added Successfully");
					logger = extent.startTest("Step 4 - Verified New Feature Addition", "PASSED");
					logger.log(LogStatus.PASS, "Verified New Feature Addition");
					extent.endTest(logger);
				}
		
}
