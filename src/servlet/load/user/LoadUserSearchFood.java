package servlet.load.user;

import java.io.IOException;
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
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			HttpSession session = request.getSession();
			OrderPeriod orderPeriod = (OrderPeriod) session.getAttribute("orderPeriod");
			String foodName = request.getParameter("food");
			Canteen c = orderPeriod.getCanteen();
			FoodDAO foodDAO = new FoodDAO();
			List<Food> list = foodDAO.searchFoodFromCanteen(c, foodName);
			
			//redirect Missing
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
