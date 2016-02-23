package servlet.process.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Food;
import controller.FoodController;

/**
 * Servlet implementation class ProcessAdminDeleteFoodServlet
 */
@WebServlet("/ProcessAdminDeleteFoodServlet")
public class ProcessAdminDeleteFoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcessAdminDeleteFoodServlet() {
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
		FoodController foodController = new FoodController();

		try {
			String foodIdString = request.getParameter("foodId");
			String stallIdString = request.getParameter("stallId");

			int stallId = Integer.parseInt(stallIdString);
			int foodId = Integer.parseInt(foodIdString);

			Food food = foodController.getFood(foodId);

			foodController.deleteFood(food);

			session.setAttribute("success", food.getName() + " has been deleted.");

			response.sendRedirect("/eureka_webservice/admin/food/view.jsp?stallId=" + stallId);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
