package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.FoodDisplayObject;
import model.FoodOrderItem;
import model.OrderWindow;
import value.StringValues;
import controller.FoodOrderController;
import controller.OrderWindowController;

/**
 * Servlet implementation class GetTodayOrdersServlet
 */
@WebServlet("/LoadOrderWindowOpenedServlet")
public class LoadOrderWindowOpenedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadOrderWindowOpenedServlet() {
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

		HttpSession session = request.getSession();
		FoodOrderController foodOrderController = new FoodOrderController();
		OrderWindowController orderWindowController = new OrderWindowController();

		try {
			OrderWindow window = orderWindowController.getOrderWindow(1);

			HashMap<String, ArrayList<FoodOrderItem>> list1 = foodOrderController
					.getAllFoodOrderOfOrderWindow(window);
			System.out.println("listsize 1: " + list1.size());

			session.setAttribute(StringValues.SESSION_ORDERS_WINDOW_OPENED_NOGROUP, list1);

			ArrayList<FoodDisplayObject> list2 = foodOrderController
					.getAllFoodOrderOfOrderWindowGroupedByStall(window);
			System.out.println("listsize 2: " + list2.size());

			session.setAttribute(StringValues.SESSION_ORDERS_WINDOW_OPENED_STALLS, list2);

			response.sendRedirect("adminFoodOrders.jsp");

		} catch (Exception e) {
			System.out.println("Error occurred at LoadOrderWindowOpenedServlet");
			e.printStackTrace();
			response.sendRedirect("adminFoodOrders.jsp");
		}
	}
}
