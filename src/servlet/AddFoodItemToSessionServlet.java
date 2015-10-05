package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Employee;
import model.Food;
import model.FoodOrder;
import model.FoodOrderItem;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class AddFoodItemToSessionServlet
 */
@WebServlet(description = "This servlet adds a food item to be stored in session", urlPatterns = { "/AddFoodItemToSessionServlet" })
public class AddFoodItemToSessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddFoodItemToSessionServlet() {
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
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	@SuppressWarnings("unchecked")
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		System.out.println("AddFoodItemToSessionServlet");

		JSONObject obj = new JSONObject();

		try {
			// Get Sesssion
			HttpSession session = request.getSession();

			JSONObject foodOrder = (JSONObject) session.getAttribute("foodOrder");
			if (foodOrder == null) {
				foodOrder = new JSONObject(); // For testing
			}
			FoodOrder order = (FoodOrder) foodOrder.get("foodOrder");
			if(order == null) {
				Employee emp = (Employee) session.getAttribute("employee");
				order = new FoodOrder("good", emp, null, null, new Date());
				
			}

			// String foodId = (String) request.getParameter("foodId");
			Food food = (Food) session.getAttribute("newFoodOrderItem");
			FoodOrderItem item = new FoodOrderItem(order, food, 2, 2.5, null, new Date());
			Set<FoodOrderItem> set = new HashSet<FoodOrderItem>();
			set.add(item);
			order.setFoodOrderList(set);
			foodOrder.put("foodOrder", order);
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			System.out.println(gson.toJson("THEORDER: " + foodOrder));

			// JSONArray foodItems = new JSONArray(); // For testing
			// foodItems.add(foodId);
			// foodOrder.put("foodItems", foodItems);
			session.setAttribute("foodOrder", foodOrder);
			System.out.println("im here");

			obj.put("status", "ok");
			obj.put("message", "Activity complete");
			
			response.sendRedirect("homepage.jsp");

		} catch (Exception e) {
			e.printStackTrace();
			obj.put("status", "fail");
			obj.put("error", e.getMessage());
		} finally {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			System.out.println(gson.toJson(obj));
		}
	}

}
