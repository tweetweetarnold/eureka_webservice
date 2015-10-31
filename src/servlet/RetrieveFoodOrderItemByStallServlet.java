package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.FoodOrderController;
import model.FoodOrderItem;

/**
 * Servlet implementation class RetrieveFoodOrderItemByStallServlet
 */
@WebServlet("/RetrieveFoodOrderItemByStallServlet")
public class RetrieveFoodOrderItemByStallServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetrieveFoodOrderItemByStallServlet() {
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
		FoodOrderController foodOrderController = new FoodOrderController();
		HashMap<Integer, ArrayList<FoodOrderItem>> hashMapToReturn = foodOrderController.getFoodOrderItemsForStall();
		RequestDispatcher rd = request.getRequestDispatcher("adminFoodOrderByStall.jsp");
		request.setAttribute("foodOrderItemByStall", hashMapToReturn);
		rd.forward(request,response);
	}

}
