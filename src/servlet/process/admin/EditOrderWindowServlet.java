package servlet.process.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.simple.JSONObject;

import com.google.gson.Gson;

import controller.OrderWindowController;

/**
 * Servlet implementation class EditOrderWindowServlet
 */
@WebServlet("/EditOrderWindowServlet")
public class EditOrderWindowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditOrderWindowServlet() {
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

		HttpSession session = request.getSession();

		JSONObject returnJson = new JSONObject();
		Gson gson = new Gson();

		OrderWindowController orderWindowController = new OrderWindowController();
		String orderWindowIdString = request.getParameter("orderWindowId");
		try {

			String startDatetimeString = request.getParameter("startDatetime");
			String endDatetimeString = request.getParameter("endDatetime");

			System.out.println(orderWindowIdString);
			System.out.println(startDatetimeString);
			System.out.println(endDatetimeString);

			// **Important: to format the time to SG timezone
			DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MMMM-yyyy HH:mm")
					.withZone(DateTimeZone.forID("Asia/Singapore"));
			DateTime startDatetime = formatter.parseDateTime(startDatetimeString);
			DateTime endDatetime = formatter.parseDateTime(endDatetimeString);

			if (startDatetime.isAfter(endDatetime)) {
				throw new Exception("Start time cannot be after end time");
			}

			System.out.println("startDatetime: " + startDatetime);
			System.out.println("endDatetime: " + endDatetime);

			orderWindowController.editOrderWindow(Integer.parseInt(orderWindowIdString),
					startDatetime, endDatetime);

			returnJson.put("success", "Order Window updated.");
			session.setAttribute("success", "Order window updated");

		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put("error", e.getMessage());
			session.setAttribute("error", e.getMessage());

		}
		response.sendRedirect("/eureka_webservice/LoadAdminEditOrderWindowServlet?windowId="
				+ orderWindowIdString);

		out.println(gson.toJson(returnJson));

	}

}
