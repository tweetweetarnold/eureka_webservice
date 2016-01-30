package servlet.process.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Stall;
import controller.StallController;

/**
 * Servlet implementation class ProcessAdminDeleteStallServlet
 */
@WebServlet("/ProcessAdminDeleteStallServlet")
public class ProcessAdminDeleteStallServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcessAdminDeleteStallServlet() {
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

		String stallIdString = request.getParameter("stallId");
		try {
			int stallId = Integer.parseInt(stallIdString);
			Stall stall = stallController.getStall(stallId);

			stallController.deleteStall(stall);

			int canteenId = (int) session.getAttribute("canteenId");

			session.setAttribute("success", stall.getName() + " has been deleted.");

			response.sendRedirect("/eureka_webservice/LoadAdminViewStallsServlet?canteenId=" + canteenId);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
