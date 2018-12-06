package bikeShare.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bikeShare.common.ConnDB;
import bikeShare.common.CrudModel;
import bikeShare.model.Address;
import bikeShare.model.Comment;
import bikeShare.model.Goods;

public class CommentDaoImpl {

	Connection ct = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public List<Comment> queryCommentByGoodsId(String id) {

		ArrayList<Comment> clist = new ArrayList<Comment>();
		String sql = "select * from comment where goodsId=?";
		String paras[] = { id };
		CrudModel cmd = new CrudModel();
		rs = cmd.queryExecute(sql, paras);
		try {
			while (rs.next()) {

				Comment comment = new Comment();
				comment.setCommentid(rs.getInt(1));

				comment.setUserid(rs.getInt(2));
				comment.setGoodsid(rs.getInt(3));
				comment.setPoint(rs.getInt(4));
				comment.setContent(rs.getString(5));
				comment.setCommenttime(rs.getDate(6));
				clist.add(comment);
			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			new ConnDB().close();

		}

		return clist;

	}

	public Comment queryCommentByUserId(String id) {

		Comment comment = null;
		String sql = "select * from commment where userId=?";
		String paras[] = { id };
		CrudModel cmd = new CrudModel();
		rs = cmd.queryExecute(sql, paras);
		try {
			while (rs.next()) {

				comment = new Comment();
				comment.setCommentid(rs.getInt(1));

				comment.setUserid(rs.getInt(2));
				comment.setGoodsid(rs.getInt(3));
				comment.setPoint(rs.getInt(4));
				comment.setContent(rs.getString(5));
				comment.setCommenttime(rs.getDate(6));
			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			new ConnDB().close();

		}

		return comment;

	}
	
//	public Comment insertComment(String id) {
//
//		Comment comment = null;
//		String sql = "INSERT INTO bike_sharing.comment (commentId, userId, goodsId, point, content, commentTime) VALUES (?, ?, ?, '0', 'nitamajiushigedashabi!', '2018-10-14 14:56:47');"
//		String paras[] = { id };
//		CrudModel cmd = new CrudModel();
//		rs = cmd.queryExecute(sql, paras);
//		try {
//			while (rs.next()) {
//
//				comment = new Comment();
//				comment.setCommentid(rs.getInt(1));
//
//				comment.setUserid(rs.getInt(2));
//				comment.setGoodsid(rs.getInt(3));
//				comment.setPoint(rs.getInt(4));
//				comment.setContent(rs.getString(5));
//				comment.setCommenttime(rs.getDate(6));
//			}
//
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		} finally {
//			new ConnDB().close();
//
//		}
//
//		return comment;
//
//	}
}
