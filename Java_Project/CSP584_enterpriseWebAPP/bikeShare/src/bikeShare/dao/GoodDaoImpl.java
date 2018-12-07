package bikeShare.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bikeShare.common.ConnDB;
import bikeShare.common.CrudModel;
import bikeShare.common.PageUtil;
import bikeShare.model.Goods;
import bikeShare.model.ImagePath;

public class GoodDaoImpl {

	Connection ct = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public List<Goods> queryAllGood() {
		ArrayList<Goods> ablist = new ArrayList<Goods>();
		String sql = "select * from goods ";
		CrudModel cmd = new CrudModel();
		rs = cmd.queryAll(sql);

		try {
			while (rs.next()) {
				Goods good = new Goods();
				good.setGoodsid(rs.getInt(1));
				good.setGoodsname(rs.getNString(2));
				good.setUptime(rs.getTime(3));
				good.setCategoryId(rs.getInt(4));
				good.setDescription(rs.getNString(5));
				good.setActivityid(rs.getInt(6));
				good.setPrice(rs.getInt(7));
				good.setNum(rs.getInt(8));
				good.setStatue(rs.getInt(9));
				good.setAddress(rs.getInt(10));
				ablist.add(good);
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			new ConnDB().close();
		}
		return ablist;
	}

	public Goods queryGoodByPrimaryKey(String id) {

		Goods good = null;
		String sql = "select * from goods where goodsId=?";
		String paras[] = { id };
		CrudModel cmd = new CrudModel();
		rs = cmd.queryExecute(sql, paras);

		try {

			while (rs.next()) {
				good = new Goods();
				good.setGoodsid(rs.getInt(1));
				good.setGoodsname(rs.getNString(2));
				good.setUptime(rs.getTime(3));
				good.setCategoryId(rs.getInt(4));
				good.setDescription(rs.getNString(5));
				good.setActivityid(rs.getInt(6));
				good.setPrice(rs.getInt(7));
				good.setNum(rs.getInt(8));
				good.setStatue(rs.getInt(9));
				good.setAddress(rs.getInt(10));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			new ConnDB().close();
		}

		return good;

	}

	public PageUtil getGoodsByPageNameLike(int pageNo, int pageSize, String paras[]) {

		ArrayList<Goods> ablist = new ArrayList<Goods>();

		CrudModel cmd = new CrudModel();

		PageUtil page = null;
		try {
			int totalCount = 0;
			String sql = "select  count(*) from goods where goodsName  like ?";
			rs = cmd.queryExecute(sql, paras);
			while (rs.next()) {
				totalCount = rs.getInt(1);
			}
			String sql2 = "select * from  goods where  goodsName like ?  limit " + ((pageNo - 1) * pageSize) + ","
					+ pageSize;
			rs = cmd.queryExecute(sql2, paras);
			while (rs.next()) {
				Goods good = new Goods();
				good.setGoodsid(rs.getInt(1));
				good.setGoodsname(rs.getNString(2));
				good.setUptime(rs.getTime(3));
				good.setCategoryId(rs.getInt(4));
				good.setDescription(rs.getNString(5));
				good.setActivityid(rs.getInt(6));
				good.setPrice(rs.getInt(7));
				good.setNum(rs.getInt(8));
				good.setStatue(rs.getInt(9));
				good.setAddress(rs.getInt(10));
				ablist.add(good);
			}
			page = new PageUtil(pageSize, totalCount);
			page.setData(ablist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

	public PageUtil getGoodsByPage(int pageNo, int pageSize) {

		ArrayList<Goods> ablist = new ArrayList<Goods>();

		CrudModel cmd = new CrudModel();

		PageUtil page = null;
		try {
			int totalCount = 0;
			String sql = "select  count(*) from goods where statue=1";
			rs = cmd.queryAll(sql);
			while (rs.next()) {
				totalCount = rs.getInt(1);
			}
			String sql2 = "select * from  goods where  statue=1  limit " + ((pageNo - 1) * pageSize) + "," + pageSize;
			rs = cmd.queryAll(sql2);
			while (rs.next()) {
				Goods good = new Goods();
				good.setGoodsid(rs.getInt(1));
				good.setGoodsname(rs.getNString(2));
				good.setUptime(rs.getTime(3));
				good.setCategoryId(rs.getInt(4));
				good.setDescription(rs.getNString(5));
				good.setActivityid(rs.getInt(6));
				good.setPrice(rs.getInt(7));
				good.setNum(rs.getInt(8));
				good.setStatue(rs.getInt(9));
				good.setAddress(rs.getInt(10));
				ablist.add(good);
			}
			page = new PageUtil(pageSize, totalCount);
			page.setData(ablist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

	public int setGoodStatus(String paras[]) {

		int i = 0;
		String sql = "update goods set statue =? where goodsId=?";
		CrudModel cmd = new CrudModel();
		i = cmd.updExecute(sql, paras);

		return i;
	}

	public int updateGoods(String paras[]) {

		int i = 0;
		String sql = "update goods set goodsName=?, upTime=?, category=?, description=?, "
				+ "activityId=?,  price=?, num=?, statue=?, address=? where goodsId=?";
		CrudModel cmd = new CrudModel();
		i = cmd.updExecute(sql, paras);

		return i;
	}

	public int deleteGoods(String paras[]) {

		int i = 0;
		String sql = "delete from goods where goodsId=?";
		CrudModel cmd = new CrudModel();
		i = cmd.updExecute(sql, paras);

		return i;
	}

	public int insertGoodsInfo(String paras[]) {

		int i = 0;
		String sql = "insert into goods(goodsName, upTime,category,description,activityId,price,num,address) values(?,?,?,?,?,?,?,?)";
		CrudModel cmd = new CrudModel();
		i = cmd.updExecute(sql, paras);

		return i;

	}

	public int queryLastGoodsId() {

		int lastId = 0;
		String sql = "SELECT MAX(goodsId) FROM goods;";
		CrudModel cmd = new CrudModel();
		rs = cmd.queryAll(sql);
		try {
			while (rs.next()) {
				lastId = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return lastId;

	}

}
