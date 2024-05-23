package DataDrivenTesting;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateTheOrganizationTest {
	public static void main(String[] args) throws IOException, InterruptedException {
		FileInputStream fis =new FileInputStream("./src/test/resources/commonData.properties");
		Properties pro =new Properties();
		pro.load(fis);
		String browser = pro.getProperty("Browser");
		String url = pro.getProperty("URL");
		String usertName = pro.getProperty("UserName");
		String password = pro.getProperty("Password");

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


		driver.findElement(By.name("user_name")).sendKeys(usertName);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		WebElement org = driver.findElement(By.linkText("Organizations"));
		org.isDisplayed();

		org.click();
		WebElement CreateOrganization = driver.findElement(By.xpath("//img[contains(@alt,\"Create Organization\")]"));
		CreateOrganization.click();
		WebElement OrganizationName =driver.findElement(By.xpath("//input[contains(@name,\"accountname\")]"));
		OrganizationName.sendKeys("Ram");
		driver.findElement(By.xpath("//input[contains(@value,'Save')]")).click();
		Thread.sleep(2000);

		WebElement orgName = driver.findElement(By.xpath("//td//a[contains(text(),\"Organizations\") and @class=\"hdrLink\"]/../../../../..//span[@class=\"dvHeaderText\"]"));
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.visibilityOf(orgName));
		String orgn = orgName.getText();
		System.out.println(orgn);
		orgn.contains("Ram");
		driver.findElement(By.xpath("(//td[@class='small']//img)[1]")).click();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		driver.quit();


	}

}
