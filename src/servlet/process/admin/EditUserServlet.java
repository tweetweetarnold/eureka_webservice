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
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

import controller.EmployeeController;
import model.Employee;

/**
 * Servlet implementation class EditUserServlet
 */
@WebServlet("/EditUserServlet")
public class EditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditUserServlet() {
		// TODO Auto-generated constructor stub
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

		Gson gson = new Gson();
		EmployeeController employeeCtrl = new EmployeeController();
		JSONObject returnJson = new JSONObject();

		try {
			JSONParser parser = new JSONParser();
			JSONObject data = (JSONObject) parser.parse(request.getReader());

			System.out.println(data);

			String jsonString = gson.toJson(data);
			Employee e = gson.fromJson(jsonString, Employee.class);

			employeeCtrl.updateEmployee(e);
			
			returnJson.put("success", "Employee " + e.getName() + " has been updated.");

		} catch (ParseException e) {
			e.printStackTrace();
			returnJson.put("error", e.getMessage());
		}
		
		out.println(gson.toJson(returnJson));

	}

}
