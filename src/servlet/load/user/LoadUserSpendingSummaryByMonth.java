package servlet.load.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.FoodOrderController;
import model.Employee;
import model.FoodOrder;

/**
 * Servlet implementation class LoadUserSpendingSummaryByMonth
 */
@WebServlet("/LoadUserSpendingSummaryByMonth")
public class LoadUserSpendingSummaryByMonth extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadUserSpendingSummaryByMonth() {
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
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	protected void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");

		HttpSession session = request.getSession();
		try {
			Employee emp = (Employee) session.getAttribute("user");

			FoodOrderController foodOrderController = new FoodOrderController();
			TreeMap<String, ArrayList<FoodOrder>> yearMonthToFoodOrders = foodOrderController.getFoodOrderSetByMonthYear(emp);
			TreeMap<String, Double>  yearMonthToTotalPrice = foodOrderController.getFoodOrderSetTotalPriceByMonthYear(yearMonthToFoodOrders);
			
			session.setAttribute("yearMonthToFoodOrders", yearMonthToFoodOrders);
			session.setAttribute("yearMonthToTotalPrice", yearMonthToTotalPrice);
			//***Haven't define the jsp page to redirect***
			//response.sendRedirect("/eureka_webservice/pages/order-history.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error msg: " + e.getMessage());
			session.setAttribute("error", "Something went wrong");
			response.sendRedirect("/eureka_webservice/pages/homepage.jsp");
		}
	}

}
