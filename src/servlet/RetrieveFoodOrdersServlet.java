package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.FoodDisplayObject;
import controller.FoodOrderController;

/**
 * Servlet implementation class retrieveFoodOrdersServlet
 */
@WebServlet("/RetrieveFoodOrdersServlet")
public class RetrieveFoodOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RetrieveFoodOrdersServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		FoodOrderController foodOrderController = new FoodOrderController();
		ArrayList<FoodDisplayObject> foodOrderList = new ArrayList<FoodDisplayObject>(
				foodOrderController.getFoodOrderForCutOff());
		
		System.out.println("foodOrderList: " + foodOrderList.size());

		session.setAttribute("foodOrders", foodOrderList);
		response.sendRedirect("adminFoodOrderByStall.jsp");

	}

}
