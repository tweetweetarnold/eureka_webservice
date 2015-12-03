package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.mail.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.validator.routines.EmailValidator;

import controller.AccessController;
import controller.CompanyController;
import model.Company;

/**
 * Servlet implementation class RetrieveBuildingsServlet
 */
@WebServlet("/RetrieveDeliveryPointsServlet")
public class RetrieveDeliveryPointsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String companyCode =(String) session.getAttribute("companyCode");
		Company company = CompanyController.getCompanyByCompanyCode(companyCode);
		Set<String> buildingSet = company.getDeliveryPointSet();
		RequestDispatcher rd = request.getRequestDispatcher("defaultBuilding.jsp");
		request.setAttribute("buildingSet", buildingSet);
		rd.forward(request, response);
		
		
	}
	

}
