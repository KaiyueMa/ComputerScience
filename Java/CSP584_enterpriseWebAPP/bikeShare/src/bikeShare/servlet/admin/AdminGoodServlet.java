package bikeShare.servlet.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import bikeShare.common.PageUtil;
import bikeShare.common.RespondTool;
import bikeShare.dao.ActivityDaoImpl;
import bikeShare.dao.AddressDaoImpl;
import bikeShare.dao.CategoryDaoImpl;
import bikeShare.dao.GoodDaoImpl;
import bikeShare.dao.ImagePathDaoimpl;
import bikeShare.model.Activity;
import bikeShare.model.Address;
import bikeShare.model.Category;
import bikeShare.model.Goods;
import bikeShare.model.ImagePath;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Servlet implementation class AdminGoodServlet
 */
@WebServlet("/AdminGoodServlet")
public class AdminGoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminGoodServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String flag = request.getParameter("flag");

		AddressDaoImpl adi = new AddressDaoImpl();
		CategoryDaoImpl cdi = new CategoryDaoImpl();
		GoodDaoImpl gdi = new GoodDaoImpl();
		ActivityDaoImpl activityadi = new ActivityDaoImpl();
		ImagePathDaoimpl imagepathDao = new ImagePathDaoimpl();
		if (flag.equals("goodshow")) {
			List<Address> alist = adi.queryAddresslist();
			List<Category> clist = cdi.queryListCategory();
			List<Activity> activityList = activityadi.selectALLActivity();
			request.setAttribute("categoryList", clist);
			request.setAttribute("addressList", alist);
			request.setAttribute("activityList", activityList);
			request.getRequestDispatcher("/WEB-INF/views/adminAllGoods.jsp").forward(request, response);
		}

		if (flag.equals("showjson")) {
			String pn = request.getParameter("page");
			int pageNo = 1;
			if (pn != null) {
				pageNo = Integer.parseInt(pn);
				System.out.println("pageNow" + pageNo);
			}
			ImagePathDaoimpl ipdi = new ImagePathDaoimpl();

			PageUtil page = gdi.getGoodsByPage(pageNo, 5);
			page.setPageNo(pageNo);

			List<Goods> goodsList = (List<Goods>) page.getData();

			for (int i = 0; i < goodsList.size(); i++) {
				Goods goods = goodsList.get(i);
				if (goods.getGoodsname().length() > 11) {
					goods.setGoodsname(goods.getGoodsname().substring(0, 10) + "...");
				}

				ImagePath imagePathList = ipdi.queryByImageId(goods.getGoodsid().toString());
				goods.setImgepath(imagePathList.getPath());
				goodsList.set(i, goods);

			}

			JSONObject jsonObject = new JSONObject();
			if (null != goodsList) {

				jsonObject.put("data", goodsList);
				jsonObject.put("pageInfo", page);

			}

			String returnString = JSON.toJSONString(jsonObject);

			PrintWriter pw = null;
			try {
				response.setCharacterEncoding("UTF-8");
				pw = response.getWriter();
				pw.write(returnString);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (pw != null) {
					pw.close();
				}
			}

		}
		if (flag.equals("updateGood")) {
			String goodsid = request.getParameter("goodsid");
			String goodsname = request.getParameter("goodsname");
			String price = request.getParameter("price");
			String num = request.getParameter("num");
			String description = request.getParameter("description");
			String category = request.getParameter("category");
			String address = request.getParameter("address");
			String statue = request.getParameter("statue");
			String activity = request.getParameter("activity");
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = sf.format(new Date());

			String paras[] = { goodsname, time, category, description, activity, price, num, statue, address, goodsid };
			// String sql="update goods set goodsName=?, upTime=? , category=?, description
			// =?, activityId=? , price =? ,num =?, address =?, statue =? where goodsId=?";
			if (gdi.updateGoods(paras) == 1) {
				RespondTool.getNewsString(request, response, "Update successed", 100);
			} else {
				RespondTool.getNewsString(request, response, "Update fail", 200);
			}
		}
		if (flag.equals("deleteGood")) {
			String goodsid = request.getParameter("goodsid");
			String paras[] = { goodsid };
			if (gdi.deleteGoods(paras) == 1) {
				RespondTool.getNewsString(request, response, "Delete success", 100);
			} else {
				RespondTool.getNewsString(request, response, "Delete fail", 200);
			}
		}
		if (flag.equals("addGood")) {

			List<Address> alist = adi.queryAddresslist();
			List<Category> clist = cdi.queryListCategory();
			List<Activity> activityList = activityadi.selectALLActivity();
			request.setAttribute("categoryList", clist);
			request.setAttribute("addressList", alist);
			request.setAttribute("activityList", activityList);
			request.getRequestDispatcher("/WEB-INF/views/addGoods.jsp").forward(request, response);
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
