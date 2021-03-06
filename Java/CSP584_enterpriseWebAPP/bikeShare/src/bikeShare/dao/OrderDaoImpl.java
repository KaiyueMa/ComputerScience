package bikeShare.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bikeShare.common.ConnDB;
import bikeShare.common.CrudModel;
import bikeShare.model.Order;


public class OrderDaoImpl {
	
	Connection  ct=null;
	PreparedStatement ps=null;
	ResultSet  rs=null;
	
	public int insertOrder(String paras[]){

		int i=0;
		String sql="insert into indent(userId,orderTime,isSend,goodsId) values(?,?,?,?)";
		CrudModel  cmd=new CrudModel();
		i=cmd.updExecute(sql, paras);
		System.out.println("插入的order的值"+i);
		return i;
		
		
	}

	public  List<Order>  findOrderByUserId(String userId){
		System.out.println("传入的参数ID"+userId);
		List<Order> olist=new ArrayList<Order>();
		String sql="select * from indent where userId=?";
		String  paras[]={userId};
		CrudModel  cmd=new CrudModel();
		rs=cmd.queryExecute(sql, paras);	
	
		try {
			
		
			while(rs.next()){
			Order order=new Order();
			 order.setOrderid(rs.getInt(1));
			 order.setUserid(rs.getInt(2));
			 order.setOrdertime(rs.getDate(3));
			 order.setOldprice(rs.getFloat(4));
			 order.setNewprice(rs.getFloat(5));
			 order.setIspay(rs.getBoolean(6));
			 order.setIssend(rs.getBoolean(7));
			 order.setIsreceive(rs.getBoolean(8));
			 order.setIscomplete(rs.getBoolean(9));
			 order.setRepaytime(rs.getDate(10)+"");
			 order.setGoodid(rs.getInt(11));
			 olist.add(order);
				}
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{
			new ConnDB().close();
		}
		
			
		
		return olist;
	}
	
	
	public  Order  findOrderByPramaryKey(String Id){
		System.out.println("传入的参数ID"+Id);
	   Order order=null;
		String sql="select * from indent where orderId=?";
		String  paras[]={Id};
		CrudModel  cmd=new CrudModel();
		rs=cmd.queryExecute(sql, paras);	
	
		try {
			
		
			while(rs.next()){
		    order=new Order();
			 order.setOrderid(rs.getInt(1));
			 order.setUserid(rs.getInt(2));
			 order.setOrdertime(rs.getTimestamp("orderTime"));
			 order.setOldprice(rs.getFloat(4));
			 order.setNewprice(rs.getFloat(5));
			 order.setIspay(rs.getBoolean(6));
			 order.setIssend(rs.getBoolean(7));
			 order.setIsreceive(rs.getBoolean(8));
			 order.setIscomplete(rs.getBoolean(9));
			 order.setRepaytime(rs.getTimestamp("repayTime")+"");
			 order.setGoodid(rs.getInt(11));
		
				}
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{
			new ConnDB().close();
		}
		
			
		
		return order;
	}
	
	public int UpdateOrderForPay(String paras[]){
		
		int i=0;
		String sql="update indent set oldPrice =?,newPrice=? ,isPay=? ,isReceive=? ,repayTime=? where orderid=?";
		CrudModel  cmd=new CrudModel();
		i=cmd.updExecute(sql, paras);
		System.out.println("�������ֵ"+i);
		return i;
	}

	public  List<Order>  findOrderListByStatue(String  paras[],String sql){
	
		List<Order> olist=new ArrayList<Order>();
	
		
		CrudModel  cmd=new CrudModel();
		rs=cmd.queryExecute(sql, paras);	
	
		try {
			
		
			while(rs.next()){
			Order order=new Order();
			 order.setOrderid(rs.getInt(1));
			 order.setUserid(rs.getInt(2));
			 order.setOrdertime(rs.getDate(3));
			 order.setOldprice(rs.getFloat(4));
			 order.setNewprice(rs.getFloat(5));
			 order.setIspay(rs.getBoolean(6));
			 order.setIssend(rs.getBoolean(7));
			 order.setIsreceive(rs.getBoolean(8));
			 order.setIscomplete(rs.getBoolean(9));
			 order.setRepaytime(rs.getDate(10)+"");
			 order.setGoodid(rs.getInt(11));
			 olist.add(order);
				}
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{
			new ConnDB().close();
		}
		
			
		
		return olist;
	}
	
  public int SetOrderALlStatue(String paras[]){
	  
		int i=0;
		String sql="update indent set  isSend=? ,isReceive=? ,isComplete=? where orderid=?";
		CrudModel  cmd=new CrudModel();
		i=cmd.updExecute(sql, paras);
		System.out.println("�������ֵ"+i);
		return i; 
  }
}
