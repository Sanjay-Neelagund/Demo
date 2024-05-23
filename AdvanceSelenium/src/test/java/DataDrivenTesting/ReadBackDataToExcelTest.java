package DataDrivenTesting;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadBackDataToExcelTest {
	public static void main(String[] args) throws Throwable {
		FileInputStream fis=new FileInputStream("./src/test/resources/TestScriptData.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		Sheet s=wb.getSheet("Org");
		Row row=s.getRow(1);
		Cell cell=row.createCell(4);

		cell.setCellType(CellType.STRING);
		cell.setCellValue("failed");
		FileOutputStream fos=new FileOutputStream("./src/test/resources/TestScriptData.xlsx");
		//Save the excel
		wb.write(fos);
		wb.close();
		System.out.println("Executed");



	}

}
