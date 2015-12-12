package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.FoodOrderController;
import model.FoodDisplayObject;

/**
 * Servlet implementation class ChineseRetrieveFoodOrderServlet
 */
@WebServlet("/ChineseRetrieveFoodOrderServlet")
public class ChineseRetrieveFoodOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChineseRetrieveFoodOrderServlet() {
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
		ArrayList<FoodDisplayObject> foodOrderList = new ArrayList<FoodDisplayObject>(foodOrderController.getFoodOrderForCutOff());
		
		RequestDispatcher rd = request.getRequestDispatcher("adminFoodOrderByStallCN.jsp");
		request.setAttribute("foodOrders", foodOrderList);
		rd.forward(request,response);
		
	}

}
