package bikeShare.servlet.front;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bikeShare.common.RespondTool;
import bikeShare.common.Tools;
import bikeShare.dao.ActivityDaoImpl;
import bikeShare.dao.CategoryDaoImpl;
import bikeShare.dao.GoodDaoImpl;
import bikeShare.dao.ImagePathDaoimpl;
import bikeShare.dao.OrderDaoImpl;
import bikeShare.dao.UserDaoImpl;
import bikeShare.model.Activity;
import bikeShare.model.Category;
import bikeShare.model.Goods;
import bikeShare.model.ImagePath;
import bikeShare.model.Order;
import bikeShare.model.User;

/**
 * Servlet implementation class ShowAddOrder
 */
@WebServlet(description = "show order list", urlPatterns = { "/ShowAddOrder" })
public class ShowAddOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowAddOrder() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String flag = request.getParameter("flag");
		ImagePathDaoimpl ipdi = new ImagePathDaoimpl();
		GoodDaoImpl gdi = new GoodDaoImpl();
		OrderDaoImpl odi = new OrderDaoImpl();
		ActivityDaoImpl activityDaoImpl = new ActivityDaoImpl();
		CategoryDaoImpl cdi = new CategoryDaoImpl();
		UserDaoImpl udi = new UserDaoImpl();
		System.out.println("flag" + flag);
		if (flag.equals("addOrder")) {
			String goodsId = request.getParameter("goodsid");
			HttpSession session = request.getSession();
			User user;
			user = (User) session.getAttribute("user");
			if (user == null) {
				request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
				return;

			}

			Goods goods = gdi.queryGoodByPrimaryKey(goodsId);
			ImagePath imagePathList = ipdi.queryByImageId(goodsId);
			goods.setImgepath(imagePathList.getPath());
			Activity activity = activityDaoImpl.queryActivityByPramaryKey(goods.getActivityid() + "");
			goods.setActivity(activity);
			if (activity.getDiscount() != 1) {
				goods.setNewPrice(goods.getPrice() * activity.getDiscount());

			} else {
				goods.setNewPrice((float) (goods.getPrice()));
			}
			request.setAttribute("goods", goods);
			request.setAttribute("user", user);
			request.getRequestDispatcher("/WEB-INF/views/orderConfirm.jsp").forward(request, response);
		} else if (flag.equals("orderFinish")) {
			String goodsId = request.getParameter("goodsid");
			// String isPay = request.getParameter("isPay");
			HttpSession session = request.getSession();

			User user = (User) session.getAttribute("user");

			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = sf.format(new Date());
			String paras[] = { user.getUserid() + "", time, "0", goodsId };

			String paras2[] = { "0", goodsId };
			gdi.setGoodStatus(paras2);
			int a = odi.insertOrder(paras);
			int b = gdi.setGoodStatus(paras2);

			if (a + b == 2) {

				RespondTool.getNewsString(request, response, "Rent success", 100);
			} else {
				RespondTool.getNewsString(request, response, "Rent fail", 200);
			}

		}
		if (flag.equals("MyOrder")) {
			HttpSession session = request.getSession();

			User user = (User) session.getAttribute("user");
			if (user == null) {
				System.out.println("denglua shabi");
				request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
				return;

			}
			List<Order> orderList = odi.findOrderByUserId(user.getUserid() + "");

			for (Integer i = 0; i < orderList.size(); i++) {
				Order order = orderList.get(i);
				Goods goods = gdi.queryGoodByPrimaryKey(Integer.toString(order.getGoodid()));
//				System.out.println(goods.getGoodsid());
				if (goods == null ) {
					continue;
				}
				ImagePath imagePathList = ipdi.queryByImageId(Integer.toString(goods.getGoodsid()));
				if (imagePathList == null ) {
					continue;
				}
				goods.setImgepath(imagePathList.getPath());
				Activity activity = activityDaoImpl.queryActivityByPramaryKey(Integer.toString(goods.getActivityid()));
				goods.setActivity(activity);
				Category category = cdi.queryCategoryByPramryKey(Integer.toString(goods.getCategoryId()));
				goods.setCategory(category);
				order.setGood(goods);
				orderList.set(i, order);
			}
			request.setAttribute("orderList", orderList);
			request.setAttribute("user", user);
			request.getRequestDispatcher("/WEB-INF/views/list.jsp").forward(request, response);
		}
		if (flag.equals("returnCar")) {

			String orderid = request.getParameter("orderid");
			Order order = (Order) odi.findOrderByPramaryKey(orderid);
			User user = udi.queryUserByPramryKey(order.getUserid() + "");
			Goods goods = gdi.queryGoodByPrimaryKey(order.getGoodid() + "");
			ImagePath imagePathList = ipdi.queryByImageId(goods.getGoodsid() + "");
			goods.setImgepath(imagePathList.getPath());
			Activity activity = activityDaoImpl.queryActivityByPramaryKey(goods.getActivityid() + "");
			goods.setActivity(activity);

			int hour = Tools.dateDiff(order.getOrdertime(), new Date());

			// SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

			order.setRepaytime(now);

			order.setHour(hour);
			hour = hour > 10 ? 10 : hour;
			if (activity.getDiscount() != 1) {

				order.setNewprice(goods.getPrice() * activity.getDiscount() * hour);
				goods.setNewPrice(goods.getPrice() * activity.getDiscount());

			} 
				else {
				order.setNewprice((float) (goods.getPrice() * hour));
				goods.setNewPrice((float) goods.getPrice() * 1);
			}

			order.setOldprice((float) (goods.getPrice() * hour));
			Category category = cdi.queryCategoryByPramryKey(goods.getCategoryId() + "");
			goods.setCategory(category);
			order.setGood(goods);
			request.setAttribute("order", order);
			request.setAttribute("user", user);
			request.getRequestDispatcher("/WEB-INF/views/orderFinish.jsp").forward(request, response);

		}
		if (flag.equals("payReturnOrder")) {
			String orderid = request.getParameter("orderid");
			String userId = request.getParameter("userId");
			String totalold = request.getParameter("totalold");
			String isPay = request.getParameter("isPay");
			String repayTime = request.getParameter("repayTime");

			Float totalnew = Float.parseFloat(request.getParameter("totalnew"));
			User user = udi.queryUserByPramryKey(userId);
			if (totalnew > user.getWallet()) {
				RespondTool.getNewsString(request, response, "Please recharge", 200);

			} else {

				String paras[] = { totalold, totalnew + "", isPay, "1", repayTime, orderid };
				odi.UpdateOrderForPay(paras);

				Order order = odi.findOrderByPramaryKey(orderid);

				String paras2[] = { "1", order.getGoodid() + "" };
				gdi.setGoodStatus(paras2);
				if (odi.UpdateOrderForPay(paras) + gdi.setGoodStatus(paras2) == 2) {

					RespondTool.getNewsString(request, response, "Check out success", 100);
				} else {
					RespondTool.getNewsString(request, response, "Check out fail", 200);
				}

			}

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
