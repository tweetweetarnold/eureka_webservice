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
import model.Employee;
import model.FoodOrder;

/**
 * Servlet implementation class LoadCompanyMonthlyChart
 */
@WebServlet("/LoadCompanyMonthlyChart")
public class LoadCompanyMonthlyChart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadCompanyMonthlyChart() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("image/jpeg");
		ServletOutputStream os = response.getOutputStream();
		HttpSession session = request.getSession();
		try {
			String companyCode = request.getParameter("company");

			FoodOrderController foodOrderController = new FoodOrderController();
			String year = request.getParameter("year");
			TreeMap<String, ArrayList<FoodOrder>> yearMonthToFoodOrders = foodOrderController
					.getCompanyFoodOrderSetByMonthYear(companyCode);
			TreeMap<String, Double> yearMonthToTotalPrice = foodOrderController
					.getFoodOrderSetTotalPriceByMonthYear(yearMonthToFoodOrders);
			JFreeChart chart = foodOrderController.generateMonthlyChart(yearMonthToTotalPrice,
					year);
			int width = 700;
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
