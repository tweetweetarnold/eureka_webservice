package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Food;
import model.FoodOrder;
import model.FoodOrderItem;

/**
 * Servlet implementation class LoadSampleTestDataServlet
 */
@WebServlet("/LoadSampleTestDataServlet")
public class LoadSampleTestDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadSampleTestDataServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request,response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		
		Food chickenRice = new Food("Chicken Rice", "Damn nice", 3.50, null, new Date());
		Food duckRice = new Food("Duck Rice", "Damn nice", 3.50, null, new Date());
		Food wantonMee = new Food("Wanton Mee", "Damn nice", 3.50, null, new Date());
		
		FoodOrderItem item1 = new FoodOrderItem(null, chickenRice, 1, 3.50, null, new Date());
		FoodOrderItem item2 = new FoodOrderItem(null, duckRice, 1, 3.50, null, new Date());
		FoodOrderItem item3 = new FoodOrderItem(null, wantonMee, 1, 3.50, null, new Date());
		Set<FoodOrderItem> set = new HashSet<>();
		set.add(item1);
		set.add(item2);
		set.add(item3);
		
		FoodOrder order = new FoodOrder("ok", null, null, set, new Date());
		
		HttpSession session = request.getSession();
		session.setAttribute("foodOrder", order);
		
		System.out.println("done");
		response.sendRedirect("cart.jsp");
	}

}
