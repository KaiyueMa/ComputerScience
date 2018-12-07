package bikeShare.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bikeShare.common.PageUtil;
import bikeShare.common.RespondTool;
import bikeShare.dao.ActivityDaoImpl;
import bikeShare.model.Activity;

/**
 * Servlet implementation class AdminActivityServlet
 */
@WebServlet("/AdminActivityServlet")
public class AdminActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminActivityServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		ActivityDaoImpl adi = new ActivityDaoImpl();
		String flag = request.getParameter("flag");
		if (flag.equals("showjson")) {
			List<Activity> alist = adi.selectALLActivity();
			RespondTool.putList(request, response, alist);

		}
		if (flag.equals("showActivity")) {

			List<Activity> alist = adi.selectALLActivity();
			request.setAttribute("pageInfo", alist);
			request.getRequestDispatcher("/WEB-INF/views/activity.jsp").forward(request, response);
		}
		if (flag.equals("showaddActivity")) {
			request.getRequestDispatcher("/WEB-INF/views/addActivity.jsp").forward(request, response);
		}
		if (flag.equals("addResult")) {
			String activityname = request.getParameter("activityname");
			String discount = request.getParameter("discount");
			String fullprice = request.getParameter("fullprice");
			String reduceprice = request.getParameter("reduceprice");
			String fullnum = request.getParameter("fullnum");
			String reducenum = request.getParameter("reducenum");
			String activitydes = request.getParameter("activitydes");

			// insert into
			// activity(activityName,activityDes,discount,fullprice,reducePrice,fullNum,reduceNum)
			// values(?,?,?,?,?,?,?)
			String paras[] = { activityname, activitydes, discount, fullprice, reduceprice, fullnum, reducenum };
			if (adi.insertActivity(paras) == 1) {
				request.setAttribute("msg", "Add success");
				request.getRequestDispatcher("/WEB-INF/views/addActivity.jsp").forward(request, response);
			} else {
				request.setAttribute("msg", "Add fail");
				request.getRequestDispatcher("/WEB-INF/views/addActivity.jsp").forward(request, response);
			}

		}
		if (flag.equals("delteActivity")) {
			String activityid = request.getParameter("activityid");
			if (adi.deleteActivy(activityid) == 1) {
				request.setAttribute("msg", "Delete Success");
				request.getRequestDispatcher("/AdminActivityServlet?flag=showActivity").forward(request, response);
			} else {
				request.setAttribute("msg", "Delete fail");
				request.getRequestDispatcher("/AdminActivityServlet?flag=showActivity").forward(request, response);
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
