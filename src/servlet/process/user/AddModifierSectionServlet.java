package servlet.process.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;

import controller.ModifierSectionController;

/**
 * Servlet implementation class AddModifierSectionServlet
 */
@WebServlet("/AddModifierSectionServlet")
public class AddModifierSectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddModifierSectionServlet() {
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

		JSONObject returnJson = new JSONObject();
		Gson gson = new Gson();

		ModifierSectionController modifierSectionCtrl = new ModifierSectionController();

		try {
			JSONParser parser = new JSONParser();
			JSONObject data = (JSONObject) parser.parse(request.getReader());
			System.out.println(gson.toJson(data));

			String foodId = (String) data.get("foodId");
			String categoryName = (String) data.get("categoryName");
			String displayType = (String) data.get("displayType");

			modifierSectionCtrl.addModifierSection(foodId, categoryName, displayType);

			returnJson.put("success", "ModifierSection " + categoryName + " has been added.");

		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put("error", e.getMessage());
		}

		System.out.println(gson.toJson(returnJson));
		out.print(gson.toJson(returnJson));

	}

}
