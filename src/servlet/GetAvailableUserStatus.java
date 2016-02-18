package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;

import value.StringValues;

/**
 * Servlet implementation class GetAvailableUserStatus
 */
@WebServlet("/GetAvailableUserStatus")
public class GetAvailableUserStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetAvailableUserStatus() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		JSONArray arr = new JSONArray();
		Gson gson = new Gson();

		arr.add(StringValues.EMPLOYEE_DESTROYED);
		arr.add(StringValues.EMPLOYEE_OK);
		arr.add(StringValues.EMPLOYEE_PENDING_VERIFICATION);
		arr.add(StringValues.EMPLOYEE_SUSPENDED);

		out.println(gson.toJson(arr));

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
