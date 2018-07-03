package com.ReportsGeneration;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.GenericMethods.GenericMethods;
import com.ParsingExcel.ReadExcel;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;



public class GenerateReports {
	
public static ExtentReports extent;
	
public static ExtentTest test;

public static String resultpath;
	
	public static String create_ResultFolder()
	{	SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		String path=System.getProperty("user.dir")+"\\Results\\"+sdf.format(new Date())+"\\"+ReadExcel.crnttestcase;
				
		File f=new File(path);
		
		if(!f.exists())
		{
			
			f.mkdirs();	
			
		}
		resultpath=path;
		return path;		
	}
	
	
	
	public static void intializeReport()
	{
		//report intialization
				extent=new ExtentReports(create_ResultFolder()+"\\"+ReadExcel.crnttestcase+".html");
				extent.addSystemInfo("Environment","QA")
				.addSystemInfo("Sprint","Sprint 32");				
	}
	
	
	public static void startTest(String keyword)
	{
		//Start the test
		
				test=extent.startTest(keyword);
	}
	
	public static void logEvent(String status,String description)
	{
		
		switch (status.toLowerCase()) {
		case "pass":
			test.log(LogStatus.PASS, description);
			break;
		case "fail":
			test.log(LogStatus.FAIL, description);
			break;
		case "warning":
			test.log(LogStatus.WARNING, description);
			break;

		default:
			break;
		}
	}
	
	public static void endTest()
	{
		extent.endTest(test);
	}
	
	public static void flushReport()
	{
		//flush report
		
				extent.flush();
				
				//close your report
				
				extent.close();
	}
	
	
public static void takescreenprint()
{
	try
	{
	TakesScreenshot sht=(TakesScreenshot)GenericMethods.driver;
	
	File src=sht.getScreenshotAs(OutputType.FILE);
	
	String dest=resultpath+"\\mypic.png";
	
	
	org.apache.commons.io.FileUtils.copyFile(src, new File(dest));
	
	}
	catch(Exception e)
	{
		System.out.println(e.getMessage());
	}
	
	
}
	

}
