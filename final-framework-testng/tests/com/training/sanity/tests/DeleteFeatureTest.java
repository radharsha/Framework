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

public class DeleteFeatureTest {
	
	public WebDriver driver;
	public String baseUrl;
	public LoginPOM loginPOM;
	public static Properties properties;
	public ScreenShot screenShot;
	public PropertiesPOM propertiesPOM;
	public FeaturesPOM featuresPOM;
	public String featureName = "AAA";
	
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
		
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentReports/DeleteFeature.html",true);
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
		
					
				//Search for the required feature and select the related Checkbox
				@Test(priority = 3)
				public void searchSelectFeature() throws InterruptedException {
					
					featuresPOM.searchFeature(featureName);
					featuresPOM.clickSearchBtn();
					
					Thread.sleep(1000);
					boolean verifyNewFeatureAdded = driver.findElement(By.xpath("//td[@data-colname='Name']//a[contains(text(),'"+featureName+"')]")).isDisplayed();
					Assert.assertTrue(verifyNewFeatureAdded);
					
					featuresPOM.selectFeatureToBeDeletedCheckBox(featureName);
					
					screenShot.captureScreenShot("Selected the feature to be deleted");
					System.out.println("Selected the feature to be deleted");
					logger = extent.startTest("Step 3 - Selected the Feature", "PASSED");
					logger.log(LogStatus.PASS, "Selected the Feature");
					extent.endTest(logger);
				}
				
				
				//Delete the selected Feature and verify deletion.
				@Test(priority = 4)
				public void deleteFeature() throws InterruptedException {
					
					
					
					Thread.sleep(1000);
					
					String bsearchCountText = driver.findElement(By.xpath("//span[@class='displaying-num']")).getText();
					String bsearchCountInt = bsearchCountText.replaceAll("[^0-9]", "");
					
					//System.out.println(bsearchCountText);
					//System.out.println(bsearchCountInt);
					
					featuresPOM.selectDropDown();
					featuresPOM.clickApplyBtn();
					
					Thread.sleep(1000);
					
					String asearchCountText = driver.findElement(By.xpath("//span[@class='displaying-num']")).getText();
					String asearchCountInt = asearchCountText.replaceAll("[^0-9]", "");
					
					//System.out.println(asearchCountText);
					//System.out.println(asearchCountInt);
					
					if((Integer.parseInt(asearchCountInt))==(Integer.parseInt(bsearchCountInt)-1)) {
						
						System.out.println("Feature Deleted Successfully");
					}
					else
						System.out.println("Feature Deletion UnSuccessful");
					
					screenShot.captureScreenShot("Feature Deleted Successfully");
					
					logger = extent.startTest("Step 4 - Deleted the Feature", "PASSED");
					logger.log(LogStatus.PASS, "Deleted the Feature");
					extent.endTest(logger);					
										
									
					
				}
				
		
}
