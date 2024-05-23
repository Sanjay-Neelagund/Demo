package DataDrivenTesting;

import static org.testng.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;

public class SimpleUnitTestJDBC {
	@Test
	public void CheckTest() throws Throwable {
		
		String execpetedEmp="FB3";
		Boolean flag=false;
		Connection conn=null;
		try {
		Driver driverRef =new Driver();
		DriverManager.registerDriver(driverRef);
		 conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
		Statement stat= conn.createStatement();
		ResultSet res= stat.executeQuery("Select * from employees");
		
		while (res.next()) {
			String actualEmp = res.getString(7);
			if (execpetedEmp.equals(actualEmp)) {
				flag=true;
				System.out.println(execpetedEmp+"Is present ===Pass===");	
			}	
		}
		} catch (Exception e) {
			System.out.println("Exception handled");
			
		}
		finally {
			System.out.println("==Connection closed===");
			conn.close();
		}
		if(flag==false) {
			System.out.println(execpetedEmp+"Is not present ===fail===");
			Assert.fail();
			
		}
		conn.close();
				
		}
}
