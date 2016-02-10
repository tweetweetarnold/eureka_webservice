package servlet.process.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Canteen;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import controller.CanteenController;
import controller.OrderWindowController;

/**
 * Servlet implementation class ProcessAdminEditOrderWindowServlet
 */
@WebServlet("/ProcessAdminEditOrderWindowServlet")
public class ProcessAdminEditOrderWindowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcessAdminEditOrderWindowServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		// CompanyController companyController = new CompanyController();
		CanteenController canteenController = new CanteenController();
		OrderWindowController orderWindowController = new OrderWindowController();
		try {
			String discountAbsoluteString = request.getParameter("discountAbsolute");
			// String companyId = request.getParameter("company");
			String canteenId = request.getParameter("canteen");
			String startDatetimeString = request.getParameter("startDatetime");
			String endDatetimeString = request.getParameter("endDatetime");
			String remarks = request.getParameter("remarks");
			String orderWindowID = request.getParameter("orderWindow");

			// **Important: to format the time to SG timezone
			DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MMMM-yyyy HH:mm").withZone(
					DateTimeZone.forID("Asia/Singapore"));
			DateTime startDatetime = formatter.parseDateTime(startDatetimeString);
			DateTime endDatetime = formatter.parseDateTime(endDatetimeString);

			// Company company = companyController.getCompany(Integer.parseInt(companyId));
			Canteen canteen = canteenController.getCanteen(Integer.parseInt(canteenId));
			double discountAbsolute = 0;
			try {
				discountAbsolute = Double.parseDouble(discountAbsoluteString);
			} catch (Exception e) {
				e.printStackTrace();
			}

			orderWindowController.editOrderWindow(Integer.parseInt(orderWindowID), startDatetime,
					endDatetime, canteen, remarks, 0.0, discountAbsolute);
			response.sendRedirect("");

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("error", e.getMessage());
			response.sendRedirect("");
		}

	}

}
