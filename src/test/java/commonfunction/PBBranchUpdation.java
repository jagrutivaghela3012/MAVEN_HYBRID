package commonfunction;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class PBBranchUpdation {
	WebDriver driver;
	public PBBranchUpdation(WebDriver driver)
	{
		this.driver=driver;
	}
	@FindBy(xpath = "(//img)[10]")
	WebElement clickeditbtn;
	@FindBy(xpath = "//input[@id='txtbnameU']")
	WebElement updatebranchname;
	@FindBy(xpath = "(//input[@id='txtadd1u'])[1]")
	WebElement updateaddress1;
	@FindBy(xpath = "//input[@id='txtareaU']")
	WebElement updatearea;
	@FindBy(xpath = "//input[@id='txtzipu']")
	WebElement updatezipcode;
	@FindBy(xpath = "//input[@id='btnupdate']")
	WebElement clickupdatebtn;
	public boolean verify_branchUpdation(String branchname,String address1,String area,String zipcode)
	{
		this.clickeditbtn.click();
		this.updatebranchname.clear();
		this.updatebranchname.sendKeys(branchname);
		this.updateaddress1.clear();
		this.updateaddress1.sendKeys(address1);
		this.updatearea.clear();
		this.updatearea.sendKeys(area);
		this.updatezipcode.clear();
		this.updatezipcode.sendKeys(zipcode);
		this.clickupdatebtn.click();
		String expectedalert=driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		String actualalert="Branch updated";
		if(expectedalert.toLowerCase().contains(actualalert.toLowerCase()))
		{
			Reporter.log(expectedalert,true);
			return true;
		}
		else
		{
			Reporter.log("Branch not updated",true);
			return false;
		}
		
	}
	

}
