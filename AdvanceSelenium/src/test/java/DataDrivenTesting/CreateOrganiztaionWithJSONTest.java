package DataDrivenTesting;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.time.Duration;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateOrganiztaionWithJSONTest {
	public static void main(String[] args) throws Throwable {
		JSONParser js=new JSONParser();
		Object obj=	js.parse(new FileReader("./src/test/resources/CommonData.json"));
		JSONObject map=(JSONObject) obj;
		String browser = map.get("Browser").toString();
		String url = map.get("URL").toString();
		String usertName = map.get("UserName").toString();
		String password = map.get("Password").toString();
		FileInputStream fis1=new FileInputStream("./src/test/resources/TestScriptData.xlsx");
		Workbook wb=WorkbookFactory.create(fis1);
		Random ran=new Random();
		int ranNum=	ran.nextInt(1000);
		String orgname=wb.getSheet("Org").getRow(1).getCell(2).toString()+ ranNum;

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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get(url);
		driver.findElement(By.name("user_name")).sendKeys(usertName);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		WebElement org = driver.findElement(By.linkText("Organizations"));
		org.isDisplayed();
		org.click();
		WebElement CreateOrganization = driver.findElement(By.xpath("//img[contains(@alt,\"Create Organization\")]"));
		CreateOrganization.click();
		WebElement OrganizationName =driver.findElement(By.xpath("//input[contains(@name,\"accountname\")]"));
		OrganizationName.sendKeys(orgname);
		driver.findElement(By.xpath("//input[contains(@value,'Save')]")).click();
		//Thread.sleep(2000);

		WebElement orgName = driver.findElement(By.xpath("//td//a[contains(text(),\"Organizations\") and @class=\"hdrLink\"]/../../../../..//span[@class=\"dvHeaderText\"]"));
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(orgName));
		String orgn = orgName.getText();

		Cell c = wb.getSheet("Org").getRow(1).createCell(4);
		c.setCellType(CellType.STRING);


		try {
			if(orgn.contains(orgname)) {
				c.setCellValue("Pass");
				System.out.println("Pass");
			}
			else {
				c.setCellValue("Fail");
				System.out.println("Fail");
			}

		}catch (Exception e) {
		}
		FileOutputStream fos=new FileOutputStream("./src/test/resources/TestScriptData.xlsx");
		wb.write(fos);
		wb.close();

		Actions ac=new Actions(driver);
		ac.moveToElement(driver.findElement(By.xpath("(//td[@class='small']//img)[1]"))).perform();
		//driver.findElement(By.xpath("(//td[@class='small']//img)[1]")).click();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		driver.quit();

	}



}
