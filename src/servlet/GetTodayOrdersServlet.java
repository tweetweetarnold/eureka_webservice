package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.FoodOrderItem;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;

import controller.FoodOrderController;

/**
 * Servlet implementation class GetTodayOrdersServlet
 */
@WebServlet("/GetTodayOrdersServlet")
public class GetTodayOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetTodayOrdersServlet() {
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
		try {
			session.setAttribute("todayOrders", foodOrderController.getFoodOrderToday2());

			HashMap<String, ArrayList<FoodOrderItem>> map = foodOrderController
					.getFoodOrderToday2();
			ArrayList<FoodOrderItem> list = map.get("arnold.lee.2013@sis.smu.edu.sg");
			System.out.println("List size: " + list.size());

			response.sendRedirect("adminFoodOrders.jsp");
		} catch (Exception e) {
			session.setAttribute("NoOrders", "There are no orders for today");
			response.sendRedirect("adminFoodOrders.jsp");
		}
	}

}
