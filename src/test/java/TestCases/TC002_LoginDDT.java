package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.BasePage;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import TestBase.BaseClass;
import Utlilities.DataProviders;

public class TC002_LoginDDT extends BaseClass {
	public BasePage bp;
	
	@Test(dataProvider = "LoginData", dataProviderClass=DataProviders.class)
	public void verifyLoginusingDDT(String email, String pass, String Expresult) {

		
			LoginPage Lp = new LoginPage(driver);
			Lp.LoginwithValid(email, pass);

			HomePage Hp = new HomePage(driver);
			String message = Hp.getMessage();
			
			if(Expresult.equalsIgnoreCase("pass")) {
				if(message.equals("Available Credits")) {
					bp =new BasePage(driver);
					bp.logout();
					Assert.assertTrue(true);
				}
				else
				{
					Assert.assertTrue(false);
				}
			}
			
			if(Expresult.equalsIgnoreCase("fail")) {
				
				if(message.equals("Available Credits")) {
					Assert.assertTrue(false);
				}
				else {
					
					Assert.assertTrue(true);
					
				}
				
			}
			

			

	

}
}