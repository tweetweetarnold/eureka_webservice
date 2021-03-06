package servlet.load.user;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import controller.FoodOrderController;
import model.Employee;
import model.FoodOrder;

/**
 * Servlet implementation class LoadUserWeeklyChart
 */
@WebServlet("/LoadUserWeeklyChart")
public class LoadUserWeeklyChart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadUserWeeklyChart() {
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
			Employee emp = (Employee) session.getAttribute("user");

			FoodOrderController foodOrderController = new FoodOrderController();
			String week = request.getParameter("week");
			TreeMap<String, ArrayList<FoodOrder>> weekToFoodOrders = foodOrderController.getFoodOrderSetByWeek(emp);
			
			TreeMap<String, Double>  weekToTotalPrice = foodOrderController.getFoodOrderSetTotalPriceByWeek(weekToFoodOrders);
			
			
			JFreeChart chart = foodOrderController.generateWeeklyChart(weekToFoodOrders, week);
			int width = 600;
			int height = 350;
			ChartUtilities.writeChartAsJPEG(os, chart, width, height);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error msg: " + e.getMessage());
			session.setAttribute("error", "Something went wrong");
			response.sendRedirect("/eureka_webservice/pages/homepage.jsp");
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
