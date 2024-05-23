package DataDrivenTesting;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataFromExcel {
	public static void main(String[] args) throws Throwable, IOException {
		//Get the execel path loaction
		FileInputStream fis =new FileInputStream("./src/test/resources/TestScriptData.xlsx");
	//Open Workbook in read mode
 	   Workbook wb=WorkbookFactory.create(fis);
//		//Get the control to sheet
//		Sheet s1=	wb.getSheet("Org");
//		//Get control to row
//		Row r1=s1.getRow(1);
//		//Get the control to cell and read the data
//		String data= r1.getCell(3).getStringCellValue();
//		//close the workbook
		String data=wb.getSheet("Org").getRow(1).getCell(3).getStringCellValue();
	System.out.println(data);
		wb.close();

	}

}
