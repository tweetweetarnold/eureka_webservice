package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Employee;
import controller.AdminController;

/**
 * Servlet implementation class GetEmployeesPaymentOwedServlet
 */
@WebServlet("/GetEmployeesPaymentOwedServlet")
public class GetEmployeesPaymentOwedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetEmployeesPaymentOwedServlet() {
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
		String paymentStatus = (String)request.getParameter("paymentStatus");
		AdminController adminController = new AdminController();
		List<Employee> employeeList = adminController.getListOfOwedPayment(paymentStatus);
		System.out.println(employeeList.size());
		RequestDispatcher rd = request.getRequestDispatcher("/testRetrieveEmployeeOwedList.jsp");
		request.setAttribute("employeeList", employeeList);
		rd.forward(request, response);
	}

}
