package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage extends BasePage {

	

	public LoginPage(WebDriver driver) {

		super(driver);

	}

	@FindBy(css = "#_username")
	WebElement userName;

	@FindBy(css = "#_password")
	WebElement password;

	@FindBy(css = "._qs_submit_btn.buttoninmobile")
	WebElement submit;

	public void LoginwithValid(String user, String Pass) {

		userName.sendKeys(user);
		password.sendKeys(Pass);
		submit.click();

	}

}
