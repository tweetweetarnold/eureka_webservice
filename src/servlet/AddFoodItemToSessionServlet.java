package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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

		JSONObject returnJson = new JSONObject();

		try {
			// Get Session
			HttpSession session = request.getSession();

			// Get FoodOrder
			FoodOrder foodOrder = null;
			if (session.getAttribute("foodOrder") == null) {
				Employee emp = (Employee) session.getAttribute("user");
				foodOrder = new FoodOrder("good", emp, null, new HashSet<FoodOrderItem>(),
						new Date());
				System.out.println("New FoodOrder created.");
			}else {
				foodOrder = (FoodOrder) session.getAttribute("foodOrder");
			}
			System.out.println("AddFoodItemToSessionServlet foodOrder is set: " + foodOrder);

			// Get selected food item
			List<Food> allFood = (ArrayList<Food>) session.getAttribute("allFood");
			String foodId = (String) request.getParameter("foodId");
			System.out.println("foodId: " + foodId);
			Food food = allFood.get(Integer.parseInt(request.getParameter("foodId")));
			System.out.println("AddFoodToItemSessionServlet Food: " + food);

			Set<FoodOrderItem> list = foodOrder.getFoodOrderList();
			list.add(new FoodOrderItem(foodOrder, food, 1, food.getPrice(), null, new Date()));

			foodOrder.setFoodOrderList(list);
			session.setAttribute("foodOrder", foodOrder);

			returnJson.put("status", "ok");
			returnJson.put("message", "New FoodOrderItem has been added to Cart");

			response.sendRedirect("homepage.jsp");

		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put("status", "fail");
			returnJson.put("error", e.getMessage());
		} finally {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			System.out.println(gson.toJson(returnJson));
		}
	}

}
