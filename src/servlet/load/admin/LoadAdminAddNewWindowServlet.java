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

		try {
			CompanyController coCtrl = new CompanyController();
			CanteenController caCtrl = new CanteenController();
			HttpSession session = request.getSession();

			ArrayList<Company> companyList = coCtrl.getAllCompany();
			ArrayList<Canteen> canteenList = caCtrl.getAllCanteens();
			ArrayList<Integer> weekList = new ArrayList<Integer>();
			for(int i = 1; i<54; i++){
				weekList.add(i);
			}
			
			
			session.setAttribute("companyList", companyList);
			session.setAttribute("canteenList", canteenList);
			session.setAttribute("weekList", weekList);
			response.sendRedirect("/eureka_webservice/admin/orderwindow/add.jsp");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("An error has occurred at LoadAdminAddNewWindowServlet: "
					+ e.getMessage());
		}
	}
}
