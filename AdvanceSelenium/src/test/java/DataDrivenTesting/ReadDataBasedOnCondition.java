package DataDrivenTesting;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataBasedOnCondition {
	public static void main(String[] args) throws Throwable {
		FileInputStream fis=new FileInputStream("./src/test/resources/TestScriptData.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		Sheet s=wb.getSheet("Org");
		String ExecpectedTestId ="tc_200";
		String data1="";
		String data2="";
		String data3="";
		boolean flag=false;
		
		for(int i=0;i<=s.getLastRowNum();i++) {
			String data="";
			try {
		 data=s.getRow(i).getCell(0).toString();
		 if(ExecpectedTestId.equals(data)) {
			 flag=true;
			data1= s.getRow(i).getCell(1).toString();
			data2= s.getRow(i).getCell(2).toString();
			data3= s.getRow(i).getCell(3).toString();
		
		 }
		
			}catch (Exception e) {
			}
			//System.out.println(data);
		}
		if(flag==true) {
			System.out.println(data1);
			System.out.println(data2);
			System.out.println(data3);
			
		}
		else {
			System.out.println(ExecpectedTestId +" Data is not available");
		}
		
	}

}
