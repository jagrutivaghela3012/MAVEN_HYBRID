package commonfunction;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PBRoleClick {
	@FindBy(xpath = "(//img)[6]")
	WebElement clickrolebtn;
	public void roleclick()
	{
		clickrolebtn.click();
	}

}
