package DataDrivenTesting;


	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;

	import com.mysql.jdbc.Driver;

	public class ExecuteSelectQueryDatabase {
		public static void main(String[] args) throws Throwable {
			//Load or register to database driver
			Driver driverRef =new Driver();
			DriverManager.registerDriver(driverRef);
			// connect to database
			Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
			System.out.println("===Done==");
			//Create sql statement
			Statement stat=	conn.createStatement();
			//Execute the select queries 
			ResultSet res=	stat.executeQuery("Select * from employees");
			while(res.next()) {
			System.out.println(res.getInt(1)+"\t"+ res.getString(2)+"\t"+res.getString(3)+"\t"+res.getString(4));
			//System.out.println(res.next());
			}

			//close the connection
			conn.close();
			



		}

	}
