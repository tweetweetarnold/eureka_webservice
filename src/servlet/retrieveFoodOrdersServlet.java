package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class retrieveFoodOrdersServlet
 */
@WebServlet("/retrieveFoodOrdersServlet")
public class retrieveFoodOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public retrieveFoodOrdersServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		FoodOrderController foodOrderController = new FoodOrderController();
//		ArrayList<FoodOrder> foodOrderList = new ArrayList<FoodOrder>(foodOrderController.getFoodOrderforCutOff());
//		
//		RequestDispatcher rd = request.getRequestDispatcher("retrieveFoodOrders.jsp");
//		request.setAttribute("foodOrders", foodOrderList);
//		rd.forward(request,response);
		
	}

}
