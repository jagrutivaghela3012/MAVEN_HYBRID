package commonfunction;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class PBLogoutPage {
	WebDriver driver;
	public PBLogoutPage(WebDriver driver)
	{
		this.driver=driver;
	}
	@FindBy(xpath = "(//img)[4]")
	WebElement clicklogoutbtn;
	@FindBy(xpath = "//input[@id='login']")
	WebElement loginbtn;
	public boolean Verify_logout()
	{
		this.clicklogoutbtn.click();
		if(this.loginbtn.isDisplayed())
		{
			Reporter.log("Logout Success",true);
			return true;
		}
		else
		{
			Reporter.log("Logout Failed",true);
			return false;
		}


	}
}
