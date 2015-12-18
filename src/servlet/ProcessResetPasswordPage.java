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
@WebServlet("/ProcessResetPasswordPage")
public class ProcessResetPasswordPage extends HttpServlet {
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessResetPasswordPage() {
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
		HttpSession session = request.getSession();
		AESAlgorithm aes = new AESAlgorithm();
		// TODO Auto-generated method stub
		try{
			String email = (String) request.getParameter("email");
			//String[] emailString = urlString.split("/");
			//String email = emailString[0];
			String newEmail = email.replaceAll(" ","+");
			String eDecrypt = aes.decrypt(newEmail);
			session.setAttribute("email", eDecrypt);
			response.sendRedirect("resetPasswordRedirect.jsp");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
			response.sendRedirect("resetPassword.jsp");
		}
	}
}
