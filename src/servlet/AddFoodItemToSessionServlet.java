package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
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
			foodOrder = new JSONObject(); // For testing

			String foodId = (String) request.getParameter("foodId");

			JSONArray foodItems = new JSONArray(); // For testing
			foodItems.add(foodId);
			foodOrder.put("foodItems", foodItems);
			session.setAttribute("foodOrder", foodOrder);

			obj.put("status", "ok");
			obj.put("message", "Activity complete");

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
