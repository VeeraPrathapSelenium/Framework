package com.Testcases;

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
	}
	

}
