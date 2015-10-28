package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.mysql.fabric.xmlrpc.base.Data;

import controller.FoodOrderController;
import model.Canteen;
import model.FoodOrder;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// String date = new Data().toString();
		// int index = date.indexOf(" ");
		// date = date.substring(0, index);
		// date+=" 00:00:00";
		FoodOrderController foodOrderController = new FoodOrderController();
		JSONObject json = null;
		JSONArray jsonArray = new JSONArray();
		try {
		HashMap tempFoodOrderHash = foodOrderController.getFoodOrderToday();
		// Iterator iter = tempFoodOrderHash.keySet().iterator();

		Gson gson = new Gson();
		
		
			json = new JSONObject(tempFoodOrderHash);
			RequestDispatcher rd = request.getRequestDispatcher("adminFoodOrders.jsp");
			request.setAttribute("foodOrders", json);
			rd.forward(request, response);
		} catch (Exception e) {
			RequestDispatcher rd = request.getRequestDispatcher("adminFoodOrders.jsp");
			request.setAttribute("NoOrders", "There are no orders for today");
			rd.forward(request, response);
			
		}
		System.out.println("HELLO");
	}

}
