package servlet.process.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.AccessController;
import controller.EmployeeController;
import model.Employee;

/**
 * Servlet implementation class ProcessChangePhoneNumber
 */
@WebServlet("/ProcessChangePhoneNumberServlet")
public class ProcessChangePhoneNumberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcessChangePhoneNumberServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		System.out.println("");
		System.out.println("****** ProcessChangePhoneNumberServlet ******");
		out.println("ProcessChangePhoneNumberServlet");
		HttpSession session = request.getSession();
		String userInput = request.getParameter("newNumber");
		
		AccessController accessController = new AccessController();
		
		try {
			Employee employee = (Employee) session.getAttribute("user");
			ArrayList<String> checkNewContactNumberError = accessController.checkContactNoRequirements(userInput);
			if (checkNewContactNumberError.isEmpty()) {
				if (accessController.updateEmployeeContactNumber(employee, userInput)) {
					System.out.println("new contact no updated");
					session.setAttribute("success", "Contact number has been updated!");
					response.sendRedirect("PLEASECHANGEME.jsp");
				}
			} else {
				String msg = "";
				for (String s : checkNewContactNumberError) {
					msg = s + "<br>" + msg;
				}
				throw new Exception(msg);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
			session.setAttribute("error",
					"Oops! Something went wrong! Please check your inputs.");
			session.setAttribute("userInput", userInput);
			response.sendRedirect("PLEASECHANGEME.jsp");
		}

}
}