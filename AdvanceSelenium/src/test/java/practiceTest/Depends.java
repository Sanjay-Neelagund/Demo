package practiceTest;

import org.testng.annotations.Test;

public class Depends {
	@Test
	public void createTest() {
		System.out.println("Create");
	}
	@Test(dependsOnMethods = "createTest")
	public void modifyTest() {
		System.out.println("Modify");
	}
	@Test(dependsOnMethods = "createTest")
	public void deleteTest() {
		System.out.println("Delete");
	}
	
	}
	
