package servlet.load.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.FoodDAO;
import model.Canteen;
import model.Food;
import model.OrderPeriod;

/**
 * Servlet implementation class LoadUserSearchFood
 */
@WebServlet("/LoadUserSearchFood")
public class LoadUserSearchFood extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadUserSearchFood() {
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

		FoodDAO foodDAO = new FoodDAO();

		try {
			OrderPeriod orderPeriod = (OrderPeriod) session.getAttribute("orderPeriod");
			String foodName = request.getParameter("food");
			Canteen c = orderPeriod.getCanteen();

			List<Food> list = foodDAO.searchFoodFromCanteen(c, foodName);

			session.setAttribute("results", list);
			response.sendRedirect("/eureka_webservice/pages/search-results.jsp");

			System.out.println(list);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
