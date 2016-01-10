package servlet.load.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Company;
import controller.CompanyController;

/**
 * Servlet implementation class LoadAdminViewCompaniesServlet
 */
@WebServlet("/LoadAdminViewCompaniesServlet")
public class LoadAdminViewCompaniesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadAdminViewCompaniesServlet() {
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

		CompanyController companyController = new CompanyController();

		ArrayList<Company> companyList = companyController.getAllCompany();
		session.setAttribute("companyList", companyList);
		
		response.sendRedirect("/eureka_webservice/admin/company/view.jsp");
	}
}
