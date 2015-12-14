package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;

import value.StringValues;
import controller.FoodOrderController;

/**
 * Servlet implementation class GetTodayOrdersServlet
 */
@WebServlet("/LoadOrderWindowOpenedServlet")
public class LoadOrderWindowOpenedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadOrderWindowOpenedServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		FoodOrderController foodOrderController = new FoodOrderController();
		try {
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

			// ************************ to remove
			laterDate = new Date();
			earlierDate = new DateTime().minusDays(1).toDate();
			// ************************ to remove

			session.setAttribute(StringValues.SESSION_ORDERS_WINDOW_OPENED_NOGROUP,
					foodOrderController.getFoodOrderToday());

			session.setAttribute(StringValues.SESSION_ORDERS_WINDOW_OPENED_STALLS,
					foodOrderController.getFoodOrderForCutOff(earlierDate, laterDate));

			response.sendRedirect("adminFoodOrders.jsp");

		} catch (Exception e) {
			System.out.println("Error occurred at LoadOrderWindowOpenedServlet");
			e.printStackTrace();
			response.sendRedirect("adminFoodOrders.jsp");
		}
	}
}
