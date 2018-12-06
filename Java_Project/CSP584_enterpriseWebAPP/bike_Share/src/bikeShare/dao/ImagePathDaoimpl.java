
package bikeShare.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



import bikeShare.common.ConnDB;
import bikeShare.common.CrudModel;
import bikeShare.model.ImagePath;

public class ImagePathDaoimpl {

	Connection  ct=null;
	PreparedStatement ps=null;
	ResultSet  rs=null;
	
	//按用户名查询
	public ImagePath queryByImageId(String goodsId){
	ImagePath image=null;
	String sql="select * from imagepath where goodId=?";
	String  paras[]={goodsId};
	CrudModel  cmd=new CrudModel();
	rs=cmd.queryExecute(sql, paras);
	try {
		while(rs.next()){
			
			image=new ImagePath();
			image.setGoodid(rs.getInt(2));
			image.setPathid(rs.getInt(1));
			image.setPath(rs.getString(3));
		
		}
		
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}finally{
		new ConnDB().close();
		
	}
	
	return image;
}
	public int insertImagePath(String paras[]){
		 
		int i=0;
		String sql="insert into imagepath(goodId,path) values(?,?)";
		CrudModel  cmd=new CrudModel();
		i=cmd.updExecute(sql, paras);
		System.out.println("�������ֵ"+i);
		return i;
	 
 }
 
}