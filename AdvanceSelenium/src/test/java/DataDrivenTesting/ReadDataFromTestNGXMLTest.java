package DataDrivenTesting;

import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

public class ReadDataFromTestNGXMLTest {
@Test
public void ReadData(XmlTest test) {
	System.out.println(test.getParameter("URL"));
	System.out.println(test.getParameter("Browser"));
	System.out.println(test.getParameter("UserName"));
	System.out.println(test.getParameter("Password"));
	
	
}
	
}
