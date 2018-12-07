package bikeShare.common;
import java.sql.*;


public class ConnDB {
	private Connection ct=null;
	private Statement sm=null;
	private ResultSet rs=null;
	String driverName="com.mysql.jdbc.Driver";
	String url="jdbc:mysql://****";
	String user="****";
	String passwd="****";	
	public Connection getConn(){
		
		try {
			Class.forName(driverName);
			ct=DriverManager.getConnection(url,user,passwd);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return ct;
	}
	
	public void close(){
		
		try {
			if(rs!=null){
			rs.close();
		}
		if(sm!=null){
			sm.close();
		}
		if(ct!=null){
			ct.close();
		}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

}
