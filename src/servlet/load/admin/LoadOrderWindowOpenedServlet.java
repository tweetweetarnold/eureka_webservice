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
import model.OrderWindow;
import controller.FoodOrderController;
import controller.OrderWindowController;

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
		OrderWindowController orderWindowController = new OrderWindowController();

		try {
			LinkedHashMap<OrderWindow, Object[]> map = new LinkedHashMap<OrderWindow, Object[]>();
			ArrayList<OrderWindow> openedWindowList = orderWindowController.getAllOpenedWindows();

			for (OrderWindow window : openedWindowList) {
				Object[] arr = new Object[2];
				HashMap<Employee, ArrayList<FoodOrderItem>> noGroup = foodOrderController
						.getAllFoodOrderOfOrderWindow(window);

				ArrayList<FoodDisplayObject> groupedByStall = foodOrderController
						.getAllFoodOrderOfOrderWindowGroupedByStall(window);

				System.out.println("nogroup size: " + noGroup.size());

				arr[0] = noGroup;
				arr[1] = groupedByStall;
				map.put(window, arr);
			}

			session.setAttribute("orderWindowMap", map);
			response.sendRedirect("/eureka_webservice/admin/orderwindow/opened.jsp");

		} catch (Exception e) {
			System.out.println("Error occurred at LoadOrderWindowOpenedServlet");
			e.printStackTrace();
			response.sendRedirect("/eureka_webservice/admin/orderwindow/opened.jsp");
		}
	}
}
