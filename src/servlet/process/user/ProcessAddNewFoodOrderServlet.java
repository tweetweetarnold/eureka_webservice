package servlet.process.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Employee;
import model.FoodOrder;
import model.FoodOrderItem;
import model.OrderWindow;
import value.StringValues;
import controller.FoodOrderController;
import controller.OrderWindowController;
import dao.EmployeeDAO;

/**
 * Servlet implementation class AddNewFoodOrder
 */
@WebServlet("/ProcessAddNewFoodOrderServlet")
public class ProcessAddNewFoodOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EmployeeDAO employeeDAO = new EmployeeDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcessAddNewFoodOrderServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		System.out.println("****** ProcessAddNewFoodOrderServlet ******");
		HttpSession session = request.getSession();

		try {
			// Retrieve myFoodOrders and User
			OrderWindowController owController = new OrderWindowController();
			FoodOrderController controller = new FoodOrderController();
			OrderWindow window = (OrderWindow) session.getAttribute("orderWindow");
			Employee employee = (Employee) session.getAttribute("user");
			System.out.println("Employee retrieved");
			if (!controller.checkForExistingOrder(employee, window)) {

				List<FoodOrderItem> myFoodOrderItems = (List<FoodOrderItem>) session
						.getAttribute("myFoodOrderItems");
				System.out.println("MyFoodOrderItems retrieved");
				Set<FoodOrderItem> hashMyFoodOrderItems = new HashSet<>(myFoodOrderItems);

				FoodOrder myFoodOrder = new FoodOrder(StringValues.ORDER_SUBMITTED, employee, null,
						window);
				for (FoodOrderItem item : hashMyFoodOrderItems) {
					item.setFoodOrder(myFoodOrder);
					out.println("size: " + item.getModifierChosenList().size());
				}
				myFoodOrder.setFoodOrderList(hashMyFoodOrderItems);
				System.out.println("New FoodOrder created");
				double discount = myFoodOrder.getOrderWindow().getPriceModifierList().get(0).getValue();
				double amountToAdd = myFoodOrder.getTotalPriceBeforePriceModifiers()
						+ discount;
				if (amountToAdd > 0) {
					employee.setAmountOwed(employee.getAmountOwed() + amountToAdd);
				}
				employeeDAO.updateEmployee(employee);
				System.out.println("Employee amount owed updated");

				// Process new FoodOrder

				controller.addFoodOrder(myFoodOrder);
				System.out.println("New FoodOrder added to database");

				session.removeAttribute("myFoodOrderItems");
				System.out.println("myFoodOrderItems cleared");

				session.setAttribute("success", "Your order has been submitted!");
				response.sendRedirect("/eureka_webservice/pages/cart.jsp");
			} else {
				session.removeAttribute("myFoodOrderItems");
				throw new Exception("You have already submitted your order for today!");
			}
		} catch (NullPointerException e) {
			session.setAttribute("error", "Add an item to cart to checkout");
			response.sendRedirect("/eureka_webservice/pages/cart.jsp");
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
			session.setAttribute("error", e.getMessage());
			response.sendRedirect("/eureka_webservice/pages/cart.jsp");
		}

	}
}
