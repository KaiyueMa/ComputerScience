package bikeShare.servlet.front;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import bikeShare.dao.UserDaoImpl;
import bikeShare.model.User;

/**
 * Servlet implementation class UserManageServlet
 */
@WebServlet(description = "deal login", urlPatterns = { "/UserManageServlet" })
public class UserManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String flag = request.getParameter("flag");
		UserDaoImpl udi = new UserDaoImpl();
		if (flag.equals("login")) {
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
		}
		if (flag.equals("register")) {
			request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
		}

		if (flag.equals("loginconfirm")) {
			String confirmlogo = request.getParameter("confirmlogo");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			HttpSession session = request.getSession();
			String verificationCode = (String) session.getAttribute("certCode");
			if (!confirmlogo.equals(verificationCode)) {
				request.setAttribute("errorMsg", "verification code wrong!");
				request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);

			}

			if (udi.queryByClientNamePass(username, password)) {
				User client = udi.getClientBean(username);
				request.getSession().setAttribute("user", client);

				request.getRequestDispatcher("/ShowFrontMainPage").forward(request, response);
				return;
			} else {
				request.setAttribute("errorMsg", "Wrong username or password!");
				request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
			}

		}

		if (flag.equals("information")) {
			HttpSession session = request.getSession();

			User user = (User) session.getAttribute("user");
			if (user == null) {
				request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
				return;
			}
			Integer userId = user.getUserid();
			System.out.println("session中的" + userId);
			user = udi.queryUserByPramryKey(userId + "");
			request.setAttribute("user", user);
			request.getRequestDispatcher("/WEB-INF/views/information.jsp").forward(request, response);

		}

		if (flag.equals("saveInfo")) {
			// String name= request.getParameter("name");
			String email = request.getParameter("email");
			String telephone = request.getParameter("telephone");
			HttpSession session = request.getSession();

			User user = (User) session.getAttribute("user");
			if (user == null) {
				request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
			}
			String paras[] = { email, telephone, user.getUserid() + "" };

			PrintWriter pw = null;
			try {
				response.setCharacterEncoding("UTF-8");
				pw = response.getWriter();
				if (udi.saveUserInformation(paras) != 0) {
					pw.write(JSONObject.fromObject(bikeShare.model.Msg.success("Update success")).toString());
				} else {
					pw.write(JSONObject.fromObject(bikeShare.model.Msg.success("Update failed")).toString());
				}

			} catch (IOException e) {

				e.printStackTrace();
			} finally {
				if (pw != null) {
					pw.close();
				}
			}
		}

		if (flag.equals("logout")) {
			HttpSession session = request.getSession();
			session.removeAttribute("user");
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
		}
		if (flag.equals("registerresult")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String telephone = request.getParameter("telephone");
			List<User> userlist = (List<User>) udi.getClientByName(username);
			if (!userlist.isEmpty()) {
				request.setAttribute("errorMsg", "This username has been used, please change");
				request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
			} else {
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String time = sf.format(new Date());

				String paras[] = { username, password, time, email, telephone };

				if (udi.insertClient(paras) == 0) {
					request.setAttribute("errorMsg", "Registration failed");
					request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
				} else {
					request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
				}

			}

		}
		if (flag.equals("savePsw")) {

			String password = request.getParameter("Psw");
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			PrintWriter pw = null;
			try {
				response.setCharacterEncoding("UTF-8");
				pw = response.getWriter();
				String paras[] = { password, user.getUserid() + "" };
				if (udi.savePassWord(paras) != 0) {
					pw.write(JSONObject.fromObject(bikeShare.model.Msg.success("Update success")).toString());
				} else {
					pw.write(JSONObject.fromObject(bikeShare.model.Msg.success("Update failed")).toString());
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (pw != null) {
					pw.close();
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
