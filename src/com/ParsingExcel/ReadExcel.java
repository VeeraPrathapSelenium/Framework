package com.ParsingExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ReportsGeneration.GenerateReports;

/*
 * ClassName:ReadExcel
 * 
 * Purpose:This class will act like a central library to read the data from the test data workbook
 * 
 * Constructor:
 *
 * Access Specification:Always public
 * 
 * Dependency Jar Files:ApachePOI
 */



public class ReadExcel extends GenerateReports{
	
//********************* Variable Declarations ***********************

	public static String Testdatapath;
	
	public static XSSFWorkbook wrkbk;
	
	public static XSSFSheet sheet;
	
	public static String environment;
	
	public static String url;
	
	public static String username;
	
	public static String password;
	
	public static String crnttestcase;
	public static String description;
	public static String crntkeyword;
	public static String crntiteration;


//*******************************************************************	
	
/*
 *Method name:
 *Purpose:
 *InputParameters
 *Output:
 *Designed By:
 *Reviewed By: 
 */
 public static boolean getEnvironment_Details()
{
	boolean status=true;
	
	//get the test data path
	//System.out.println(System.getProperty("user.dir")+"\\TestData\\Testdata.xlsx");
	
	Testdatapath=System.getProperty("user.dir")+"\\TestData\\Testdata.xlsx";
	
	//Verify if the test data file is Exist else throw an exception
	
	File testdata_file=new File(Testdatapath);
	
	if(testdata_file.exists())
		
	{
		openExcel(); 
		
		//Read the Excel WorkBook
		
		int col_number=getColumn_Header_Position("CommonTestdata","Status");
		
		int rcnt=getRowCount("CommonTestdata");
		
		for(int r=1;r<=rcnt-1;r++){
			
			
			String exec_status=wrkbk.getSheet("CommonTestdata").getRow(r).getCell(col_number).getStringCellValue();
			
			if(exec_status.trim().toLowerCase().equals("yes"))
			{
				System.out.println("Fetching data");
				
				//Get environment
				col_number=getColumn_Header_Position("CommonTestdata","Environment");
				
				environment=wrkbk.getSheet("CommonTestdata").getRow(r).getCell(col_number).getStringCellValue();
				
				//Get Url
				col_number=getColumn_Header_Position("CommonTestdata","Url");
				
				url=wrkbk.getSheet("CommonTestdata").getRow(r).getCell(col_number).getStringCellValue();
				
				//Get UserName
				col_number=getColumn_Header_Position("CommonTestdata","Username");
				
				username=wrkbk.getSheet("CommonTestdata").getRow(r).getCell(col_number).getStringCellValue();
				
				//Get Password
				col_number=getColumn_Header_Position("CommonTestdata","Password");
				
				password=wrkbk.getSheet("CommonTestdata").getRow(r).getCell(col_number).getStringCellValue();
				
				
				System.out.println(environment+"***"+url+"***"+"username"+"***"+password);
				
			}
			
		}
			
		
	}
	
	else
	{
		try {
			throw new Exception("Testdata path is not Exist");
		}
		catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	
	return status;
}




public static int getRowCount(String sheetname)
{int rowcount=0;
	 sheet=wrkbk.getSheet(sheetname);
	 rowcount=sheet.getLastRowNum();
	 
	 return rowcount;
}

public static int  getColumnCount(String sheetname)
{
	int colcnt=0;
	 sheet=wrkbk.getSheet(sheetname);
	 colcnt=sheet.getRow(getRowCount(sheetname)).getLastCellNum();
	 
	 return colcnt;
}

public static int getColumn_Header_Position(String sheetname,String columnname) 
	
{
	
	
	int columnnumber=0;
	
	for(int i=0;i<getColumnCount(sheetname);i++)
	{
		
		String columndata=sheet.getRow(0).getCell(i).getStringCellValue();
		
		if(columndata.toLowerCase().equals(columnname.toLowerCase()))
		{
			columnnumber=i;
			break;
		}
				
	}
	
	return columnnumber;
	
}

public static int getRowNumber_Testcase_Position(String sheetname,String testcase,String itr)
{
	int rownumber=0;
	for(int i=0;i<=getRowCount(sheetname);i++)
	{
		String rowdata=sheet.getRow(i).getCell(0).getStringCellValue();
		
		String crntitr=sheet.getRow(i).getCell(2).getStringCellValue();
		if(rowdata.toLowerCase().equals(testcase.toLowerCase()) && crntitr.equals(itr))
		{
			rownumber=i;
			break;
		}
		
	}
	
	return rownumber;

	
}

public static String getData(String sheetname,String testcase,String columnname, String itr)
{
	
	String data="";
	
	int row=getRowNumber_Testcase_Position(sheetname,testcase,itr);
	
	if(!(row==0))
	{
		int colnmber=getColumn_Header_Position(sheetname,columnname);
		
		
		if(!(colnmber==0))
		{
			CellType celltype=sheet.getRow(row).getCell(colnmber).getCellTypeEnum();
			
			switch (celltype) {
			
			case NUMERIC:
				
				int numericdata=(int) sheet.getRow(row).getCell(colnmber).getNumericCellValue();					
				System.out.println(numericdata);
				
				
				
				break;
			case STRING:
				
				String rowdata=sheet.getRow(row).getCell(colnmber).getStringCellValue();
				
				System.out.println(rowdata);
				data=rowdata;
				break;

			default:
				break;
			}
			
			
		}
		
	}
	return data;
}


public static void openExcel() 
{
	File f=new File(Testdatapath);
	
	FileInputStream fis;
	try {
		fis = new FileInputStream(f);
		
		wrkbk=new XSSFWorkbook(fis);
		
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	
    

}
	
public static void read_EachTestcase()

{
	int rcnt=getRowCount("TestCases");
	
	for(int r=1;r<=rcnt;r++)
	{
		int cnt=getColumnCount("TestCases");
		
		crnttestcase=wrkbk.getSheet("TestCases").getRow(r).getCell(0).getStringCellValue();
		
		description=wrkbk.getSheet("TestCases").getRow(r).getCell(1).getStringCellValue();
		
		String execute=wrkbk.getSheet("TestCases").getRow(r).getCell(2).getStringCellValue();
		
		if(!(crnttestcase.isEmpty()) && execute.trim().toLowerCase().equals("yes"))
		{
			//create the result folder based on the current test case
			create_ResultFolder();
			
			//intialize the result report on test case name
			
			intializeReport();
			
			
			for(int c=3;c<=cnt;c++)
			{
				
				
				String keyword=wrkbk.getSheet("TestCases").getRow(r).getCell(c).getStringCellValue();
				
				if(!(keyword.isEmpty()))
				{
					try {
					Class cls=Class.forName(crnttestcase);
					
					// get data based on iteration
					
					//verify if the "_" is available for the keyword
					if(! keyword.contains("_"))
					{
						startTest(keyword);
						Method m=cls.getDeclaredMethod(keyword, null);
						
						m.invoke(m);
					}
					else
					{
						
						
						crntkeyword=keyword.split("_")[0];
						startTest(crntkeyword);
						crntiteration=keyword.split("_")[1];
						Method m=cls.getDeclaredMethod(crntkeyword, null);
						
						m.invoke(m);
					}
					
					
					
					} catch (Exception e) {
						
						System.out.println("Exception occur in executing the test case "+crnttestcase);
						e.printStackTrace();
					} 
					
					endTest();
					
				}else
				{
					break;
				}
				
				
			}
			
			flushReport();
			
		}
		else
		{
			break;
		}
			
		
		
		
		
		
		
	}
	
	
	
}

}
