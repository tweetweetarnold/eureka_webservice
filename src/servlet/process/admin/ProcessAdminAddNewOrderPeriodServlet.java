package servlet.process.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Canteen;
import model.Company;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import controller.CanteenController;
import controller.CompanyController;
import controller.OrderPeriodController;

/**
 * Servlet implementation class ProcessAdminAddNewOrderPeriodServlet
 */
@WebServlet("/ProcessAdminAddNewOrderPeriodServlet")
public class ProcessAdminAddNewOrderPeriodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcessAdminAddNewOrderPeriodServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		OrderPeriodController orderPeriodController = new OrderPeriodController();
		CompanyController companyController = new CompanyController();
		CanteenController canteenController = new CanteenController();

		String numberOfWeeksString = request.getParameter("repeat");
		String discountAbsoluteString = request.getParameter("discountAbsolute");
		String companyId = request.getParameter("company");
		String canteenId = request.getParameter("canteen");
		String startDatetimeString = request.getParameter("startDatetime");
		String endDatetimeString = request.getParameter("endDatetime");
		String remarks = request.getParameter("remarks");

		System.out.println("startDate string: " + startDatetimeString);
		System.out.println("endDate string: " + endDatetimeString);
		System.out.println("discount " + discountAbsoluteString);
		System.out.println("companyId: " + companyId);
		System.out.println("canteenId: " + canteenId);

		try {
			// **Important: to format the time to SG timezone
			DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MMMM-yyyy HH:mm")
					.withZone(DateTimeZone.forID("Asia/Singapore"));
			DateTime startDatetime = formatter.parseDateTime(startDatetimeString);
			DateTime endDatetime = formatter.parseDateTime(endDatetimeString);

			System.out.println("startDatetime: " + startDatetime);
			System.out.println("endDatetime: " + endDatetime);

			Company company = companyController.getCompany(Integer.parseInt(companyId));
			Canteen canteen = canteenController.getCanteen(Integer.parseInt(canteenId));
			double discountAbsolute = 0;
			int numberOfWeeks = Integer.parseInt(numberOfWeeksString);

			try {
				discountAbsolute = Double.parseDouble(discountAbsoluteString);
				if (discountAbsolute > 0) {
					discountAbsolute *= -1;
					System.out.println("In the try : " + discountAbsolute);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			boolean available = orderPeriodController.checkForOrderPeriodAvailability(startDatetime,
					endDatetime, company, numberOfWeeks);

			if (!available) {
				throw new Exception("Order Period have already been taken");
			} else {
				orderPeriodController.createNewOrderPeriod2(startDatetime, endDatetime, company,
						canteen, numberOfWeeks, remarks, discountAbsolute);
			}
			session.setAttribute("success", "New Order Period created successfully.");

			response.sendRedirect("/eureka_webservice/admin/orderperiod/active.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("error", e.getMessage());
			response.sendRedirect("/eureka_webservice/admin/orderperiod/active.jsp");
		}

	}
}
