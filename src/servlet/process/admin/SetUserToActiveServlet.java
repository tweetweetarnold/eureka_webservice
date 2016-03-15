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

import controller.EmployeeController;
import model.Employee;
import value.StringValues;

/**
 * Servlet implementation class SetUserToActiveServlet
 */
@WebServlet("/SetUserToActiveServlet")
public class SetUserToActiveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SetUserToActiveServlet() {
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

		PrintWriter out = response.getWriter();
		response.setContentType("application/json");

		Gson gson = new Gson();
		EmployeeController employeeCtrl = new EmployeeController();
		JSONObject returnJson = new JSONObject();

		try {
			JSONParser parser = new JSONParser();
			JSONObject data = (JSONObject) parser.parse(request.getReader());
			System.out.println(gson.toJson(data));

			String email = ((String) data.get("email"));

			Employee e = employeeCtrl.getEmployeeByEmail(email);
			if (e.getAmountOwed() > 0) {
				e.setStatus(StringValues.EMPLOYEE_SUSPENDED);
			} else {
				e.setStatus(StringValues.EMPLOYEE_ACTIVE);
			}
			employeeCtrl.updateEmployee(e);

			returnJson.put("success", "Employee " + e.getName() + " updated.");

		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put("error", e.getMessage());
		}

		out.println(gson.toJson(returnJson));
	}

}
