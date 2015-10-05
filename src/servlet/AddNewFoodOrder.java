package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import model.FoodOrder;

import com.google.gson.Gson;

import controller.FoodOrderController;

/**
 * Servlet implementation class AddNewFoodOrder
 */
@WebServlet("/AddNewFoodOrder")
public class AddNewFoodOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewFoodOrder() {
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
//		FoodOrder tempFoodOrder = (FoodOrder) request.getAttribute("foodOrder");
//		String arnold = (String) request.getParameter("arnold");
//		
//		System.out.println("arnold: " + arnold);
//		System.out.println("Temp: " + tempFoodOrder);
//		FoodOrderController foodOrderController = new FoodOrderController();
//		Gson gson = new Gson();
////		FoodOrder foodOrder = gson.fromJson(tempFoodOrder, FoodOrder.class );
//		FoodOrder foodOrder = tempFoodOrder;
//		foodOrderController.addFoodOrder(foodOrder);
		
		HttpSession session = request.getSession();
		JSONObject foodOrder = (JSONObject) session.getAttribute("foodOrder");
		FoodOrder order = (FoodOrder) foodOrder.get("foodOrder");
		System.out.println("order: " + order);
		FoodOrderController controller = new FoodOrderController();
		controller.addFoodOrder(order);
		System.out.println("foodOrder added to database");
		
		response.sendRedirect("cart.jsp");
		
	}

}
