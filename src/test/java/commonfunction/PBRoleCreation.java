package commonfunction;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

public class PBRoleCreation {
WebDriver driver;
public PBRoleCreation(WebDriver driver)
{
	this.driver=driver;
}
@FindBy(xpath = "//input[@id='btnRoles']")
WebElement clicknewrolebtn;
@FindBy(xpath = "//input[@id='txtRname']")
WebElement enterrolename;
@FindBy(name = "txtRDesc")
WebElement enterroledes;
@FindBy(xpath = "//select[@id='lstRtypeN']")
WebElement enterroletype;
@FindBy(xpath = "//input[@id='btninsert']")
WebElement clicksubmit;
public boolean verify_rolecreation(String rolename,String roledescription,String roletype)
{
	this.clicknewrolebtn.click();
	this.enterrolename.sendKeys(rolename);
	this.enterroledes.sendKeys(roledescription);
	new Select(enterroletype).selectByVisibleText(roletype);
	this.clicksubmit.click();
	String expectedalert=driver.switchTo().alert().getText();
	driver.switchTo().alert().accept();
	String actualalert="New Role with id";
	if(expectedalert.toLowerCase().contains(actualalert.toLowerCase()))
	{
		Reporter.log(expectedalert,true);
		return true;
	}
	else
	{
		Reporter.log("Role not created",true);
		return false;
	}
	
}

}
