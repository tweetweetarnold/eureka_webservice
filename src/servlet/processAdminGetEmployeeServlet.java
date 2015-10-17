package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.UserController;
import model.Employee;

/**
 * Servlet implementation class processGetEmployeeServlet
 */
@WebServlet("/processAdminGetEmployeeServlet")
public class processAdminGetEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public processAdminGetEmployeeServlet() {
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
		String username = request.getParameter("username");
		UserController userController = new UserController();
		Employee employee = userController.retrieveEmployeeViaUsername(username);
		RequestDispatcher rd = request.getRequestDispatcher("adminProfile.jsp");
		request.setAttribute("Name", employee.getName());
		request.setAttribute("username", username);
		request.setAttribute("email", employee.getEmail());
		request.setAttribute("contactNumber", employee.getContactNo());
		request.setAttribute("orderHistory", employee.getOrderHistory());
		request.setAttribute("status", employee.getStatus());
		request.setAttribute("amountOwed", employee.getAmountOwed());
		rd.forward(request,response);
	}

}
