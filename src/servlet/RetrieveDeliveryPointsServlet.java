package servlet;

import java.io.IOException;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Company;
import controller.CompanyController;

/**
 * Servlet implementation class RetrieveBuildingsServlet
 */
@WebServlet("/RetrieveDeliveryPointsServlet")
public class RetrieveDeliveryPointsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	CompanyController companyController = new CompanyController();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RetrieveDeliveryPointsServlet() {
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

		String companyCode = (String) session.getAttribute("companyCode");
		System.out.println(companyCode + "SADA");

		Company company = companyController.getCompanyByCompanyCode(companyCode);
		Set<String> buildingSet = company.getDeliveryPointSet();
		System.out.println(" Servelytsfas " + buildingSet.size());

		session.removeAttribute("companyCode");

		RequestDispatcher rd = request.getRequestDispatcher("defaultDeliveryPoint.jsp");
		request.setAttribute("buildingSet", buildingSet);
		rd.forward(request, response);

	}

}
