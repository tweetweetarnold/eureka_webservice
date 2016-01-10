package servlet.process.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Food;
import model.Stall;
import controller.FoodController;
import controller.StallController;

/**
 * Servlet implementation class ProcessAdminAddNewFoodServlet
 */
@WebServlet("/ProcessAdminAddNewFoodServlet")
public class ProcessAdminAddNewFoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcessAdminAddNewFoodServlet() {
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
		StallController stallController = new StallController();

		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String priceString = request.getParameter("price");
		String stallIdString = request.getParameter("stallId");
		System.out.println("String: " + stallIdString);

		String imageDirectory = request.getParameter("imageDirectory");
		String weatherConditions = request.getParameter("weatherConditions");

		try {
			double price = Double.parseDouble(priceString);
			int stallId = Integer.parseInt(stallIdString);

			Stall stall = stallController.getStall(stallId);
			Food food = new Food(name, description, price, imageDirectory, stall);

			food.setWeatherConditions(weatherConditions);

			System.out.println("foodname: " + food.getName());
			System.out.println("saving food...");
			foodController.saveFood(food);

			session.setAttribute("success", "Food added successfully.");

			response.sendRedirect("/eureka_webservice/admin/food/view.jsp");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
