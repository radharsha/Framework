package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class FeaturesPOM {

	public WebDriver driver;
	
			
	public FeaturesPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//*[@id='menu-posts-property']//a[contains(text(),'Features')]")
	private WebElement lkfeatures;
	
	@FindBy(xpath = "//*[@id='tag-name']")
	private WebElement tbName;
	
	@FindBy(xpath = "//*[@id='tag-slug']")
	private WebElement tbSlug;
	
	@FindBy(xpath = "//*[@id='tag-description']")
	private WebElement tbDescription;
	
	@FindBy(xpath = "//*[@id='submit']")
	private WebElement btnAddNew;
	
	@FindBy(xpath = "//*[@id='tag-search-input']")
	private WebElement tbSearchbox;
	
	@FindBy(xpath = "//*[@id='search-submit']")
	private WebElement btnSearch;
	
	@FindBy(xpath = "//*[@id='bulk-action-selector-top']")
	WebElement dropDown;
	
	@FindBy(xpath="//*[@id='doaction']")
	WebElement applyBtn;
	
	
	
	
	public void clickFeaturesBtn() {
		this.lkfeatures.click();
	}
	
	public void sendName(String featureName) {
		this.tbName.sendKeys(featureName);
	}
	
	public void sendSlug() {
		this.tbSlug.sendKeys("AAA_TestSlug");
	}
	
	public void sendDescription() {
		this.tbDescription.sendKeys("AAA_TestDescription........");
	}
	
	public void clickAddNewBtn() {
		this.btnAddNew.click();
	}
	
	public void searchFeature(String featureName) {
		this.tbSearchbox.sendKeys(featureName);
	}
	
	public void clickSearchBtn() {
		this.btnSearch.click();
	}
	

	public void selectFeatureToBeDeletedCheckBox(String featureName) {
		WebElement checkBoxToBeSelected = driver.findElement(By.xpath("//label[@class='screen-reader-text' and contains(text(),'"+featureName+"')]/following-sibling::input[@type='checkbox']"));
		checkBoxToBeSelected.click();
	}
	
	public void selectDropDown() {
		Select option = new Select(dropDown);
	      option.selectByVisibleText("Delete"); 	
	}
		
	public void clickApplyBtn() {
		applyBtn.click();
	}
		
		
	}
	
