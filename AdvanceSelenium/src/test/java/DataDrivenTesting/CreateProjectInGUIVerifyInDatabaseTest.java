package DataDrivenTesting;

import static org.testng.Assert.fail;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;

public class CreateProjectInGUIVerifyInDatabaseTest {
	@Test
	public void createProject() throws Throwable {
		String browser = System.getProperty("Browser");
		String url = System.getProperty("URL");
		String usertName = System.getProperty("UserName");
		String password = System.getProperty("Password");
		String DBUrl=System.getProperty("DBURL");
		String DBun=System.getProperty("DBUN");
		String DBpsw=System.getProperty("DBPSW");
		boolean flag=false;
		
		WebDriver driver =null;
		if (browser.equals("chrome")) {
			driver=new ChromeDriver();
		}
		else if (browser.equals("edge")) {
			driver =new EdgeDriver();
		}
		else if (browser.equals("firefox")) {
			driver =new FirefoxDriver();
		}
		else {
			driver=new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.get(url);
		FileInputStream fis=new FileInputStream("./src/test/resources/TestScriptData.xlsx");
		Workbook wb= WorkbookFactory.create(fis);
		Sheet sh =wb.getSheet("Projects");
		Row row = sh.getRow(1);
		String projectName=row.getCell(0).toString();
		//String projectName="FB3";
		String projectManger=row.getCell(1).toString();
		

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(url);
		driver.findElement(By.name("username")).sendKeys(usertName);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();
		driver.findElement(By.linkText("Projects")).click();
		driver.findElement(By.xpath("//span[text()='Create Project']")).click();
		driver.findElement(By.name("projectName")).sendKeys(projectName);
		driver.findElement(By.name("createdBy")).sendKeys(projectManger);
		Select sel=new Select(driver.findElement(By.name("status")));
		sel.selectByIndex(0);
		
		driver.findElement(By.xpath("//input[@value='Add Project']")).click();
		
		Driver driverRef= new Driver();
		DriverManager.registerDriver(driverRef);
		Connection conn= DriverManager.getConnection(DBUrl, DBun, DBpsw);
		
		Statement stat= conn.createStatement();
		ResultSet res= stat.executeQuery("select * from employees");
		//String proj ="FB3";
		
		while (res.next()) {
			String proj =res.getString(1).toString();
			if(projectName.equals(proj)) {
				flag=true;
			}
			
		}
		if(flag==false) {
			System.out.println(projectName +"is not present in DB =====Failed====");
			//Assert.fail();
		}
		else {
			System.out.println(projectName+"Is present in DB ===Pass====");
		}
		conn.close();
		wb.close();
		driver.quit();

	}

}
