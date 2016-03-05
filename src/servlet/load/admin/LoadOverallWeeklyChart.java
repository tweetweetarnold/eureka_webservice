package servlet.load.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

import controller.FoodOrderController;
import model.FoodOrder;

/**
 * Servlet implementation class LoadOverallWeeklyChart
 */
@WebServlet("/LoadOverallWeeklyChart")
public class LoadOverallWeeklyChart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadOverallWeeklyChart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("image/jpeg");
		ServletOutputStream os = response.getOutputStream();
		HttpSession session = request.getSession();
		try {
			

			FoodOrderController foodOrderController = new FoodOrderController();
			String week = request.getParameter("week");
			TreeMap<String, ArrayList<FoodOrder>> weekToFoodOrders = foodOrderController.getAllFoodOrdersSetByWeek();
			
			TreeMap<String, Double>  weekToTotalPrice = foodOrderController.getFoodOrderSetTotalPriceByWeek(weekToFoodOrders);
			
			
			JFreeChart chart = foodOrderController.generateWeeklyChart(weekToFoodOrders, week);
			int width = 600;
			int height = 350;
			ChartUtilities.writeChartAsJPEG(os, chart, width, height);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error msg: " + e.getMessage());
			session.setAttribute("error", "Something went wrong");
			response.sendRedirect("/eureka_webservice/admin/company/view.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
