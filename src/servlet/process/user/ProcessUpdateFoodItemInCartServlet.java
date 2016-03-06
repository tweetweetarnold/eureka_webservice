package servlet.process.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.FoodOrderItem;

/**
 * Servlet implementation class ProcessUpdateFoodItemInCartServlet
 */
@WebServlet("/ProcessUpdateFoodItemInCartServlet")
public class ProcessUpdateFoodItemInCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessUpdateFoodItemInCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); 
		String newQuantity = request.getParameter("quantity");
		String count = request.getParameter("count");
		List<FoodOrderItem> myFoodOrderItems= (List<FoodOrderItem>) session.getAttribute("myFoodOrderItems");
		myFoodOrderItems.get(Integer.parseInt(count)-1).setQuantity(Integer.parseInt(newQuantity));
//		for(FoodOrderItem f : myFoodOrderItems){
//			if(Integer.parseInt(foodOrderItemId)== f.getFoodOrderItemId()){
//				System.out.println(foodOrderItemId);
//				f.setQuantity(Integer.parseInt(newQuantity));
//			}
//		}
		session.setAttribute("success",  "The Cart has been updated!");

		response.sendRedirect("/eureka_webservice/pages/cart.jsp");
	}

}
