package practice.orgtest;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CreateOrganizationWithphonenumbertest {
	public static void main(String[] args) throws Throwable {
		FileInputStream fis=new FileInputStream("./src/test/resources/commonData.properties");
		Properties pro= new Properties();
		pro.load(fis);

		String browser=pro.getProperty("Browser");
		String url=pro.getProperty("URL");
		String userName=pro.getProperty("UserName");
		String password=pro.getProperty("Password");
		FileInputStream fis1=new FileInputStream("./src/test/resources/TestScriptData.xlsx");
		Workbook wb= WorkbookFactory.create(fis1);
		Sheet sh= wb.getSheet("Org");
		Row row=sh.getRow(7);
		Random ran=new Random();
		int num=ran.nextInt(1000);
		String orgName=row.getCell(2).toString()+num;
		String phone =row.getCell(3).toString();
		wb.close();
		WebDriver driver=null;
		if (browser.equals("chrome")) {
			driver=new ChromeDriver();
		}
		else if (browser.equals("edge")) {
			driver=new EdgeDriver();
		}
		else if (browser.equals("firefox")) {
			driver=new FirefoxDriver();
		}
		else  {
			driver=new ChromeDriver();
		}
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		driver.findElement(By.name("user_name")).sendKeys(userName);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		WebElement org = driver.findElement(By.linkText("Organizations"));
		org.isDisplayed();
		org.click();
		
		WebElement CreateOrganization = driver.findElement(By.xpath("//img[contains(@alt,\"Create Organization\")]"));
		CreateOrganization.click();
		WebElement OrganizationName =driver.findElement(By.xpath("//input[contains(@name,\"accountname\")]"));
		OrganizationName.sendKeys(orgName);
		driver.findElement(By.id("phone")).sendKeys(phone);
		driver.findElement(By.xpath("//input[contains(@value,'Save')]")).click();
		

		WebElement orgName1 = driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
		//WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(25));
		//wait.until(ExpectedConditions.visibilityOf(orgName1));
		String header = orgName1.getText();
		String phon=driver.findElement(By.id("dtlview_Phone")).getText();
		if (phon.contains(phone)) {
			System.out.println(phone+"is created");
			
		}
		else {
			System.out.println(phone+"is not created");
		}
		driver.findElement(By.xpath("(//td[@class='small']//img)[1]")).click();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		driver.quit();

		

	}

}
	


