package bikeShare.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bikeShare.common.RespondTool;
import bikeShare.dao.CategoryDaoImpl;
import bikeShare.model.Category;

/**
 * Servlet implementation class AdminCategoryServlet
 */
@WebServlet("/AdminCategoryServlet")
public class AdminCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminCategoryServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CategoryDaoImpl cdi = new CategoryDaoImpl();
		String flag = request.getParameter("flag");
		if (flag.equals("ShowCategory")) {
			List<Category> clist = cdi.queryListCategory();
			request.setAttribute("categoryList", clist);
			request.getRequestDispatcher("/WEB-INF/views/addCategory.jsp").forward(request, response);

		}
		if (flag.equals("deleteCate")) {
			String cateid = request.getParameter("cateid");
			String[] paras = { cateid + "" };
			if (cdi.deleteCategory(paras) == 1) {
				RespondTool.getNewsString(request, response, "Delete success", 100);
			} else {
				RespondTool.getNewsString(request, response, "Delete fail", 200);
			}

		}
		if (flag.equals("saveCate")) {
			String cateid = request.getParameter("cateid");
			String catename = request.getParameter("catename");
			String paras[] = { catename };
			if (cdi.queryIsNUll(catename)) {

				request.setAttribute("msg", "Category exists");
				request.getRequestDispatcher("/WEB-INF/views/addCategory.jsp").forward(request, response);
			} else {
				cdi.insertCateGory(paras);
				request.setAttribute("msg", "Adding category success");
				request.getRequestDispatcher("/WEB-INF/views/addCategory.jsp").forward(request, response);
			}

		}
		if (flag.equals("updateCate")) {

			String cateid = request.getParameter("cateid");
			String catename = request.getParameter("catename");
			String paras2[] = { catename, cateid };
			if (cdi.queryIsNUll(catename)) {
				RespondTool.getNewsString(request, response, "category exists", 200);
			} else {
				cdi.updateCategory(paras2);
				RespondTool.getNewsString(request, response, "update success", 200);
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
