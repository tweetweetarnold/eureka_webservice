package servlet.process.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;

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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			Gson gson = new Gson();

			JSONParser parser = new JSONParser();
			JSONObject data = (JSONObject) parser.parse(request.getReader());
			System.out.println(gson.toJson(data));

			int foodId = Integer.parseInt((String) data.get("foodId"));
			System.out.println("foodID: " + foodId);
			JSONArray arr = (JSONArray) data.get("modifierList");
			for (int i = 0; i < arr.size(); i++) {
				JSONObject obj = (JSONObject) arr.get(i);
				String name = (String) obj.get("name");
				double price = Double.parseDouble((String) obj.get("price"));
				System.out.println("name: " + name);
				System.out.println("price: " + price);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
