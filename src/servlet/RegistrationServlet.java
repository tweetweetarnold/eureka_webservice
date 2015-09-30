package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.RegistrationController;
import model.Employee;
import services.PasswordService;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// request.setCharacterEncoding("UTF-8");
		// response.setCharacterEncoding("UTF-8");

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("RegistrationServlet");

		try {
			RegistrationController registrationController = new RegistrationController();
			String employeeName = (String) request.getParameter("name");
			String username = (String) request.getParameter("username");
			String password = (String) request.getParameter("password");
			String confirmPwd = (String) request.getParameter("confirmPwd");
			String contactNum = (String) request.getParameter("contactNumber");
			String bankAcc = (String) request.getParameter("bankAcc");
			String companyName = (String) request.getParameter("company");

			// Check user parameters
			boolean validation = !employeeName.equals("")
					&& !username.equals("") && !password.equals("")
					&& !confirmPwd.equals("") && (password.length() >= 7)
					&& (confirmPwd.length() >= 7) && (contactNum.length() >= 8)
					&& !contactNum.equals("") && !bankAcc.equals("")
					&& !companyName.equals("") && password.equals(confirmPwd);

			validation = true; // for testing
			if (validation) {
				// long contactNumber = Long.parseLong(contactNum);
				long contactNumber = 123; // for testing
				int generatedEmployeeId = registrationController.registerUser(
						username, password, employeeName, bankAcc,
						contactNumber, companyName);
				out.println("You have successfully registered to our application. Your login id is : "
						+ generatedEmployeeId);

				// For testing purposes,upon successful registration, it will
				// stay on the servlet page for 10 seconds
				// to tell user to login using the generated employee id and
				// then direct to login page
				// response.setHeader("Refresh",
				// "10; URL=/eureka_webservice/login.jsp");
				// response.sendRedirect("/eureka_webservice/login.jsp");

			} else {
				out.println("here2");
				// response.sendRedirect("/eureka_webservice/registration.jsp");
			}

		} catch (Exception e) {
			out.println("here1");
			out.println(e.getMessage());
			e.printStackTrace();
		}
		// }
	}
}
