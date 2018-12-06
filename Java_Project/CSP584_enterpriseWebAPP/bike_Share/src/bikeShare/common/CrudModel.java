package bikeShare.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;





public class CrudModel {

	
	
	Connection  ct=null;
	PreparedStatement ps=null;
	ResultSet  rs=null;

	public  ResultSet queryAll(String sql){
		try {
			ct=new ConnDB().getConn();
			ps=ct.prepareStatement(sql);

			rs=ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();

		}finally{

			new ConnDB().close();
			
		}
		return rs;
	}
	
	

	public  ResultSet  queryExecute(String sql,String []paras){

		try {
			ct=new ConnDB().getConn();
			ps=ct.prepareStatement(sql);
			for (int i = 0; i<paras.length; i++) {
				ps.setString(i+1,paras[i]);
				
			}
			rs=ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{

			new ConnDB().close();
			
		}
		return rs;
	}
	

	public int updExecute(String sql,String []paras){

		int b=0;
		
		try {
		
			ct=new ConnDB().getConn();
			ps=ct.prepareStatement(sql);

			for (int i = 0; i<paras.length; i++) {
				
				ps.setString(i+1,paras[i]);
				
			}
		
			 b=ps.executeUpdate();

			
		} catch (Exception e) {
			e.printStackTrace();
		
	
			
			// TODO: handle exception
		}finally{
			
			new ConnDB().close();
		}
		System.out.println("模型nui的值"+b);
		return b;
		
	}
}
