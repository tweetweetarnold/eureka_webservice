package servlet;
import model.FoodOrder;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.FoodOrderController;
import java.util.*;
/**
 * Servlet implementation class ViewWeeklyConsolidatedPaymentServlet
 */
@WebServlet("/ViewWeeklyConsolidatedPaymentServlet")
public class ViewWeeklyConsolidatedPaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewWeeklyConsolidatedPaymentServlet() {
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
		Calendar cal = Calendar.getInstance(); 
		Date today = new Date();
		//please input new date. Date should be the starting sunday for the week of orders you wish to retrieve
		ArrayList<FoodOrder> foodOrderList = foodOrderController.getFoodOrderForUsernameWeek(username, today);
		
	}

}
