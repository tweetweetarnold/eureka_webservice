package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import controller.FoodOrderController;
import model.FoodOrderItem;
import net.minidev.json.JSONObject;


/**
 * Servlet implementation class retrieveFoodOrdersServlet
 */
@WebServlet("/retrieveFoodOrdersServlet")
public class retrieveFoodOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public retrieveFoodOrdersServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		FoodOrderController foodOrderController = new FoodOrderController();
		JSONArray jsonArray = new JSONArray();
		ArrayList<String> userList = new ArrayList<String>();
		HashMap tempFoodOrderHash = foodOrderController.getFoodOrderToday();
		Iterator iter = tempFoodOrderHash.keySet().iterator();
		while(iter.hasNext()){
			String username = (String)iter.next();
			if(!username.equals("totalPrice")){
				userList.add(username);
			}
		}
		JSONObject userRowSpan = new JSONObject();
		for(String s : userList){
			FoodOrderItem tempFoodOrderItem = (FoodOrderItem)tempFoodOrderHash.get(s);
			int userRowSpan = tempFoodOrderItem.getFood().getModifiersSize();
			
		}
		
	}

}
