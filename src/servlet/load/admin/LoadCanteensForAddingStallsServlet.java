package servlet.load.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Canteen;
import dao.CanteenDAO;

/**
 * Servlet implementation class LoadCanteensForAddingStallsServlet
 */
@WebServlet("/LoadCanteensForAddingStallsServlet")
public class LoadCanteensForAddingStallsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadCanteensForAddingStallsServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		System.out.println("****** LoadCanteensForAddingStallsServlet ******");
		HttpSession session = request.getSession();
		HashMap<String, String> userInput = new HashMap<>();
		ArrayList<Canteen> canteenList = new ArrayList<Canteen>();
		try {
			CanteenDAO canteenDAO = new CanteenDAO();
			canteenList = canteenDAO.getAllCanteens();
			RequestDispatcher rd = request.getRequestDispatcher("addStallsTEST.jsp");
			request.setAttribute("canteenList", canteenList);
			rd.forward(request, response);
		} catch (Exception e) {
		}
	}

}
