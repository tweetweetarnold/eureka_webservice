package servlet.load.admin;

import java.io.IOException;
import java.util.ArrayList;
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
 * Servlet implementation class LoadAdminViewCompanyMonthlySpending
 */
@WebServlet("/LoadAdminViewCompanyMonthlySpending")
public class LoadAdminViewCompanyMonthlySpending extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadAdminViewCompanyMonthlySpending() {
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
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		try {

			String companyCode = request.getParameter("company");
			String companyName = request.getParameter("name");
			FoodOrderController foodOrderController = new FoodOrderController();
			TreeMap<String, ArrayList<String>> yearToMonthList = foodOrderController
					.getCompanyYearToMonthList(companyCode);
			session.setAttribute("name", companyName);
			session.setAttribute("yearToMonthList", yearToMonthList);
			//session.setAttribute("company", companyCode);
			System.out.println(yearToMonthList.size());

			String year = request.getParameter("id");
			if (year != null) {
				TreeMap<String, ArrayList<FoodOrder>> yearMonthToFoodOrders = foodOrderController
						.getCompanyFoodOrderSetByMonthYear(companyCode);
				TreeMap<String, Double> yearMonthToTotalPrice = foodOrderController
						.getFoodOrderSetTotalPriceByMonthYear(yearMonthToFoodOrders);

				// session.setAttribute("yearToMonthList", yearToMonthList);
				session.setAttribute("result", year);
				session.setAttribute("yearMonthToFoodOrders", yearMonthToFoodOrders);
				session.setAttribute("yearMonthToTotalPrice", yearMonthToTotalPrice);

			}

			// ***Haven't define the jsp page to redirect***
			 response.sendRedirect("/eureka_webservice/admin/company/monthly-summary.jsp?company=" + companyCode);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error msg: " + e.getMessage());
			session.setAttribute("error", "Something went wrong");
		//	response.sendRedirect("/eureka_webservice/pages/homepage.jsp");
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
