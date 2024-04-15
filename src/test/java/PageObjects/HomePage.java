package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	
	public HomePage(WebDriver driver) {
		super(driver);

	}

	@FindBy(css = ".credit_font")
	WebElement message;

	public String getMessage() {
		try {

			String mess = message.getText();
			return mess;

		} catch (Exception e) {
			return (e.getMessage());
		}
	}

}
