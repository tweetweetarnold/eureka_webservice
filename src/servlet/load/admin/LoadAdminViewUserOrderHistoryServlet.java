package servlet.load.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.FoodOrder;
import controller.FoodOrderController;

/**
 * Servlet implementation class LoadAdminViewUserOrderHistoryServlet
 */
@WebServlet("/LoadAdminViewUserOrderHistoryServlet")
public class LoadAdminViewUserOrderHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadAdminViewUserOrderHistoryServlet() {
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

		FoodOrderController foodOrderController = new FoodOrderController();
		HttpSession session = request.getSession();

		String email = request.getParameter("email");
		String name = request.getParameter("name");

		List<FoodOrder> list = foodOrderController.getFoodOrderSet(email);

		session.setAttribute("orderHistoryList", list);
		session.setAttribute("name", name);

		response.sendRedirect("/eureka_webservice/admin/user/order-history.jsp");

	}

}
