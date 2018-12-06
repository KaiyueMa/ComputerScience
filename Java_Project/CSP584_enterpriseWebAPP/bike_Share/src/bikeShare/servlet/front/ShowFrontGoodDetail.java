package bikeShare.servlet.front;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bikeShare.dao.ActivityDaoImpl;
import bikeShare.dao.AddressDaoImpl;
import bikeShare.dao.CategoryDaoImpl;
import bikeShare.dao.CommentDaoImpl;
import bikeShare.dao.GoodDaoImpl;
import bikeShare.dao.ImagePathDaoimpl;
import bikeShare.dao.UserDaoImpl;
import bikeShare.model.Activity;
import bikeShare.model.Address;
import bikeShare.model.Category;
import bikeShare.model.Comment;
import bikeShare.model.Goods;
import bikeShare.model.ImagePath;
import bikeShare.model.User;

/**
 * Servlet implementation class ShowFrontGoodDetail
 */
@WebServlet(description = "show product detail", urlPatterns = { "/ShowFrontGoodDetail" })
public class ShowFrontGoodDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowFrontGoodDetail() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String goodsid = request.getParameter("goodsid");
		Map<String, Object> goodsInfo = new HashMap<String, Object>();
		ImagePathDaoimpl ipdi = new ImagePathDaoimpl();
		GoodDaoImpl gdi = new GoodDaoImpl();
		AddressDaoImpl adi = new AddressDaoImpl();
		CategoryDaoImpl cdi = new CategoryDaoImpl();
		ActivityDaoImpl activityDaoImpl = new ActivityDaoImpl();
		CommentDaoImpl commentDao = new CommentDaoImpl();
		UserDaoImpl userdao = new UserDaoImpl();

		Goods goods = gdi.queryGoodByPrimaryKey(goodsid);
		Category cate = cdi.queryCategoryByPramryKey(goods.getCategoryId() + "");
		Address address = adi.queryAddressByPramryKey(goods.getAddress() + "");
		Activity activity = activityDaoImpl.queryActivityByPramaryKey(goods.getActivityid() + "");
//		goods.setActivitydesc();
		goods.setActivity(activity);
		ImagePath imagePathList = ipdi.queryByImageId(goods.getGoodsid().toString());
		goods.setImgepath(imagePathList.getPath());
		goodsInfo.put("goods", goods);
		goodsInfo.put("cate", cate);
		goodsInfo.put("image", imagePathList);
		goodsInfo.put("address", address);
		System.out.println(activity.getActivitydes());
		goodsInfo.put("activity", activity.getActivitydes());
		request.setAttribute("goodsInfo", goodsInfo);
		List<Comment> commentList = commentDao.queryCommentByGoodsId(goods.getGoodsid() + "");
		for (Integer i = 0; i < commentList.size(); i++) {
			Comment comment = commentList.get(i);
			User commentUser = userdao.queryUserByPramryKey(comment.getUserid() + "");
			comment.setUserName(commentUser.getUsername());
			commentList.set(i, comment);
		}
		request.setAttribute("commentList", commentList);
		request.getRequestDispatcher("/WEB-INF/views/detail.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
