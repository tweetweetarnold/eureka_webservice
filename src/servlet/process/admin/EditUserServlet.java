package servlet.process.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import controller.CompanyController;
import controller.EmployeeController;
import model.Company;
import model.Employee;

/**
 * Servlet implementation class EditUserServlet
 */
@WebServlet("/EditUserServlet")
public class EditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	final JsonDeserializer<Employee> empDeserialize = new JsonDeserializer<Employee>() {

		@Override
		public Employee deserialize(JsonElement json, Type arg1, JsonDeserializationContext arg2)
				throws JsonParseException {

			CompanyController ctrl = new CompanyController();

			JsonObject o = json.getAsJsonObject();

//			Date date = new Date(o.get("createDate").getAsLong());
			String password = o.get("password").getAsString();
			String name = o.get("name").getAsString();
			String email = o.get("email").getAsString();
			long contactNo = o.get("contactNo").getAsLong();
			String status = o.get("status").getAsString();
			Double amountOwed = o.get("amountOwed").getAsDouble();

			JsonObject c = o.get("company").getAsJsonObject();
			int cId = c.get("companyId").getAsInt();
			Company company;
			try {
				company = ctrl.getCompany(cId);
				Employee e = new Employee(password, name, email, contactNo, company);
				e.setStatus(status);
				e.setAmountOwed(amountOwed);
				return e;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			return null;
		}
	};

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditUserServlet() {
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

		Gson gson = new GsonBuilder().registerTypeAdapter(Employee.class, empDeserialize).create();

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
