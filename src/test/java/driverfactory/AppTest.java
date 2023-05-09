package driverfactory;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import commonfunction.PBBranchClick;
import commonfunction.PBBranchCreation;
import commonfunction.PBBranchUpdation;
import commonfunction.PBLoginPage;
import commonfunction.PBLogoutPage;
import commonfunction.PBRoleClick;
import commonfunction.PBRoleCreation;
import commonfunction.PBRolesUpdation;
import config.AppUtil;
import utilities.ExcelFileUtil;

public class AppTest extends AppUtil  {
	String inputpath="./FileInput/DataEngine.xlsx";
	String outputpath="./FileOutput/HybridPBResult.xlsx";
	String TCsheet="TestCases";
	String TSsheet="TestSteps";
	@Test
	public void startTest()throws Throwable
	{
		boolean res=false;
		String tcres="";
		ExcelFileUtil xl=new ExcelFileUtil(inputpath);
		int TCCount=xl.rowCount(TCsheet);
		int TSCount=xl.rowCount(TSsheet);
		Reporter.log("no of rows in TCsheet ::"+TCCount+"no of rows in TSsheet::"+TSCount,true);
		//itreate rows in tcsheet
		for(int i=1;i<=TCCount;i++)
		{
			String Executionstatus=xl.getCellData(TCsheet, i, 2);
			if(Executionstatus.equalsIgnoreCase("Y"))
			{
				//read tcid cell
				String TCid=xl.getCellData(TCsheet, i, 0);
				//iterate all rows in TSsheet
				for(int j=1;j<=TSCount;j++)
				{
					//read tsid cell data
					String TSid=xl.getCellData(TSsheet, j, 0);
					if(TCid.equalsIgnoreCase(TSid))
					{
						//read keyword cell data
						String keyword=xl.getCellData(TSsheet, j, 3);
						if(keyword.equalsIgnoreCase("adminLogin"))
						{
							//call login page
							PBLoginPage login=PageFactory.initElements(driver, PBLoginPage.class);
							String username=xl.getCellData(TSsheet, j, 5);
							String password=xl.getCellData(TSsheet, j, 6);
							res=login.Verify_login(username, password);
						}
						else if(keyword.equalsIgnoreCase("branchCreate"))
						{
							PBBranchClick branchclick=PageFactory.initElements(driver, PBBranchClick.class);
							PBBranchCreation newbranch=PageFactory.initElements(driver, PBBranchCreation.class);
							String branchname=xl.getCellData(TSsheet, j, 5);
							String address1=xl.getCellData(TSsheet, j, 6);
							String address2=xl.getCellData(TSsheet, j, 7);
							String address3=xl.getCellData(TSsheet, j, 8);
							String area=xl.getCellData(TSsheet, j, 9);
							String zipcode=xl.getCellData(TSsheet, j, 10);
							String country=xl.getCellData(TSsheet, j, 11);
							String state=xl.getCellData(TSsheet, j, 12);
							String city=xl.getCellData(TSsheet, j, 13);
							branchclick.branchclick();
							res=newbranch.Verify_branchcreation(branchname, address1, address2, address3, area, zipcode, country, state, city);
						}
						else if(keyword.equalsIgnoreCase("branchUpdate"))
						{
							PBBranchClick branchclick=PageFactory.initElements(driver, PBBranchClick.class);
							PBBranchUpdation branchupdate=PageFactory.initElements(driver, PBBranchUpdation.class);
							String branchname=xl.getCellData(TSsheet, j, 5);
							String address1=xl.getCellData(TSsheet, j, 6);
							String area=xl.getCellData(TSsheet, j, 9);
							String zipcode=xl.getCellData(TSsheet,j, 10);
							branchclick.branchclick();
							res=branchupdate.verify_branchUpdation(branchname, address1, area, zipcode);
						}
						else if (keyword.equalsIgnoreCase("adminLogout"))
						{
							PBLogoutPage logout=PageFactory.initElements(driver, PBLogoutPage.class);
							res=logout.Verify_logout();
						}
						else if(keyword.equalsIgnoreCase("rolecreate"))
						{
							PBRoleClick roles=PageFactory.initElements(driver, PBRoleClick.class);
							PBRoleCreation rolecreate=PageFactory.initElements(driver, PBRoleCreation.class);
							String rolename=xl.getCellData(TSsheet, j, 5);
							String roledes=xl.getCellData(TSsheet, j, 6);
							String roletype=xl.getCellData(TSsheet, j, 7);
							roles.roleclick();
							res=rolecreate.verify_rolecreation(rolename,roledes, roletype);
						}
						else if(keyword.equalsIgnoreCase("RoleUpdate"))
						{
							PBRoleClick roles=PageFactory.initElements(driver, PBRoleClick.class);
							PBRolesUpdation roleupdate=PageFactory.initElements(driver, PBRolesUpdation.class);
							String rolename=xl.getCellData(TSsheet, j, 5);
							roles.roleclick();
							res=roleupdate.verify_roleupdation(rolename);
						}
						String tsres="";
						if(res)
						{
							tsres="Pass";
							xl.setCelldata(TSsheet, j, 4,tsres,outputpath);
						}
						else
						{
							tsres="Fail";
							xl.setCelldata(TSsheet, j, 4, tsres, outputpath);
						}
						tcres=tsres;
					}

				}
				xl.setCelldata(TCsheet, i, 3, tcres, outputpath);
			}
			else
			{
				//write blocked in status column which executionstatus flaged N
				xl.setCelldata(TCsheet, i, 3, "Blocked", outputpath);
			}
		}
	}

}
