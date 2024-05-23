package DataDrivenTesting;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadMultipleDataFromExcelTest {
	public static void main(String[] args) throws Throwable {
		FileInputStream fis=new FileInputStream("./src/test/resources/TestScriptData.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		Sheet s=wb.getSheet("Product");
		
		System.out.println(s.getLastRowNum());
		for(int i=1;i<= s.getLastRowNum();i++){
			Row row=s.getRow(i);
		String data=row.getCell(0).toString();
		String data1=row.getCell(1).toString();
		System.out.println(data +" "+ data1);
		}
		
		
	}

}
