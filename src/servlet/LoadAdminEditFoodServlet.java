package servlet;

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
 * Servlet implementation class LoadAdminEditFoodDetailsServlet
 */
@WebServlet("/LoadAdminEditFoodDetailsServlet")
public class LoadAdminEditFoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadAdminEditFoodServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		FoodController foodController = new FoodController();

		String foodIdString = request.getParameter("foodId");
		int foodId = Integer.parseInt(foodIdString);

		Food food = foodController.getFood(foodId);

		session.setAttribute("foodId", foodId);
		session.setAttribute("name", food.getName());
		session.setAttribute("description", food.getDescription());
		session.setAttribute("price", food.getPrice());
		session.setAttribute("imageDirectory", food.getImageDirectory());
		session.setAttribute("weatherConditions", food.getWeatherConditions());

		response.sendRedirect("adminEditFood.jsp");
	}

}
