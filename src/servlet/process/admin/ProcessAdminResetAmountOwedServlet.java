package servlet.process.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.EmployeeController;
import dao.EmployeeDAO;

/**
 * Servlet implementation class ProcessAdminResetAmountOwedServlet
 */
@WebServlet("/ProcessAdminResetAmountOwedServlet")
public class ProcessAdminResetAmountOwedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessAdminResetAmountOwedServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}
 
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		EmployeeController employeeController = new EmployeeController();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		employeeController.updateAmountOwed(employeeDAO.getAllEmployeesFromCompany(2), 0.0);
		response.sendRedirect("/eureka_webservice/admin/login.jsp");
	}

}
