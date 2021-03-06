package bikeShare.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bikeShare.common.ConnDB;
import bikeShare.common.CrudModel;
import bikeShare.model.Category;

public class CategoryDaoImpl {
	Connection  ct=null;
	PreparedStatement ps=null;
	ResultSet  rs=null;

	
	public Category queryCategoryByPramryKey(String  id){
			
		Category cate=null;
		String sql="select * from category where cateId=?";
		String  paras[]={id};
		CrudModel  cmd=new CrudModel();
		rs=cmd.queryExecute(sql, paras);
		try {
			while(rs.next()){	
				cate=new Category();
				cate.setCateid(rs.getInt(1));
				cate.setCatename(rs.getString(2));		
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			new ConnDB().close();
			
		}
		
		return cate;
		
	}
	 
	public List<Category> queryListCategory(){
		
		ArrayList<Category> ablist=new ArrayList<Category>();
		String sql="select * from category ";
		CrudModel  cmd=new CrudModel();
		rs=cmd.queryAll(sql);
		try {
			while(rs.next()){
				Category cate=new Category();
				cate.setCateid(rs.getInt(1));
				cate.setCatename(rs.getString(2));		
				ablist.add(cate);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			new ConnDB().close();
			
		}
		
		return ablist;
	
}
public int deleteCategory(String paras[]){
		
		int i=0;
		String sql="delete from category where cateId=?";
		CrudModel  cmd=new CrudModel();
		i=cmd.updExecute(sql, paras);
		System.out.println("�������ֵ"+i);
		return i;
	}

  public int updateCategory(String paras[]){
	
	int i=0;
	String sql="update  category set cateName=? where cateId=?";
	CrudModel  cmd=new CrudModel();
	i=cmd.updExecute(sql, paras);
	System.out.println("�������ֵ"+i);
	return i;
}
	
  public int  insertCateGory(String paras[]){
	  int i=0; 
	 
		String sql=" insert into category(cateName) values(?)";
		CrudModel  cmd=new CrudModel();
		i=cmd.updExecute(sql, paras);
		System.out.println("�������ֵ"+i);
		return i;
  }
  
	public boolean queryIsNUll(String cateName){
		 boolean b=false;
		 String sql="select * from category  where cateName=? ";
		String  paras[]={cateName};
		 CrudModel  cmd=new CrudModel();
		rs=cmd.queryExecute(sql, paras);
		 try {
			
			 if(rs.next()){
				 b=true;
				 
			 }
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return b;
		 
			
		}
  
}