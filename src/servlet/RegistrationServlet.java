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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}
	
	public void process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// request.setCharacterEncoding("UTF-8");
		// response.setCharacterEncoding("UTF-8");
		try {
			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println();
			RegistrationController registrationController = new RegistrationController();
			String employeeName = (String) request.getParameter("employeeName");
			String username = (String) request.getParameter("username");
			String password = (String) request.getParameter("password");
			String confirmPwd = (String) request.getParameter("confirmPwd");
			String contactNum = (String) request.getParameter("contactNumber");
			String bankAcc = (String) request.getParameter("bankAcc");
			String companyName = (String) request.getParameter("company");
			if (!employeeName.equals("") && !username.equals("") && !password.equals("") && !confirmPwd.equals("") && !contactNum.equals("") && !bankAcc.equals("") && !companyName.equals("") && password.equals(confirmPwd)) {
				long contactNumber = Long.parseLong(contactNum);
				int generatedEmployeeId = registrationController.registerUser(username, password, employeeName, bankAcc, contactNumber, companyName);
				out.println("You have successfully registered to our application. Your login id is : " + generatedEmployeeId);
			} else {
				response.sendRedirect("/eureka_webservice/registration.jsp");
			}
			
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	//}
		}

}
