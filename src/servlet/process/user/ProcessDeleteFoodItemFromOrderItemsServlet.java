package servlet.process.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.FoodOrderItem;

/**
 * Servlet implementation class ProcessDeleteFoodItemFromOrderItemsServlet
 */
@WebServlet("/ProcessDeleteFoodItemFromOrderItemsServlet")
public class ProcessDeleteFoodItemFromOrderItemsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcessDeleteFoodItemFromOrderItemsServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");

			out.println(request.getParameter("foodPosition"));
			int pos = Integer.parseInt((String) request.getParameter("foodPosition"));

			HttpSession session = request.getSession();
			List<FoodOrderItem> myFoodOrderItems = (List<FoodOrderItem>) session
					.getAttribute("myFoodOrderItems");
			System.out.println("MyFoodOrderItems is retrieved");

			myFoodOrderItems.remove(pos);
			System.out.println("FoodOrderItem has been removed");

			response.sendRedirect("/eureka_webservice/pages/cart.jsp");
		} catch (Exception e) {

		}

	}

}
