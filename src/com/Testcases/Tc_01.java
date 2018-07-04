package com.Testcases;

import java.io.IOException;

import org.openqa.selenium.By;

import com.GenericMethods.GenericMethods;
import com.ScreenFunctions.MyInfo;

public class Tc_01 extends GenericMethods{
	
	public static void Login()
	{
		launch_Application("ff");
	}
	
	public static void EdiMyinfo()
	{
		MyInfo info=new MyInfo();
		
		info.navigateTo_MyInfo();
		info.enter_PersonalDetails();
		//upload document
		driver.findElement(By.id("btnAddAttachment")).click();
		driver.findElement(By.id("ufile")).click();
		try {
			Runtime.getRuntime().exec("C:\\Users\\tm\\Desktop\\Fileupload.exe");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

}
