package servlet.load.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Employee;
import model.FoodOrder;
import controller.FoodOrderController;

/**
 * Servlet implementation class GetEmployeeOrderHistoryServlet
 */
@WebServlet("/LoadUserOrderHistoryServlet")
public class LoadUserOrderHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadUserOrderHistoryServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		HttpSession session = request.getSession();
		try {
			Employee emp = (Employee) session.getAttribute("user");

			FoodOrderController foodOrderController = new FoodOrderController();
			// FoodDisplayPaymentController foodDisplayPaymentController = new
			// FoodDisplayPaymentController();
			List<FoodOrder> foodOrderList = foodOrderController.getFoodOrderSet(emp.getEmail());

			System.out.println(foodOrderList.size());
			// session.setAttribute("foodDisplayPaymentList",
			// foodDisplayPaymentController.renderFoodDisplayPaymentList(foodOrderList));

			session.setAttribute("orderHistory", foodOrderList);
			response.sendRedirect("/eureka_webservice/pages/order-history.jsp");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error msg: " + e.getMessage());
			session.setAttribute("error", "Something went wrong");
			response.sendRedirect("/eureka_webservice/pages/homepage.jsp");
		}

	}

}
