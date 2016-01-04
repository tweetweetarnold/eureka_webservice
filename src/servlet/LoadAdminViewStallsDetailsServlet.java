package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Canteen;
import model.Stall;
import controller.CanteenController;
import controller.StallController;

/**
 * Servlet implementation class LoadAdminViewStallsDetailsServlet
 */
@WebServlet("/LoadAdminViewStallsDetailsServlet")
public class LoadAdminViewStallsDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadAdminViewStallsDetailsServlet() {
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
		StallController stallController = new StallController();
		CanteenController canteenController = new CanteenController();

		String canteenIdString = request.getParameter("canteenId");
		int canteenId = Integer.parseInt(canteenIdString);

		Canteen canteen = canteenController.getCanteen(canteenId);

		ArrayList<Stall> list = stallController.getAllStallsUnderCanteen(canteen);

		session.setAttribute("canteenName", canteen.getName());
		session.setAttribute("stallList", list);

		response.sendRedirect("adminViewStalls.jsp");

	}

}
