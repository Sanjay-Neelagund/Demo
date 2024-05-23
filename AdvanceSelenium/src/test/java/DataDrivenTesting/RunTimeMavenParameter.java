package DataDrivenTesting;

import org.testng.annotations.Test;

public class RunTimeMavenParameter {
	@Test
	public void testMaven() {
		System.out.println("test ng test");
		String url = System.getProperty("Url");
		String UN = System.getProperty("un");
		System.out.println(url);
		System.out.println(UN);
		
	}

}
