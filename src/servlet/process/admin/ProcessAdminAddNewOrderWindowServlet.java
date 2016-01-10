package servlet.process.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Canteen;
import model.Company;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import controller.CanteenController;
import controller.CompanyController;
import controller.OrderWindowController;

/**
 * Servlet implementation class ProcessAdminAddNewOrderWindowServlet
 */
@WebServlet("/ProcessAdminAddNewOrderWindowServlet")
public class ProcessAdminAddNewOrderWindowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcessAdminAddNewOrderWindowServlet() {
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
		OrderWindowController orderWindowController = new OrderWindowController();
		CompanyController companyController = new CompanyController();
		CanteenController canteenController = new CanteenController();

		// TODO: orderwindow discount not done
		String weekString = request.getParameter("Repeat");
		String discountString = request.getParameter("discount");
		String companyId = request.getParameter("company");
		String canteenId = request.getParameter("canteen");
		String startDatetimeString = request.getParameter("startDatetime");
		String endDatetimeString = request.getParameter("endDatetime");

		System.out.println("companyId: " + companyId);
		System.out.println("canteenId: " + canteenId);

		try {
			DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MMMM-yyyy HH:mm");
			DateTime startDatetime = formatter.parseDateTime(startDatetimeString);
			DateTime endDatetime = formatter.parseDateTime(endDatetimeString);

			System.out.println("startDatetime: " + startDatetime);
			System.out.println("endDatetime: " + endDatetime);

			Company company = companyController.getCompany(Integer.parseInt(companyId));
			Canteen canteen = canteenController.getCanteen(Integer.parseInt(canteenId));
			double discount = 0;
			int week = Integer.parseInt(weekString);
			try{
				discount = Double.parseDouble(discountString);
			}catch(Exception e){
			}
			orderWindowController.createNewOrderindow(startDatetime, endDatetime, company, canteen,
					discount, week);

			session.setAttribute("success", "New Order Window created successfully.");

			response.sendRedirect("/eureka_webservice/admin/homepage.jsp");
		} catch (Exception e) {
			PrintWriter out = response.getWriter();
			out.print("error: " + e.getMessage());
			e.printStackTrace();
		}

	}
}
