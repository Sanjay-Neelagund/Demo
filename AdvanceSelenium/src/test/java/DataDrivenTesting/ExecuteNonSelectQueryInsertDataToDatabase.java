package DataDrivenTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class ExecuteNonSelectQueryInsertDataToDatabase {
	public static void main(String[] args) throws Throwable {
		//Load or regester to database driver
		Driver driverRef =new Driver();
		DriverManager.registerDriver(driverRef);
		// connect to database
		Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
		System.out.println("===Done==");
		//Create sql statement
		Statement stat=	conn.createStatement();
		//Execute the noselect quaries 
		for (int i = 3; i < 10; i++) {
			int res=	stat.executeUpdate("insert into employees values('"+i+"','FB"+i+"','Demo',100);");
			System.out.println(res);
		}
		//close the connection
		conn.close();

	}

}
