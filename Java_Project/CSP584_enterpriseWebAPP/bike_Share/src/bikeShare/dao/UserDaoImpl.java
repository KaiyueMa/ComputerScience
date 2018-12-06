package bikeShare.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

import bikeShare.common.ConnDB;
import bikeShare.common.CrudModel;

import bikeShare.model.User;

public class UserDaoImpl {

	Connection ct = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public User queryUserByPramryKey(String id) {

		User user = null;
		String sql = "select * from user where userId=?";
		String paras[] = { id };
		CrudModel cmd = new CrudModel();
		rs = cmd.queryExecute(sql, paras);
		try {
			while (rs.next()) {

				user = new User();
				user.setUserid(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setRegtime(rs.getDate(4));
				user.setEmail(rs.getString(5));
				user.setTelephone(rs.getString(6));
				user.setWallet(rs.getFloat(7));
				user.setSecuritycash(rs.getInt(8));
				user.setPaytime(rs.getDate(9));

			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			new ConnDB().close();

		}

		return user;

	}

	public boolean queryByClientNamePass(String username, String password) {
		boolean b = false;
		String sql = "select * from user where username=? and password=?";
		String paras[] = { username, password };
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

	public User getClientBean(String username) {
		User user = null;
		String sql = "select * from user where username=?";
		String paras[] = { username };
		CrudModel cmd = new CrudModel();
		rs = cmd.queryExecute(sql, paras);
		try {
			if (rs.next()) {
				user = new User();
				user.setUserid(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setRegtime(rs.getDate(4));
				user.setEmail(rs.getString(5));
				user.setTelephone(rs.getString(6));
				user.setWallet(rs.getFloat(7));
				user.setSecuritycash(rs.getInt(8));
				user.setPaytime(rs.getDate(9));
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			new ConnDB().close();

		}

		return user;

	}

	public List<User> getClientByName(String username) {
		List<User> userList = new ArrayList<User>();
		String sql = "select * from user where username=?";
		String paras[] = { username };
		CrudModel cmd = new CrudModel();
		rs = cmd.queryExecute(sql, paras);
		try {
			while (rs.next()) {
				User user = new User();
				user.setUserid(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setRegtime(rs.getDate(4));
				user.setEmail(rs.getString(5));
				user.setTelephone(rs.getString(6));
				user.setWallet(rs.getFloat(7));
				user.setSecuritycash(rs.getInt(8));
				user.setPaytime(rs.getDate(9));
				userList.add(user);
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			new ConnDB().close();

		}

		return userList;

	}

	public int insertClient(String paras[]) {

		int i = 0;
		String sql = "insert into user(username,password,regTime,email,telephone) values(?,?,?,?,?)";
		CrudModel cmd = new CrudModel();
		i = cmd.updExecute(sql, paras);

		return i;
	}

	public int payCash(String paras[]) {
		int i = 0;
		String sql = "update user set wallet=?,securityCash=?,payTime=? where userId=?";
		CrudModel cmd = new CrudModel();
		i = cmd.updExecute(sql, paras);

		return i;
	}

	public int saveUserInformation(String paras[]) {
		int i = 0;
		String sql = "update user set email=?,telephone=? where userId=?";
		CrudModel cmd = new CrudModel();
		i = cmd.updExecute(sql, paras);
		return i;
	}

	// change password
	public int savePassWord(String paras[]) {
		int i = 0;
		String sql = "update user set password=? where userId=?";
		CrudModel cmd = new CrudModel();
		i = cmd.updExecute(sql, paras);
		return i;
	}

	public List<User> queryAllUser() {

		List<User> userList = new ArrayList<User>();
		String sql = "select * from user";

		CrudModel cmd = new CrudModel();
		rs = cmd.queryAll(sql);
		try {
			while (rs.next()) {
				User user = new User();
				user.setUserid(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setRegtime(rs.getDate(4));
				user.setEmail(rs.getString(5));
				user.setTelephone(rs.getString(6));
				user.setWallet(rs.getFloat(7));
				user.setSecuritycash(rs.getInt(8));
				user.setPaytime(rs.getDate(9));
				userList.add(user);
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			new ConnDB().close();
		}
		return userList;
	}

	public int deleteUser(String paras[]) {
		int i = 0;
		String sql = "delete from user where userId=?";
		CrudModel cmd = new CrudModel();
		i = cmd.updExecute(sql, paras);
		return i;
	}
}