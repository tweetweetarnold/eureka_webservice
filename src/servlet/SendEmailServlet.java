package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.AdminController;
import model.Employee;
import services.EmailGenerator;

/**
 * Servlet implementation class SendEmailServlet
 */
@WebServlet("/SendEmailServlet")
public class SendEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SendEmailServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdminController adminController = new AdminController();
		List<Employee> employeeOweList = adminController.getListOfOwedPayment("Owe");
		if (employeeOweList.size() >= 1) { // ensure that the list is not 0 then
											// proceed to add recipients and
											// send
			String[] emailAddress = new String[employeeOweList.size()];
			for (int i = 0; i < employeeOweList.size(); i++) {
				Employee e = employeeOweList.get(i);
				String employeeEmail = e.getEmail();
				emailAddress[i] = employeeEmail;
			}

			EmailGenerator javaEmail = new EmailGenerator();
			try {
				javaEmail.setMailServerProperties();
				javaEmail
						.sendEmail(
								"Outstanding Payment",
								"Dear User,</br>You have an outsanding payments. Please log in to the DABAO App to resolve your outstanding payments.</br>You will not be able to place new orders until these outstanding payments have been resolved.</br></br>Regards,</br>Admin</br></br>This is a system-generated email; please DO NOT REPLY to this email.",
								emailAddress);
				response.sendRedirect("adminHomepageTest.jsp");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
