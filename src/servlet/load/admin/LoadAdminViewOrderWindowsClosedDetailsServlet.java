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
 * Servlet implementation class LoadAdminViewOrderWindowsClosedDetailsServlet
 */
@WebServlet("/LoadAdminViewOrderWindowsClosedDetailsServlet")
public class LoadAdminViewOrderWindowsClosedDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadAdminViewOrderWindowsClosedDetailsServlet() {
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

		String windowIdString = request.getParameter("windowId");

		try {
			Integer windowId = Integer.parseInt(windowIdString);

			OrderWindow window = orderWindowController.getOrderWindow(windowId);
			System.out.println("windowID here: " + window.getWindowId());

			HashMap<Employee, ArrayList<FoodOrderItem>> noGroup = foodOrderController
					.getAllFoodOrderOfOrderWindow(window);

			ArrayList<FoodDisplayObject> groupedByStall = foodOrderController
					.getAllFoodOrderOfOrderWindowGroupedByStall(window);

			System.out.println("nogroup size: " + noGroup.size());

			session.setAttribute("window", window);
			session.setAttribute("noGroup", noGroup);
			session.setAttribute("groupedByStall", groupedByStall);

			response.sendRedirect("/eureka_webservice/admin/orderwindow/closed-details.jsp");

		} catch (Exception e) {
			System.out.println("Error occurred at LoadAdminViewOrderWindowsClosedDetailsServlet");
			e.printStackTrace();
			response.sendRedirect("/eureka_webservice/admin/orderwindow/closed-details.jsp");
		}
	}
}
