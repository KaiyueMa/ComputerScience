package bikeShare.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bikeShare.common.ConnDB;
import bikeShare.common.CrudModel;
import bikeShare.common.PageUtil;
import bikeShare.model.Activity;
import bikeShare.model.Admin;
import bikeShare.model.ImagePath;

public class ActivityDaoImpl {
	Connection ct = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public Activity queryActivityByPramaryKey(String id) {
		Activity activity = null;
		String sql = "select * from activity where activityId=?";
		String paras[] = { id };
		CrudModel cmd = new CrudModel();
		rs = cmd.queryExecute(sql, paras);
		try {
			while (rs.next()) {

				activity = new Activity();
				activity.setActivityid(rs.getInt(1));
				activity.setActivityname(rs.getString(2));
				
				activity.setActivitydes(rs.getString(3));
				
				activity.setDiscount(rs.getFloat(4));
			

				activity.setFullprice(rs.getInt(5));
				activity.setReduceprice(rs.getInt(6));
				activity.setFullnum(rs.getInt(7));
				activity.setReducenum(rs.getInt(8));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			new ConnDB().close();

		}
		return activity;
	}

	public int deleteActivy(String id) {
		int i = 0;
		String paras[] = { id };
		String sql = "delete from activity where activityId =?";
		CrudModel cmd = new CrudModel();
		i = cmd.updExecute(sql, paras);

		return i;
	}

	public PageUtil getActivityByPage(int pageNo, int pageSize) {

		ArrayList<Activity> ablist = new ArrayList<Activity>();

		CrudModel cmd = new CrudModel();

		PageUtil page = null;
		try {
			int totalCount = 0;
			String sql = "select  count(*) from activity ";
			rs = cmd.queryAll(sql);
			while (rs.next()) {
				totalCount = rs.getInt(1);
			}
			String sql2 = "select * from  activity limit " + ((pageNo - 1) * pageSize) + "," + pageSize;
			rs = cmd.queryAll(sql2);
			while (rs.next()) {

				Activity activity = new Activity();
				activity.setActivityid(rs.getInt(1));
				activity.setActivityname(rs.getString(2));

				activity.setActivitydes(rs.getString(3));
		
				activity.setDiscount(rs.getFloat(4));
			
				activity.setFullprice(rs.getInt(5));
				activity.setReduceprice(rs.getInt(6));
				activity.setFullnum(rs.getInt(7));
				activity.setReducenum(rs.getInt(8));
				ablist.add(activity);
			}
			page = new PageUtil(pageSize, totalCount);
			page.setData(ablist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

	public List<Activity> selectALLActivity() {

		List<Activity> alist = new ArrayList<Activity>();
		String sql = "select * from activity ";

		CrudModel cmd = new CrudModel();
		rs = cmd.queryAll(sql);
		try {
			while (rs.next()) {

				Activity activity = new Activity();
				activity.setActivityid(rs.getInt(1));
				activity.setActivityname(rs.getString(2));
		
				activity.setActivitydes(rs.getString(3));
		
				activity.setDiscount(rs.getFloat(4));
			
				activity.setFullprice(rs.getInt(5));
				activity.setReduceprice(rs.getInt(6));
				activity.setFullnum(rs.getInt(7));
				activity.setReducenum(rs.getInt(8));
				alist.add(activity);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			new ConnDB().close();

		}
		return alist;
	}

	public int insertActivity(String paras[]) {
		int i = 0;

		String sql = "insert into activity(activityName, activityDes, discount, fullPrice, reducePrice, fullNum, reduceNum) values(?,?,?,?,?,?,?)";
		CrudModel cmd = new CrudModel();
		i = cmd.updExecute(sql, paras);

		return i;
	}
}
