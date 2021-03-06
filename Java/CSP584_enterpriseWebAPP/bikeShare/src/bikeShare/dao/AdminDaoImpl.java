package bikeShare.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bikeShare.common.ConnDB;
import bikeShare.common.CrudModel;
import bikeShare.common.PageUtil;
import bikeShare.model.Admin;

public class AdminDaoImpl {
	Connection ct = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public boolean queryByAdminNamePass(String username, String password, String dept) {
		boolean b = false;
		String sql = "select * from bike_sharing.admin where adminName=? and password=? and dept=?";
		String paras[] = { username, password, dept };
		CrudModel cmd = new CrudModel();
		rs = cmd.queryExecute(sql, paras);
		try {

			if (rs.next()) {
				b = true;

			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return b;

	}

	public Admin getAdmintBean(String username) {
		Admin admin = null;
		String sql = "select * from admin where adminName=?";
		String paras[] = { username };
		CrudModel cmd = new CrudModel();
		rs = cmd.queryExecute(sql, paras);
		try {
			while (rs.next()) {
				admin = new Admin();
				admin.setAdminid(rs.getInt(1));
				admin.setAdminname(rs.getString(2));
				admin.setPassword(rs.getString(3));
				admin.setDept(rs.getInt(4));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			new ConnDB().close();

		}

		return admin;

	}

	public PageUtil getAdminUserByPage(int pageNo, int pageSize) {

		ArrayList<Admin> ablist = new ArrayList<Admin>();

		CrudModel cmd = new CrudModel();

		PageUtil page = null;
		try {
			int totalCount = 0;
			String sql = "select  count(*) from admin ";
			rs = cmd.queryAll(sql);
			while (rs.next()) {
				totalCount = rs.getInt(1);
			}
			String sql2 = "select * from  admin limit " + ((pageNo - 1) * pageSize) + "," + pageSize;
			rs = cmd.queryAll(sql2);
			while (rs.next()) {
				Admin admin = new Admin();
				admin.setAdminid(rs.getInt(1));
				admin.setAdminname(rs.getString(2));
				admin.setPassword(rs.getString(3));
				admin.setDept(rs.getInt(4));
				ablist.add(admin);
			}
			page = new PageUtil(pageSize, totalCount);
			page.setData(ablist);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return page;
	}

	public int deleteAdmin(String id) {
		int i = 0;
		String paras[] = { id };
		String sql = "delete from admin where adminId=?";
		CrudModel cmd = new CrudModel();
		i = cmd.updExecute(sql, paras);

		return i;

	}

}
