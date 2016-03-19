package servlet.process.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;

import controller.FoodController;
import controller.FoodOrderController;
import model.Food;
import model.FoodOrder;
import model.FoodOrderItem;
import model.Modifier;
import model.ModifierChosen;

/**
 * Servlet implementation class AddFoodOrderItemIntoFoodOrderServlet
 */
@WebServlet("/AddFoodOrderItemIntoFoodOrderServlet")
public class AddFoodOrderItemIntoFoodOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddFoodOrderItemIntoFoodOrderServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		FoodOrderController foodOrderCtrl = new FoodOrderController();
		FoodController foodCtrl = new FoodController();

		Gson gson = new Gson();
		JSONObject returnJson = new JSONObject();

		int foodOrderItemId = -1;

		try {
			JSONParser parser = new JSONParser();
			JSONObject data = (JSONObject) parser.parse(request.getReader());
			System.out.println(gson.toJson(data));

			JSONObject f = (JSONObject) data.get("food");
			JSONObject m = (JSONObject) data.get("modifier");
			int q = Integer.parseInt((String) data.get("quantity"));
			int foodOrderId = Integer.parseInt((String) data.get("foodOrderId"));
			if (data.get("foodOrderItemId") != null) {
				foodOrderItemId = Integer.parseInt((String) data.get("foodOrderItemId"));
			}
			System.out.println("foodorderitemId: " + foodOrderItemId);

			// to delete the food if there is a foodorderitemid selected
			if (foodOrderItemId != -1) {
				foodOrderCtrl.deleteFoodOrderItem(foodOrderItemId);
				System.out.println("food deleted: " + foodOrderItemId);
			}

			String jsonString = gson.toJson(m);
			System.out.println(jsonString);
			Modifier m1 = gson.fromJson(jsonString, Modifier.class);

			Food food = foodCtrl.getFood(((Long) f.get("foodId")).intValue());
			FoodOrder order = foodOrderCtrl.getFoodOrder(foodOrderId);
			FoodOrderItem item = new FoodOrderItem(order, food, q, null);

			System.out.println("modifierchosen: " + m1);
			if (m1 != null) {
				ModifierChosen mchosen = new ModifierChosen(m1, item);
				Set<ModifierChosen> modifierChosenList = new HashSet<ModifierChosen>();
				modifierChosenList.add(mchosen);
				item.setModifierChosenList(modifierChosenList);
			}

			foodOrderCtrl.addFoodOrderItemIntoFoodOrder(item, order);

			returnJson.put("success", "Order updated.");

		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put("error", e.getMessage());
		}

		out.println(gson.toJson(returnJson));

	}

}
