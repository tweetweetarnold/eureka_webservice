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
import model.Stall;
import controller.CanteenController;
import controller.StallController;

/**
 * Servlet implementation class LoadAdminViewStallsDetailsServlet
 */
@WebServlet("/LoadAdminViewStallsServlet")
public class LoadAdminViewStallsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadAdminViewStallsServlet() {
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
		StallController stallController = new StallController();
		CanteenController canteenController = new CanteenController();

		String canteenIdString = request.getParameter("canteenId");
		int canteenId = Integer.parseInt(canteenIdString);

		Canteen canteen = canteenController.getCanteen(canteenId);

		ArrayList<Stall> list = stallController.getAllStallsUnderCanteen(canteen);

		session.setAttribute("canteenName", canteen.getName());
		session.setAttribute("canteenId", canteenId);
		session.setAttribute("stallList", list);

		response.sendRedirect("/eureka_webservice/admin/stall/view.jsp");

	}

}
