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
import org.openqa.selenium.support.ui.Select;

public class CreateOrganizationWithIndustryTest {
	public static void main(String[] args) throws Throwable {
		FileInputStream fis=new FileInputStream("./src/test/resources/commonData.properties");
		Properties pro=new Properties();
		pro.load(fis);
		String browser = pro.getProperty("Browser");
		String url = pro.getProperty("URL");
		String userName = pro.getProperty("UserName");
		String password = pro.getProperty("Password");
		Random ran=new Random();
		int num=ran.nextInt(1000);
		FileInputStream fis1=new FileInputStream("./src/test/resources/TestScriptData.xlsx");
		Workbook wb= WorkbookFactory.create(fis1);
		Sheet sh=wb.getSheet("Org");
		Row row=sh.getRow(4);
		String orgName = row.getCell(2).toString()+num;
		String industry = row.getCell(3).toString();
		String type = row.getCell(4).toString();
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
		WebElement industrysel = driver.findElement(By.name("industry"));
		Select sc=new Select(industrysel);
		sc.selectByVisibleText(industry);
		WebElement typesel=  driver.findElement(By.name("accounttype"));
		Select sc1=new Select(typesel);
		sc1.selectByVisibleText(type);
		driver.findElement(By.xpath("//input[contains(@value,'Save')]")).click();

		WebElement orgName1 = driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
		//WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(25));
		//wait.until(ExpectedConditions.visibilityOf(orgName1));
		String header = orgName1.getText();
		String indus=driver.findElement(By.id("dtlview_Industry")).getText();
		String actype=driver.findElement(By.id("dtlview_Type")).getText();

		if (indus.contains(industry)) {
			System.out.println(industry+"is created");
			
		}
		else {
			System.out.println(industry+"is not created");
		}
		if(actype.equals(type)) {
			System.out.println(type+"is created");

		}
		else {
			System.out.println(type+"is not created");

		}
		driver.findElement(By.xpath("(//td[@class='small']//img)[1]")).click();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		driver.quit();

	}

}
