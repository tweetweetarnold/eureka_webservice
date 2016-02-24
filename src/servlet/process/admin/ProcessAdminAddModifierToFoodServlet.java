package servlet.process.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;

import controller.ModifierSectionController;

/**
 * Servlet implementation class ProcessAdminAddModifierToFoodServlet
 */
@WebServlet("/ProcessAdminAddModifierToFoodServlet")
public class ProcessAdminAddModifierToFoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcessAdminAddModifierToFoodServlet() {
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

		Gson gson = new Gson();
		JSONObject returnJson = new JSONObject();

		try {

			JSONParser parser = new JSONParser();
			JSONObject data = (JSONObject) parser.parse(request.getReader());
			System.out.println(gson.toJson(data));

			String foodId = (String) data.get("foodId");
			System.out.println("foodID: " + foodId);
			JSONArray arr = (JSONArray) data.get("modifierList");
			ModifierSectionController modifierSectionController = new ModifierSectionController();
			String modifierSectionId = ""
					+ modifierSectionController.addModifierSection(foodId, "Add-ons", "c");
			for (int i = 0; i < arr.size(); i++) {
				JSONObject obj = (JSONObject) arr.get(i);
				String name = (String) obj.get("name");
				double price = Double.parseDouble((String) obj.get("price"));
				// modifier creation
				modifierSectionController.createAndAddModifier(name, "", "", price, foodId,
						modifierSectionId);
				System.out.println("name: " + name);
				System.out.println("price: " + price);
			}

			returnJson.put("success", "Add-Ons added successfully.");

		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put("error", e.getMessage());
		}

		out.println(gson.toJson(returnJson));
	}

}
