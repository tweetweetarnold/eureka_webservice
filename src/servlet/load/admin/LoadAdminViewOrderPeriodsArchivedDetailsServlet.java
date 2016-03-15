package servlet.load.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Employee;
import model.FoodDisplayObject;
import model.FoodOrderItem;
import model.OrderPeriod;
import controller.FoodOrderController;
import controller.OrderPeriodController;

/**
 * Servlet implementation class LoadAdminViewOrderPeriodsArchivedDetailsServlet
 */
@WebServlet("/LoadAdminViewOrderPeriodsArchivedDetailsServlet")
public class LoadAdminViewOrderPeriodsArchivedDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadAdminViewOrderPeriodsArchivedDetailsServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		FoodOrderController foodOrderController = new FoodOrderController();
		OrderPeriodController orderPeriodController = new OrderPeriodController();

		String periodIdString = request.getParameter("periodId");

		try {
			Integer periodId = Integer.parseInt(periodIdString);

			OrderPeriod period = orderPeriodController.getOrderPeriod(periodId);
			System.out.println("periodID here: " + period.getPeriodId());

			HashMap<Employee, ArrayList<FoodOrderItem>> noGroup = foodOrderController
					.getAllFoodOrderOfOrderPeriod(period);

			ArrayList<FoodDisplayObject> groupedByStall = foodOrderController
					.getAllFoodOrderOfOrderPeriodGroupedByStall(period);

			System.out.println("nogroup size: " + noGroup.size());

			session.setAttribute("period", period);
			session.setAttribute("noGroup", noGroup);
			session.setAttribute("groupedByStall", groupedByStall);

			response.sendRedirect("/eureka_webservice/admin/orderperiod/archived-details.jsp");

		} catch (Exception e) {
			System.out.println("Error occurred at LoadAdminViewOrderPeriodsArchivedDetailsServlet");
			e.printStackTrace();
			response.sendRedirect("/eureka_webservice/admin/orderperiod/archived-details.jsp");
		}
	}
}
