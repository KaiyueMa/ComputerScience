package smartPortables;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/autocomplete")

public class AutoCompleteServlet extends HttpServlet {

	private static final Logger LOGGER = Logger.getLogger(AutoCompleteServlet.class.getName());
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String SEARCH_ID = "searchId";
	private static final String COMPLETE = "complete";
	private static final String LOOKUP = "lookup";

	private ServletContext context;

	String searchId = null;

	@Override
	public void init(ServletConfig config) throws ServletException {
		this.context = config.getServletContext();

	}

	/**
	 * Handles the HTTP <code>GET</code> method.
	 * 
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			searchId = request.getParameter(SEARCH_ID);
			if (searchId == null) {
				context.getRequestDispatcher("/Error").forward(request, response);
				return;
			}

			final String action = request.getParameter("action");

			if (action.equals(COMPLETE)) {
				searchId = searchId.trim().toLowerCase();
				// check if user sent empty string
				if (!searchId.equals("")) {
					String data = AjaxUtility.readdata(searchId).toString();
					if (!data.equals("")) {
						response.setContentType("text/xml");
						response.getWriter().write("<products>" + data + "</products>");
					} else {
						// nothing to show
						response.setStatus(HttpServletResponse.SC_NO_CONTENT);
						System.out.println("Nothing AutoComplete");
					}
				}
			} else if (action.equals(LOOKUP)) {
				HashMap<String, Product> data = AjaxUtility.getData();
				if (data.containsKey(searchId.trim())) {
					request.setAttribute("data", data.get(searchId.trim()));
					RequestDispatcher rd = context.getRequestDispatcher("/ProductData");
					rd.forward(request, response);
				}
			}
		} catch (Exception ex) {
			LOGGER.log(Level.WARNING, "Exception occur", ex);
		}
	}
}
