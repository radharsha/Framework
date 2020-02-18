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
import com.training.pom.RegionsPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class AddNewRegionTest {
	
	public WebDriver driver;
	public String baseUrl;
	public LoginPOM loginPOM;
	public static Properties properties;
	public ScreenShot screenShot;
	public PropertiesPOM propertiesPOM;
	public FeaturesPOM featuresPOM;
	public RegionsPOM regionsPOM;
	public String regionName = "BBB City";
	
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
		regionsPOM = new RegionsPOM(this.driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
		
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentReports/AddNewRegion.html",true);
		extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
		
		
	}
	
	
	
	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		//driver.quit();
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
		
		//Click on Regions Button
		@Test(priority = 2)
		public void clickRegionsBtn() throws InterruptedException {
			
			Thread.sleep(1000);
				
			 boolean ExpectedHeading = driver.findElement(By.xpath("//*[contains(text(),'Properties') and @class='wp-heading-inline']")).isDisplayed();
			Assert.assertTrue(ExpectedHeading);
			
			
			regionsPOM.clickRegionsBtn();
			screenShot.captureScreenShot("Clicked on Regions Button");
			System.out.println("Clicked on Regions Button");
		}
		
		//Add new region
				@Test(priority = 3)
				public void addNewRegion() throws InterruptedException {
					
					Thread.sleep(1000);
						
					 boolean ExpectedHeading = driver.findElement(By.xpath("//*[contains(text(),'Regions') and @class='wp-heading-inline']")).isDisplayed();
					Assert.assertTrue(ExpectedHeading);
					
					
					regionsPOM.clickRegionsBtn(); 				
					regionsPOM.sendName(regionName);
					regionsPOM.sendSlug();
					regionsPOM.sendDescription();
					
					screenShot.captureScreenShot("Entered values on Regions Screen");
					System.out.println("Entered values on Regions Screen");
					
					regionsPOM.clickAddNewBtn();
					Thread.sleep(1000);
					
					screenShot.captureScreenShot("Clicked on Add New Region Button");
					System.out.println("Clicked on Add New Region Button");
					logger = extent.startTest("Step 3 - Add New Region", "PASSED");
					logger.log(LogStatus.PASS, "Valid Add New Region");
					extent.endTest(logger);
					
					
									
				}
				
				
				@Test(priority = 4)
				public void verifyAddNewRegion() throws InterruptedException {
					
					regionsPOM.searchRegion(regionName);
					regionsPOM.clickSearchBtn();
					
					Thread.sleep(1000);
					boolean verifyNewRegionAdded = driver.findElement(By.xpath("//td[@data-colname='Name']//a[contains(text(),'"+regionName+"')]")).isDisplayed();
					Assert.assertTrue(verifyNewRegionAdded);
					
					screenShot.captureScreenShot("New Region Added Successfully");
					System.out.println("New Region Added Successfully");
					logger = extent.startTest("Step 4 - Verified New Region Addition", "PASSED");
					logger.log(LogStatus.PASS, "Verified New Region Addition");
					extent.endTest(logger);
				}
		
}
