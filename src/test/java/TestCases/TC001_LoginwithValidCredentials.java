package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.BasePage;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import TestBase.BaseClass;

public class TC001_LoginwithValidCredentials extends BaseClass {

	@Test (groups = {"Sanity", "Regression"})
	public void verifyLogin() {
		logger.info("******Starting TC001_LoginWithValid********");
		logger.debug("***Application Logs***");
		try {
		LoginPage Lp = new LoginPage(driver);
		Lp.LoginwithValid(p.getProperty("user"), p.getProperty("pass"));
		
		logger.info("********Sucessfully Login************");
		HomePage Hp = new HomePage(driver);
		String message = Hp.getMessage();

		if(message.equals("Available Credits")) {
			logger.info("***Passed***");
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("****Failed****");
			logger.debug("***Debug logs*****");
			Assert.fail();
		}
		
		}
		catch (Exception e) {
		
		logger.error("*********TestFail********");
		Assert.fail();

	}
		BasePage bp = new BasePage(driver);
		bp.logout();
		logger.info("Logout Sucessful");
		logger.info("****Complete TC001********");
	}
}
