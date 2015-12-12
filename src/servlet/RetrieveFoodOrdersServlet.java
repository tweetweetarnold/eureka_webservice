package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.FoodDisplayObject;
import controller.FoodOrderController;

/**
 * Servlet implementation class retrieveFoodOrdersServlet
 */
@WebServlet("/RetrieveFoodOrdersServlet")
public class RetrieveFoodOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RetrieveFoodOrdersServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		//date processing
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date laterDate = cal.getTime();

		laterDate.setHours(10);
		laterDate.setMinutes(0);
		cal.add(Calendar.DATE, -1);
		Date earlierDate = cal.getTime();
		earlierDate.setHours(10);
		earlierDate.setMinutes(0);
		

		FoodOrderController foodOrderController = new FoodOrderController();
		ArrayList<FoodDisplayObject> foodOrderList = new ArrayList<FoodDisplayObject>(
				foodOrderController.getFoodOrderforCutOff(earlierDate, laterDate));

		session.setAttribute("foodOrders", foodOrderList);
		response.sendRedirect("adminFoodOrderByStall.jsp");

	}

}
