package servlet.load.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.OrderWindow;
import controller.OrderWindowController;

/**
 * Servlet implementation class LoadAdminViewOrderWindowsClosedServlet
 */
@WebServlet("/LoadAdminViewOrderWindowsClosedServlet")
public class LoadAdminViewOrderWindowsClosedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadAdminViewOrderWindowsClosedServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		OrderWindowController orderWindowController = new OrderWindowController();

		ArrayList<OrderWindow> list = orderWindowController.getAllClosedWindows();

		session.setAttribute("orderWindowList", list);

		response.sendRedirect("/eureka_webservice/admin/orderwindow/closed.jsp");

	}
}
