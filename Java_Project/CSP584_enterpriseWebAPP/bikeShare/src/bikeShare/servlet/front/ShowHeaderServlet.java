package bikeShare.servlet.front;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bikeShare.common.PageUtil;
import bikeShare.dao.CategoryDaoImpl;
import bikeShare.dao.GoodDaoImpl;
import bikeShare.dao.ImagePathDaoimpl;
import bikeShare.model.Category;
import bikeShare.model.Goods;
import bikeShare.model.ImagePath;

/**
 * Servlet implementation class ShowHeaderServlet
 */
@WebServlet("/ShowHeaderServlet")
public class ShowHeaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowHeaderServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String flag = request.getParameter("flag");
		GoodDaoImpl gdi = new GoodDaoImpl();
		CategoryDaoImpl catedi = new CategoryDaoImpl();
		if (flag.equals("showCate")) {
			String catename = request.getParameter("cate");
			if (catename.equals("cate ")) {
				List<Category> clist = catedi.queryListCategory();

			} else {

			}
		}

		if (flag.equals("search")) {

			String goodsname = request.getParameter("keyword");
			ImagePathDaoimpl ipdi = new ImagePathDaoimpl();
			String pn = request.getParameter("page");
			int pageNo = 1;
			if (pn != null) {
				pageNo = Integer.parseInt(pn);
				System.out.println("pageNow" + pageNo);
			}
			String[] paras = { "%" + goodsname + "%" };
			PageUtil page = gdi.getGoodsByPageNameLike(pageNo, 4, paras);

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

			page.setData(goodsList);
			request.setAttribute("pageInfo", page);
			request.setAttribute("keyword", goodsname);

			request.getRequestDispatcher("/WEB-INF/views/search.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
