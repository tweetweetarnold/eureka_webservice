package servlet.process.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.FoodOrderController;

/**
 * Servlet implementation class ProcessAdminDeleteFoodOrderItemServlet
 */
@WebServlet("/ProcessAdminDeleteFoodOrderItemServlet")
public class ProcessAdminDeleteFoodOrderItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcessAdminDeleteFoodOrderItemServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String foodOrderItemId = request.getParameter("foodOrderItemId");
		System.out.println("******Deleting: " + foodOrderItemId);
		FoodOrderController foodOrderController = new FoodOrderController();
		try {

			foodOrderController.deleteFoodOrderItem(Integer.parseInt(foodOrderItemId));

			session.setAttribute("success", foodOrderItemId + " has been deleted.");

			response.sendRedirect("/eureka_webservice/LoadOrderPeriodActiveServlet");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
