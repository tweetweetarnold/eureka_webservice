package servlet.load.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Canteen;
import model.Company;
import controller.CanteenController;
import controller.CompanyController;

/**
 * Servlet implementation class LoadAdminAddNewWindowServlet
 */
@WebServlet("/LoadAdminAddNewWindowServlet")
public class LoadAdminAddNewWindowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadAdminAddNewWindowServlet() {
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

		try {
			CompanyController coCtrl = new CompanyController();
			CanteenController caCtrl = new CanteenController();
			HttpSession session = request.getSession();

			ArrayList<Company> companyList = coCtrl.getAllCompany();
			ArrayList<Canteen> canteenList = caCtrl.getAllCanteens();

			session.setAttribute("companyList", companyList);
			session.setAttribute("canteenList", canteenList);

			response.sendRedirect("adminAddNewWindow.jsp");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("An error has occurred at LoadAdminAddNewWindowServlet: "
					+ e.getMessage());
		}
	}
}
