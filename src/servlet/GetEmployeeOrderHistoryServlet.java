package servlet;

import java.io.IOException;
import java.util.List;
import java.util.Set;

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
 * Servlet implementation class GetEmployeeOrderHistoryServlet
 */
@WebServlet("/GetEmployeeOrderHistoryServlet")
public class GetEmployeeOrderHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetEmployeeOrderHistoryServlet() {
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
		String username = (String)request.getParameter("username");
		FoodOrderController foodOrderController = new FoodOrderController();
		List<FoodOrder> foodOrderList = foodOrderController.getFoodOrderSet(username);
		System.out.println(foodOrderList.size());
		RequestDispatcher rd = request.getRequestDispatcher("retrieveEmployeeOrderHistory.jsp");
		request.setAttribute("foodOrderList", foodOrderList);
		rd.forward(request, response);
	}

}
