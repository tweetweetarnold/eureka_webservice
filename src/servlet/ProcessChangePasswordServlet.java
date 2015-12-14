package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.EmployeeController;
import model.Employee;
import services.PasswordService;

/**
 * Servlet implementation class ProcessChangePasswordServlet
 */
@WebServlet("/ProcessChangePasswordServlet")
public class ProcessChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessChangePasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		System.out.println("");
		System.out.println("****** ProcessChangePasswordServlet ******");
		out.println("ProcessChangePasswordServlet");
		HttpSession session = request.getSession();
		String originalPassword = request.getParameter("originalPassword");
		String newPassword = request.getParameter("newPassword");
		String newPasswordConfirmation = request.getParameter("newPasswordConfirmation");
		
		try{
			Employee employee = (Employee) session.getAttribute("user");
			String password = PasswordService.decryptPassword(employee.getPassword());
			if(password.equals(originalPassword)){
				if(newPassword.equals(newPasswordConfirmation)){
					employee.setPassword(PasswordService.encryptPassword(newPassword));
					EmployeeController userController = new EmployeeController();
					userController.updateEmployee(employee);
					session.setAttribute("success", "Password has been updated!");
					response.sendRedirect("PLEASECHANGEME.jsp");
				}else{
					throw new Exception("new passwords do not match");
				}
			}else{
				throw new Exception("unable to verify user's password");
			}
			
			
			
			
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
			session.setAttribute("error",
					"Oops! Something went wrong! Please check your inputs.");
			response.sendRedirect("PLEASECHANGEME.jsp");
		}
	}
}
