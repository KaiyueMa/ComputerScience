package bikeShare.servlet.front;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import bikeShare.common.PageUtil;
import bikeShare.dao.GoodDaoImpl;
import bikeShare.dao.ImagePathDaoimpl;
import bikeShare.model.Goods;
import bikeShare.model.ImagePath;
import bikeShare.service.GoodServiceImpl;

/**
 * Servlet implementation class ShowFrontHomeByPager
 */
@WebServlet(description = "????", urlPatterns = { "/ShowFrontHomeByPager" })
public class ShowFrontHomeByPager extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowFrontHomeByPager() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		String pn = request.getParameter("pn");
		int pageNo = 1;
		if (pn != null) {
			pageNo = Integer.parseInt(pn);
			System.out.println("pageNow" + pageNo);
		}
		ImagePathDaoimpl ipdi = new ImagePathDaoimpl();
		GoodDaoImpl gdi = new GoodDaoImpl();
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

		// https://www.baidu.com/s?ie=utf-8&f=3&rsv_bp=1&rsv_idx=1&tn=92610479_hao_pg&wd=%E5%A6%82%E4%BD%95%E6%8A%8Aajax%E8%BF%94%E5%9B%9E%E7%9A%84list%E9%9B%86%E5%90%88%20%20servlet&oq=%25E5%25A6%2582%25E4%25BD%2595%25E6%258A%258Aajax%25E8%25BF%2594%25E5%259B%259E%25E7%259A%2584list%25E9%259B%2586%25E5%2590%2588&rsv_pq=bbddda32000262d5&rsv_t=f86bJql8livOwybLaeKMziRmah6aN7xI89KSmBe%2Foyjt9Du218Q1cvFi7yBSKf2qyM5Nqk3D&rqlang=cn&rsv_enter=0&inputT=4834&rsv_sug3=198&rsv_sug1=126&rsv_sug7=000&rsv_sug4=5688&rsv_sug=1
		// https://www.cnblogs.com/qianzf/p/7115282.html
		// https://blog.csdn.net/yanghan1222/article/details/78447231
		// https://blog.csdn.net/veggiel/article/details/52327551
		// https://blog.csdn.net/baidu_34211956/article/details/79929934
		// https://blog.csdn.net/zc1994113/article/details/50390058

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
