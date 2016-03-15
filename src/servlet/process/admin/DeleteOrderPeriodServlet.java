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

import controller.OrderPeriodController;

/**
 * Servlet implementation class DeleteOrderPeriodServlet
 */
@WebServlet("/DeleteOrderPeriodServlet")
public class DeleteOrderPeriodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteOrderPeriodServlet() {
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
		OrderPeriodController orderPeriodCtrl = new OrderPeriodController();

		Gson gson = new Gson();

		try {
			JSONParser parser = new JSONParser();
			JSONObject data = (JSONObject) parser.parse(request.getReader());
			System.out.println(gson.toJson(data));

			int periodId = ((Long) data.get("periodId")).intValue();

			orderPeriodCtrl.deleteOrderPeriod(periodId);

			returnJson.put("success", "Order Period ID: " + periodId + " has been deleted.");

		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put("error", e.getMessage());
		}

		out.println(gson.toJson(returnJson));
		System.out.println(gson.toJson(returnJson));

	}

}
