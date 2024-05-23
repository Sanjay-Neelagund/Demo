package practiceTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class sampleDPTest {
	
	@Test
	public void getProductInfo(String brandName,String productName)
	{
		WebDriver driver =new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.amazon.in/");
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandName,Keys.ENTER);
		String pPrice= driver.findElement(By.xpath("//span[text()='"+productName+"']/../../../../div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/a[1]/span[1]/span[1]")).getText();
		
		System.out.println(pPrice);
	}
	@DataProvider
	public Object[][] getProductDetails() throws Throwable{
		FileInputStream fis=new FileInputStream("./src/test/resources/TestScriptData.xlsx");
		Workbook wb= WorkbookFactory.create(fis);
		int rowCount=wb.getSheet("Product").getLastRowNum();
		Object[][] objArr=new Object[rowCount][2]; 
		
		for (int i = 0; i < rowCount; i++) {
			
			objArr[i][0]="hj";
			
		}
		return objArr;
	}

}
