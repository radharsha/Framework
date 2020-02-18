package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RegionsPOM {

	public WebDriver driver;
	
			
	public RegionsPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//*[@id='menu-posts-property']//a[contains(text(),'Regions')]")
	private WebElement lkregions;
	
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
	
	
	
	
	public void clickRegionsBtn() {
		this.lkregions.click();
	}
	
	public void sendName(String regionName) {
		this.tbName.sendKeys(regionName);
	}
	
	public void sendSlug() {
		this.tbSlug.sendKeys("BBB_TestSlug");
	}
	
	public void sendDescription() {
		this.tbDescription.sendKeys("BBB_TestDescription........");
	}
	
	public void clickAddNewBtn() {
		this.btnAddNew.click();
	}
	
	public void searchRegion(String regionName) {
		this.tbSearchbox.sendKeys(regionName);
	}
	
	public void clickSearchBtn() {
		this.btnSearch.click();
	}
	

	public void selectRegionToBeDeletedCheckBox(String regionName) {
		WebElement checkBoxToBeSelected = driver.findElement(By.xpath("//label[@class='screen-reader-text' and contains(text(),'"+regionName+"')]/following-sibling::input[@type='checkbox']"));
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
	
