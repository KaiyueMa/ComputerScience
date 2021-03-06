package bikeShare.servlet.front;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bikeShare.model.Goods;
import bikeShare.service.GoodServiceImpl;

/**
 * Servlet implementation class ShowFrontMainPage
 */
@WebServlet(description = "show front page", urlPatterns = { "/ShowFrontMainPage" })
public class ShowFrontMainPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowFrontMainPage() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		GoodServiceImpl gsi = new GoodServiceImpl();

		Goods meirong = gsi.getNews(90);
		Goods remen = gsi.getNews(91);
		Goods mingxing = gsi.getNews(92);
		if (null != meirong) {
			request.setAttribute("meirong", meirong);
		}
		if (null != remen) {
			request.setAttribute("remen", remen);
		}
		if (null != mingxing) {
			request.setAttribute("mingxing", mingxing);
		}

		request.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(request, response);

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
