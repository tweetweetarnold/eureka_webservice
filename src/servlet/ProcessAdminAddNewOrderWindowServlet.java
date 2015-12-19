package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Canteen;
import model.Company;

import org.joda.time.DateTime;

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
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();
		OrderWindowController owCtrl = new OrderWindowController();
		CompanyController companyCtrl = new CompanyController();
		CanteenController canteenCtrl = new CanteenController();

		String companyId = request.getParameter("company");
		String canteenId = request.getParameter("canteen");
		String startDatetime = request.getParameter("startDatetime");
		String endDatetime = request.getParameter("endDatetime");

		System.out.println("companyId: " + companyId);
		System.out.println("canteenId: " + canteenId);
		System.out.println("startDatetime: " + startDatetime);
		System.out.println("endDatetime: " + endDatetime);

		try {
			// DateFormat formatter = new SimpleDateFormat("dd-mmmm-yyyy hh:mm");
			// System.out.println("startdate format: " + formatter.parse(startDatetime));

			Company company = companyCtrl.getCompany(Integer.parseInt(companyId));
			Canteen canteen = canteenCtrl.getCanteen(Integer.parseInt(canteenId));

			owCtrl.createNewOrderindow(new DateTime(), new DateTime().plusDays(2), company, canteen);

			session.setAttribute("success", "New Order Window created successfully.");

			response.sendRedirect("adminHomepage.jsp");
		} catch (Exception e) {
			PrintWriter out = response.getWriter();
			out.print("error");
		}

	}
}
