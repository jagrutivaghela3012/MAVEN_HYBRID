package commonfunction;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

public class PBBranchCreation {
	WebDriver driver;
	public PBBranchCreation(WebDriver driver)
	{
		this.driver=driver;
	}
	@FindBy(xpath = "//input[@id='BtnNewBR']")
	WebElement newbranchbtn;
	@FindBy(xpath = "//input[@id='txtbName']")
	WebElement enterbranchname;
	@FindBy(xpath = "(//input[@id='txtAdd1'])[1]")
	WebElement enteraddress1;
	@FindBy(xpath = "(//input[@id='Txtadd2'])[1]")
	WebElement enteraddress2;
	@FindBy(xpath = "(//input[@id='txtadd3'])[1]")
	WebElement enteraddress3;
	@FindBy(xpath = "//input[@id='txtArea']")
	WebElement enterarea;
	@FindBy(xpath = "//input[@id='txtZip']")
	WebElement enterzipcode;
	@FindBy(xpath = "//select[@id='lst_counrtyU']")
	WebElement entercountry;
	@FindBy(xpath = "//select[@id='lst_stateI']")
	WebElement enterstate;
	@FindBy(xpath = "//select[@id='lst_cityI']")
	WebElement entercity;
	@FindBy(xpath = "//input[@id='btn_insert']")
	WebElement clicksubmitbtn;
	public boolean Verify_branchcreation(String branchname,String address1,String Address2,String address3,
			String area,String zipcode,String country,String state,String city)
{

		this.newbranchbtn.click();
		this.enterbranchname.sendKeys(branchname);
		this.enteraddress1.sendKeys(address1);
		this.enteraddress2.sendKeys(Address2);
		this.enteraddress3.sendKeys(address3);
		this.enterarea.sendKeys(area);
		this.enterzipcode.sendKeys(zipcode);
		new Select(entercountry).selectByVisibleText(country);
		new Select(enterstate).selectByVisibleText(state);
		new Select(entercity).selectByVisibleText(city);
		this.clicksubmitbtn.click();
		String ExpectedAlert=driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		String Actualalert="New Branch with id";
		if(ExpectedAlert.toLowerCase().contains(Actualalert.toLowerCase()))
		{
			Reporter.log(ExpectedAlert,true);
			return true;
		}
		else
		{
			Reporter.log("New branch not created",true);
			return false;
		}
}
	

}
