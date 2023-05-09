package commonfunction;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class PBRolesUpdation {
	WebDriver driver;
	public PBRolesUpdation(WebDriver driver)
	{
		this.driver=driver;
	}
	@FindBy(xpath = "(//img)[10]")
	WebElement clickeditbtn;
	@FindBy(xpath = "//input[@id='txtrNameU']")
	WebElement updaterolename;
	@FindBy(xpath = "//input[@id='btnupdate']")
	WebElement clickupdatebtn;
	public boolean verify_roleupdation(String rolename)
	{
		this.clickeditbtn.click();
		this.updaterolename.clear();
		this.updaterolename.sendKeys(rolename);
		this.clickupdatebtn.click();
		String expectedalert=driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		String actualalert="Role updated";
		if(expectedalert.toLowerCase().contains(actualalert.toLowerCase()))
		{
			Reporter.log(expectedalert,true);
			return true;
		}
		else
		{
			Reporter.log("Roles not updated",true);
			return false;
		}
	}

}
