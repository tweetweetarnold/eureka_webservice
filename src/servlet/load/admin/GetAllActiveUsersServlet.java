package servlet.load.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Employee;

import com.google.gson.Gson;

import controller.EmployeeController;

/**
 * Servlet implementation class GetAllActiveUsersServlet
 */
@WebServlet("/GetAllActiveUsersServlet")
public class GetAllActiveUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetAllActiveUsersServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		Gson gson = new Gson();

		EmployeeController employeeCtrl = new EmployeeController();

		ArrayList<Employee> list = employeeCtrl.getAllNonDestroyedEmployees();
		out.print(gson.toJson(list));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
