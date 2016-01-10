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
 * Servlet implementation class ProcessAdminEditFoodServlet
 */
@WebServlet("/ProcessAdminEditFoodServlet")
public class ProcessAdminEditFoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcessAdminEditFoodServlet() {
		super();
		// TODO Auto-generated constructor stub
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

		String foodIdString = request.getParameter("foodId");

		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String priceString = request.getParameter("price");

		String imageDirectory = request.getParameter("imageDirectory");
		String weatherConditions = request.getParameter("weatherConditions");

		try {
			int foodId = Integer.parseInt(foodIdString);
			double price = Double.parseDouble(priceString);

			Food food = foodController.getFood(foodId);
			int stallId = food.getStall().getStallId();

			food.setName(name);
			food.setDescription(description);
			food.setPrice(price);
			food.setImageDirectory(imageDirectory);
			food.setWeatherConditions(weatherConditions);

			System.out.println("foodname: " + food.getName());
			System.out.println("saving food...");
			foodController.updateFood(food);

			session.setAttribute("success", "Food updated successfully.");

			response.sendRedirect("/eureka/LoadAdminViewFoodsServlet?stallId=" + stallId);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
