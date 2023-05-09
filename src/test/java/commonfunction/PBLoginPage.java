package commonfunction;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class PBLoginPage {
	WebDriver driver;
	public PBLoginPage(WebDriver driver)
	{
		this.driver=driver;
	}
	@FindBy(xpath = "//input[@id='txtuId']")
	WebElement username;
	@FindBy(xpath ="//input[@id='txtPword']")
	WebElement password;
	@FindBy(xpath = "//input[@id='login']")
	WebElement clickbtn;
	public boolean Verify_login(String user,String pass)
	{
		this.username.sendKeys(user);
		this.password.sendKeys(pass);
		this.clickbtn.click();
		String expected="adminflow";
		String actual=driver.getCurrentUrl();
		if(actual.toLowerCase().contains(expected.toLowerCase()))
		{
			Reporter.log("Login successful",true);
			return true;
		}
		else
		{
			String Error_msg=driver.switchTo().alert().getText();
			Reporter.log(Error_msg,true);
			driver.switchTo().alert().accept();
			return false;
		}
	}

}
