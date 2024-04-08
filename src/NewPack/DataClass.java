package NewPack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.mysql.jdbc.Driver;

public class DataClass {

	public static boolean dbCheck(String username,String password) {
		
		Connection CON=null;
		PreparedStatement PMT=null;
		ResultSet RS=null;
		try {
			Driver db=new Driver();
			DriverManager.registerDriver(db);
//			Class.forName("com.mysql.cj.jdbc.Driver");
			String dburl="jdbc:mysql://localhost:3306/userpass?user=root&password=root";
			CON=DriverManager.getConnection(dburl);
		
			String querString="select * from information where username=?";
			PMT=CON.prepareStatement(querString);
			PMT.setString(1, username);
			RS=PMT.executeQuery();
			if (RS.next())
			{				
				
				if (password.equals(RS.getString(2))) {
					return true;
				}
				else {
					return false;
				}
			}
			else {
				return false;
			}
			
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
		
		
	}
	
	
}
