package servlet.load.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.lucene.util.packed.PackedLongValues.Iterator;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import controller.FoodOrderController;
import model.Employee;
import model.FoodOrder;

/**
 * Servlet implementation class LoadUserMonthlyChart
 */
@WebServlet("/LoadUserMonthlyChart")
public class LoadUserMonthlyChart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadUserMonthlyChart() {
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
		response.setContentType("image/jpeg");
		ServletOutputStream os = response.getOutputStream();
		HttpSession session = request.getSession();
		try {
			Employee emp = (Employee) session.getAttribute("user");

			FoodOrderController foodOrderController = new FoodOrderController();
			String year = request.getParameter("year");
			TreeMap<String, ArrayList<FoodOrder>> yearMonthToFoodOrders = foodOrderController
					.getFoodOrderSetByMonthYear(emp);
			TreeMap<String, Double> yearMonthToTotalPrice = foodOrderController
					.getFoodOrderSetTotalPriceByMonthYear(yearMonthToFoodOrders);
			JFreeChart chart = foodOrderController.generateMonthlyChart(yearMonthToTotalPrice, year);
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
