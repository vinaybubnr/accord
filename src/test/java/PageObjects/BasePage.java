package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
	
    WebDriver driver;
	
	public BasePage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
		
	}
	
	
	@FindBy(xpath ="//a[@class='dropdown-item' and text()='Logout']")
	WebElement logout;
	
	@FindBy(css ="#navbardrop")
	WebElement Myprofile;
	
	public void logout() {
		Myprofile.click();
		logout.click();
	}

}
