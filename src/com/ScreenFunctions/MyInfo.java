package com.ScreenFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.GenericMethods.GenericMethods;

public class MyInfo extends GenericMethods{
	
	
	public static boolean navigateTo_MyInfo()
	{
		boolean status=true;
		
		try
		{
			WebElement element=driver.findElement(By.xpath(property.getProperty("Lnk_MyInfo_Myinfo")));
			
			status=hoverAndClick(element);
			
			if(status)
			{
				System.out.println("My Info is clicked sucessfully");
			}
			else
			{
				System.out.println("My Info is not clicked sucessfully");
			}
		}catch(Exception e)
		{
			
		}
		
		return status;
		
	}

	
	public static boolean enter_PersonalDetails()
	{
		boolean status =true;
		try
		{
		//click on the Edit button
			
			status=click_Edit();
			if(status)
			{
				//Enter the personal details
				String firstname=getData(crntkeyword,crnttestcase,"FirstName",crntiteration);	
				setText(property.getProperty("Edi_MyInfo_FirstName"),firstname);
				
				//Enter the MiddleName
				String middlename=getData(crntkeyword,crnttestcase,"MiddleName",crntiteration);	
				setText(property.getProperty("Edi_MyInfo_MiddleName"),middlename);
				
				
			}
		}catch(Exception e)
		{
			
		}
		return status;
	}
	
	
	public static boolean click_Edit()
	{
		boolean status=true;
		try
		{
			driver.findElement(By.xpath(property.getProperty("Btn_MyInfo_Edit"))).click();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			System.out.println("Unable to click on Edit button");
		}
		return status;
		
	}
	
}
