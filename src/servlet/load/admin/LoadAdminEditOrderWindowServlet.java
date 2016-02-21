package servlet.load.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.OrderWindowController;
import model.OrderWindow;

/**
 * Servlet implementation class LoadAdminEditOrderWindowServlet
 */
@WebServlet("/LoadAdminEditOrderWindowServlet")
public class LoadAdminEditOrderWindowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadAdminEditOrderWindowServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		OrderWindowController orderWindowCtrl = new OrderWindowController();

		try {
			String orderWindowIdString = request.getParameter("windowId");
			int orderWindowId = Integer.parseInt(orderWindowIdString);
			OrderWindow window = orderWindowCtrl.getOrderWindow(orderWindowId);

			session.setAttribute("window", window);

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("error", e.getMessage());

		}

		response.sendRedirect("/eureka_webservice/admin/orderwindow/edit.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
