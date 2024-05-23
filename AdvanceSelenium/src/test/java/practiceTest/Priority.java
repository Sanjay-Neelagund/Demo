package practiceTest;

import org.testng.annotations.Test;

public class Priority {
	@Test(priority = 0)
	public void createTest() {
		System.out.println("Create");
	}
	@Test(priority = 1)
	public void modifyTest() {
		System.out.println("Modify");
	}
	@Test(priority = 2)
	public void delteTest() {
		System.out.println("Delete");
	}

}
