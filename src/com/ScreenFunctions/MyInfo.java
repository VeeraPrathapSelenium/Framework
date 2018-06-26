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

}
