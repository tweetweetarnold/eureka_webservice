package servlet.process.admin;

import java.io.IOException;

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
 * Servlet implementation class ProcessAdminAddNewStallServlet
 */
@WebServlet("/ProcessAdminAddNewStallServlet")
public class ProcessAdminAddNewStallServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcessAdminAddNewStallServlet() {
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

		String name = request.getParameter("name");
		String contactNoString = request.getParameter("contactNo");
		String canteenIdString = request.getParameter("canteenId");
		System.out.println("String: " + canteenIdString);

		String imageDirectory = request.getParameter("imageDirectory");

		try {
			int canteenId = Integer.parseInt(canteenIdString);
			long contactNo = Long.parseLong(contactNoString);

			Canteen c = canteenController.getCanteen(canteenId);
			Stall s = new Stall(name, contactNo, c, null, imageDirectory);

			System.out.println("stallname: " + s.getName());
			System.out.println("saving food...");
			stallController.saveStall(s);

			session.setAttribute("success", "Stall added successfully.");

			response.sendRedirect("/eureka_webservice/LoadAdminViewStallsServlet?canteenId="
					+ canteenId);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
