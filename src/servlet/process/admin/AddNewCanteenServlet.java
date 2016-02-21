package servlet.process.admin;

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

import controller.CanteenController;

/**
 * Servlet implementation class AddNewCanteenServlet
 */
@WebServlet("/AddNewCanteenServlet")
public class AddNewCanteenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddNewCanteenServlet() {
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

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		CanteenController canteenCtrl = new CanteenController();

		JSONObject returnJson = new JSONObject();
		Gson gson = new Gson();

		try {

			JSONParser parser = new JSONParser();
			JSONObject data = (JSONObject) parser.parse(request.getReader());
			System.out.println(gson.toJson(data));

			String name = (String) data.get("name");
			String address = (String) data.get("address");

			canteenCtrl.addCanteen(name, address);

			returnJson.put("success", "Canteen " + name + " has been added.");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("add new canteen error");
			returnJson.put("error", e.getMessage());
		}
		
		System.out.println(gson.toJson(returnJson));
		out.print(gson.toJson(returnJson));
	}

}
