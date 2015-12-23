package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.EmployeeController;
import dao.EmployeeDAO;
import model.Employee;
import services.AESAlgorithm;
import services.EmailGenerator;
import services.PasswordService;

/**
 * Servlet implementation class ProcessResetPasswordServlet
 */
@WebServlet("/ProcessResetPasswordRedirectServlet")
public class ProcessResetPasswordRedirectServlet extends HttpServlet {
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessResetPasswordRedirectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		System.out.println("");
		System.out.println("****** ProcessResetPasswordRedirectServlet ******");
		out.println("ProcessResetPasswordRedirectServlet");
		HttpSession session = request.getSession();
		
		String email = (String) session.getAttribute("email");
		String newPassword = request.getParameter("newPassword");
		String newPasswordConfirmation = request.getParameter("confirmPwd");
		
		try{
			AESAlgorithm aesAlgo = new AESAlgorithm();
			EmployeeController userController = new EmployeeController();
			Employee employee = userController.retrieveEmployeeViaEmail(email);
				if(newPassword.equals(newPasswordConfirmation)){
					String encryptedPassword = aesAlgo.encrypt(email + newPassword);
					employee.setPassword(encryptedPassword);
					userController.updateEmployee(employee);
					session.setAttribute("success", "Password has been updated!");
					response.sendRedirect("login.jsp");
				}else{
					throw new Exception("New password does not match");
				}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
			session.setAttribute("error",
					e.getMessage());
			response.sendRedirect("resetPasswordRedirect.jsp");
		}
	}
}