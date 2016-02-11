package servlet.process.admin;

import java.io.IOException;
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
 * Servlet implementation class ProcessAdminDeleteUserServlet
 */
@WebServlet("/ProcessAdminDeleteUserServlet")
public class ProcessAdminDeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessAdminDeleteUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		EmployeeController employeeController = new EmployeeController();
		AccessController accessController = new AccessController();
		
		String email = request.getParameter("emailID");
		System.out.println("******Deleting: " + email);
		try {
			
			Employee employee = employeeController.retrieveEmployeeViaEmail(email);
			
			employeeController.removeEmployee(employee);
			accessController.constructDeleteUserNotificationEmail(email);
			
			session.setAttribute("success", employee.getName() + " has been deleted." );
			
			response.sendRedirect("/eureka_webservice/GetAllUsersServlet");
		} catch (Exception e){
			e.printStackTrace();
		}
	}

}
