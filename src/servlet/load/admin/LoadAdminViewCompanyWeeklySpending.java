package servlet.load.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.FoodOrderController;
import model.FoodOrder;

/**
 * Servlet implementation class LoadAdminViewCompanyWeeklySpending
 */
@WebServlet("/LoadAdminViewCompanyWeeklySpending")
public class LoadAdminViewCompanyWeeklySpending extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadAdminViewCompanyWeeklySpending() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		try {

			String companyCode = request.getParameter("company");
			String companyName = request.getParameter("name");
			FoodOrderController foodOrderController = new FoodOrderController();
			TreeMap<String, ArrayList<FoodOrder>> weekToFoodOrders = foodOrderController
					.getCompanyFoodOrderSetByWeek(companyCode);
			TreeMap<String, Double>  weekToTotalPrice = foodOrderController.getFoodOrderSetTotalPriceByWeek(weekToFoodOrders);
			session.setAttribute("name", companyName);
			Set<String> weekList = weekToTotalPrice.keySet();
			session.setAttribute("weekList", weekList);

			String week = request.getParameter("id");
			if (week != null) {
				TreeMap<String, Double> dateToTotalPrice = foodOrderController.dateToTotalPrice(weekToFoodOrders, week);
				// session.setAttribute("yearToMonthList", yearToMonthList);
				session.setAttribute("resultSet", week);
				session.setAttribute("dateToTotalPrice", dateToTotalPrice);
				session.setAttribute("weekToFoodOrders", weekToFoodOrders);
				session.setAttribute("weekToTotalPrice", weekToTotalPrice);

			}

			// ***Haven't define the jsp page to redirect***
			 response.sendRedirect("/eureka_webservice/admin/company/weekly-summary.jsp?company=" + companyCode);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error msg: " + e.getMessage());
			session.setAttribute("error", "Something went wrong");
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
