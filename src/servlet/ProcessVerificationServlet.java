package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.validator.routines.EmailValidator;

import controller.AccessController;
import dao.EmployeeDAO;
import model.Employee;
import services.AESAlgorithm;
import services.EmailGenerator;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/ProcessVerificationServlet")
public class ProcessVerificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcessVerificationServlet() {
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
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		System.out.println("*********** VerificationServlet ***********");

		HttpSession session = request.getSession();
		AESAlgorithm aes = new AESAlgorithm();

		try {
			/*AccessController accessController = new AccessController();		
			String password = (String)session.getAttribute("password");
			String employeeName = (String)session.getAttribute("employeeName");
			String email = (String)session.getAttribute("email");
			Long contactNumber = (Long)session.getAttribute("contactNumber");
			String companyCode = (String)session.getAttribute("companyCode");
			*/
			EmployeeDAO employeedao = new EmployeeDAO();
			String email = (String)request.getParameter("email");
			String newEmail = email.replaceAll(" ","+");
			String eDecrypt = aes.decrypt(newEmail);
			String[] emailString = eDecrypt.split("&");
			String verifiedEmail = emailString[0];
			Employee employee = employeedao.getEmployeeByEmail(eDecrypt);
			employee.setEmail(verifiedEmail);
			employeedao.saveEmployee(employee);
			Employee employeeUnverified = employeedao.getEmployeeByEmail(eDecrypt);
			employeedao.deleteEmployee(employeeUnverified);
			session.setAttribute("success","Your email has been verified. You may login now. Email:" + verifiedEmail);
			response.sendRedirect("login.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("error", e.getMessage());
			response.sendRedirect("login.jsp");
		}
	}
}
