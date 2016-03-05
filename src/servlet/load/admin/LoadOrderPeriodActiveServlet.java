package servlet.load.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

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
 * Servlet implementation class GetTodayOrdersServlet
 */
@WebServlet("/LoadOrderPeriodActiveServlet")
public class LoadOrderPeriodActiveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadOrderPeriodActiveServlet() {
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
		FoodOrderController foodOrderCtrl = new FoodOrderController();
		OrderPeriodController orderPeriodCtrl = new OrderPeriodController();

		try {
			LinkedHashMap<OrderPeriod, Object[]> map = new LinkedHashMap<OrderPeriod, Object[]>();
			ArrayList<OrderPeriod> openedPeriodList = orderPeriodCtrl.getAllOpenedPeriods();

			for (OrderPeriod period : openedPeriodList) {
				Object[] arr = new Object[2];
				HashMap<Employee, ArrayList<FoodOrderItem>> noGroup = foodOrderCtrl
						.getAllFoodOrderOfOrderPeriod(period);

				ArrayList<FoodDisplayObject> groupedByStall = foodOrderCtrl
						.getAllFoodOrderOfOrderPeriodGroupedByStall(period);

				System.out.println("nogroup size: " + noGroup.size());

				arr[0] = noGroup;
				arr[1] = groupedByStall;
				map.put(period, arr);
			}

			session.setAttribute("orderPeriodMap", map);
			response.sendRedirect("/eureka_webservice/admin/orderperiod/active.jsp");

		} catch (Exception e) {
			System.out.println("Error occurred at LoadOrderPeriodActiveServlet");
			e.printStackTrace();
			response.sendRedirect("/eureka_webservice/admin/orderperiod/active.jsp");
		}
	}
}
