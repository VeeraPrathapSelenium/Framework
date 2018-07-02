package com.GenericMethods;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.ParsingExcel.ReadExcel;

public class GenericMethods extends ReadExcel{
	
	public static WebDriver driver;
	
	public static 	Properties property;
	
	public static boolean launch_Application(String browser)
	{
		boolean status=true;
	try
	{
		
		switch (browser.toLowerCase()) {
		case "ff":
			driver=new FirefoxDriver();
			break;
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			driver=new ChromeDriver();
			break;
		default:
			break;
		}
		
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		//Enter credentials
		
		try
		{
			driver.findElement(By.xpath(property.getProperty("Edi_Login_Username"))).sendKeys(username);
			driver.findElement(By.xpath(property.getProperty("Edi_Login_Password"))).sendKeys(password);
			driver.findElement(By.xpath(property.getProperty("Btn_Login_Signin"))).click();
		}
		catch(Exception e)
		{
			System.out.println("Exception occured while entering credentials");
			
		}
		
		
		
	}
	catch(Exception e)
	{
		status=false;
		System.out.println(e.getMessage());
	}
		return status;
	}
	
	public static void loadPropertiesFile()
	{
		try
		{
			String propertiesfile=System.getProperty("user.dir")+"\\ObjectRepository.properties";
			File f=new File(propertiesfile);
			
			FileInputStream fis=new FileInputStream(f);
			
			property=new Properties();
			
			property.load(fis);
			
			
		}catch(Exception e)
		{
			System.out.println("Exception occur in reading properties file");
			e.getStackTrace();
		}
	}

	
	public static boolean hoverAndClick(WebElement element)
	{boolean status=true;
		try
		{
			Actions acc=new Actions(driver);
			
			acc.moveToElement(element).click(element).build().perform();
			
		}catch(Exception e)
		{status=false;
			System.out.println("Exception occur while clicking the element "+element);
		}
		return status;
	}
	
	
	public static void setText(String xpath,String data)
	{
		boolean status=true;
		
		try
		{
			WebElement element=driver.findElement(By.xpath(xpath));
			element.click();
			element.clear();
			element.sendKeys(data);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}
