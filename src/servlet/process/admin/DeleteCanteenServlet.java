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
 * Servlet implementation class DeleteCanteenServlet
 */
@WebServlet("/DeleteCanteenServlet")
public class DeleteCanteenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteCanteenServlet() {
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

		JSONObject returnJson = new JSONObject();
		Gson gson = new Gson();

		CanteenController canteenCtrl = new CanteenController();

		try {
			JSONParser parser = new JSONParser();
			JSONObject data = (JSONObject) parser.parse(request.getReader());
			System.out.println(gson.toJson(data));

			int canteenId = ((Long) data.get("canteenId")).intValue();
			String canteenName = (String) data.get("canteenName");
			canteenCtrl.deleteCanteen(canteenId);

			returnJson.put("success", "Canteen " + canteenName + " has been deleted.");

		} catch (Exception e) {
			e.printStackTrace();
			
			returnJson.put("error", e.getMessage());
		}

		out.println(gson.toJson(returnJson));

	}
}
