package com.training.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PropertiesPOM {
	
	public WebDriver driver;
	public String propToBeDeleted;
		
	public PropertiesPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//*[contains(text(),'Properties')]")    
	private WebElement properties;
	
	@FindBy(xpath = "//*[contains(text(),'All Properties')]")
	private WebElement allProperties;
	
	//@FindBy(xpath="//td[@data-colname = 'Title']//a[contains(text()'"+propToBeDeleted+"')]//ancestor::td[@data-colname = 'Title']/preceding-sibling::th[@class='check-column']//input[@type='checkbox']")
	//WebElement checkBoxToBeSelected; 
	
	@FindBy(xpath = "//*[@id='bulk-action-selector-top']")
	WebElement dropDown;
	
	@FindBy(xpath="//*[@id='doaction']")
	WebElement applyBtn;
	
	
	public void clickPropertiesBtn() {	
		this.properties.click();		
		}
		
	public void clickAllPropertiesBtn() {
		this.allProperties.click();
	}
	
	
	public void selectPropToBeDeletedCheckBox(String propToBeDeleted) {
		WebElement checkBoxToBeSelected = driver.findElement(By.xpath("//td[@data-colname = 'Title']//a[contains(text(),'"+propToBeDeleted+"')]//ancestor::td[@data-colname = 'Title']/preceding-sibling::th[@class='check-column']//input[@type='checkbox']"));
		checkBoxToBeSelected.click();
	}
		
	public void selectDropDown() {
		Select option = new Select(dropDown);
	      option.selectByVisibleText("Move to Trash"); 	
	}
		
	public void clickApplyBtn() {
		applyBtn.click();
	}
		
	}
		
			
	
	

