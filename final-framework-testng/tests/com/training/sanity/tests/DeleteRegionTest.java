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

public class DeleteRegionTest {
	
	public WebDriver driver;
	public String baseUrl;
	public LoginPOM loginPOM;
	public static Properties properties;
	public ScreenShot screenShot;
	public PropertiesPOM propertiesPOM;
	public FeaturesPOM featuresPOM;
	public RegionsPOM regionsPOM;
	public String regionName = "BBB";
	
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
		
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentReports/DeleteRegion.html",true);
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
			
			Thread.sleep(5000);
				
			 boolean ExpectedHeading = driver.findElement(By.xpath("//*[contains(text(),'Properties') and @class='wp-heading-inline']")).isDisplayed();
			Assert.assertTrue(ExpectedHeading);
			
			
			regionsPOM.clickRegionsBtn(); 
			screenShot.captureScreenShot("Clicked on Regions Button");
			System.out.println("Clicked on Regions Button");
		}
		
					
				//Search for the required region and select the related Checkbox
				@Test(priority = 3)
				public void searchSelectRegion() throws InterruptedException {
					
					regionsPOM.searchRegion(regionName);
					regionsPOM.clickSearchBtn();
					
					Thread.sleep(1000);
					boolean verifyNewRegionAdded = driver.findElement(By.xpath("//td[@data-colname='Name']//a[contains(text(),'"+regionName+"')]")).isDisplayed();
					Assert.assertTrue(verifyNewRegionAdded);
					
					regionsPOM.selectRegionToBeDeletedCheckBox(regionName);
					
					screenShot.captureScreenShot("Selected the region to be deleted");
					System.out.println("Selected the region to be deleted");
					logger = extent.startTest("Step 3 - Selected the Region", "PASSED");
					logger.log(LogStatus.PASS, "Selected the Region");
					extent.endTest(logger);
				}
				
				
				//Delete the selected Region and verify deletion.
				@Test(priority = 4)
				public void deleteRegion() throws InterruptedException {
					
					
					
					Thread.sleep(1000);
					
					String bsearchCountText = driver.findElement(By.xpath("//span[@class='displaying-num']")).getText();
					String bsearchCountInt = bsearchCountText.replaceAll("[^0-9]", "");
					
					//System.out.println(bsearchCountText);
					//System.out.println(bsearchCountInt);
					
					regionsPOM.selectDropDown();
					regionsPOM.clickApplyBtn();
					
					Thread.sleep(1000);
					
					String asearchCountText = driver.findElement(By.xpath("//span[@class='displaying-num']")).getText();
					String asearchCountInt = asearchCountText.replaceAll("[^0-9]", "");
					
					//System.out.println(asearchCountText);
					//System.out.println(asearchCountInt);
					
					if((Integer.parseInt(asearchCountInt))==(Integer.parseInt(bsearchCountInt)-1)) {
						
						System.out.println("Region Deleted Successfully");
					}
					else
						System.out.println("Region Deletion UnSuccessful");
					
					screenShot.captureScreenShot("Region Deleted Successfully");
					
					logger = extent.startTest("Step 4 - Deleted the Region", "PASSED");
					logger.log(LogStatus.PASS, "Deleted the Region");
					extent.endTest(logger);					
										
									
					
				}
				
		
}
