package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.FoodOrderItem;
import controller.FoodOrderController;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

		FoodOrderController foodOrderController = new FoodOrderController();

		// date processing
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date laterDate = cal.getTime();

		laterDate.setHours(10);
		laterDate.setMinutes(0);
		cal.add(Calendar.DATE, -1);
		Date earlierDate = cal.getTime();
		earlierDate.setHours(10);
		earlierDate.setMinutes(0);

		HashMap<Integer, ArrayList<FoodOrderItem>> hashMapToReturn = foodOrderController
				.getFoodOrderItemsForStall(earlierDate, laterDate);

		session.setAttribute("foodOrderItemByStall", hashMapToReturn);
		response.sendRedirect("adminFoodOrderByStall.jsp");

	}
}
