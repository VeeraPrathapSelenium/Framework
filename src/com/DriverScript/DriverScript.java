package com.DriverScript;

import com.GenericMethods.GenericMethods;
import com.ParsingExcel.ReadExcel;

public class DriverScript extends GenericMethods {

	public static void main(String[] args) {
		
		loadPropertiesFile();
		ReadExcel exl=new ReadExcel();
		exl.getEnvironment_Details();
		exl.read_EachTestcase();
		

	}

}
