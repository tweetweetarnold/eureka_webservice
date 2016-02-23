package servlet.load.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Employee;

import com.google.gson.Gson;

import controller.EmployeeController;

/**
 * Servlet implementation class GetUserByEmailServlet
 */
@WebServlet("/GetUserByEmailServlet")
public class GetUserByEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetUserByEmailServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("applcation/json");
		PrintWriter out = response.getWriter();

		EmployeeController employeeCtrl = new EmployeeController();
		String email = request.getParameter("email");
		System.out.println("email: " + email);

		Employee e = employeeCtrl.getEmployeeByEmail(email);

		Gson gson = new Gson();
		out.print(gson.toJson(e));

	}

}
