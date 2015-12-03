package servlet;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.FoodOrderController;
import controller.UserController;
import model.Employee;
import model.FoodOrder;

/**
 * Servlet implementation class processGetEmployeeServlet
 */
@WebServlet("/ProcessAdminGetEmployeeServlet")
public class ProcessAdminGetEmployeeServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessAdminGetEmployeeServlet2() {
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
		String email = request.getParameter("email");
		FoodOrderController foodOrderController = new FoodOrderController();
		List<FoodOrder> foodOrderList= foodOrderController.getFoodOrderSet(email);
		System.out.println(foodOrderList.size());
		UserController userController = new UserController();
		Employee employee = userController.retrieveEmployeeViaEmail(email);
		RequestDispatcher rd = request.getRequestDispatcher("adminProfile.jsp");
		request.setAttribute("name", employee.getName());
		//USERNAME!?@#
		request.setAttribute("username", employee.getName());
		request.setAttribute("email", employee.getEmail());
		request.setAttribute("contactNumber", employee.getContactNo());
		request.setAttribute("status", employee.getStatus());
		request.setAttribute("orderHistory", foodOrderList);
		DecimalFormat df = new DecimalFormat("####0.00");
		request.setAttribute("amountOwed", df.format(employee.getAmountOwed()));
	
		
		rd.forward(request,response);
	}

}
