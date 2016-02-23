package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.EmployeeController;
import dao.CompanyDAO;

/**
 * Servlet implementation class DemoSuspendEmployeeXDDServlet
 */
@WebServlet("/DemoSuspendEmployeeXDDServlet")
public class DemoSuspendEmployeeXDDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DemoSuspendEmployeeXDDServlet() {
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
		CompanyDAO companyDAO = new CompanyDAO();
		EmployeeController employeeController = new EmployeeController();
		employeeController.suspendOverduePaymentFromCompany(companyDAO.getCompanyByCompanyCode("XDD123"));
		
		
	}
}
