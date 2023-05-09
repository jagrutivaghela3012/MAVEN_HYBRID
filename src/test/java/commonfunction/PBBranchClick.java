package commonfunction;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PBBranchClick {
	@FindBy(xpath = "(//img)[5]")
	WebElement branchclick;
	public void branchclick()
	{
		branchclick.click();
	}

}
