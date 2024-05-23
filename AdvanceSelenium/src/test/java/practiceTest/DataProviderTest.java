package practiceTest;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

public class DataProviderTest {
	@Test(dataProvider = "getData")
	public void createTest(String FN,String Ln)
	{
		System.out.println(FN+Ln);
	}
	@DataProvider 
	public Object[][] getData() {
	Object[][] objArr=new Object[1][2];
	objArr[0][0]="sam";
	objArr[0][1]="hm";
	return objArr;
	}
	
	

}
