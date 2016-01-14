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
 * Servlet implementation class ProcessAdminEditStallServlet
 */
@WebServlet("/ProcessAdminEditStallServlet")
public class ProcessAdminEditStallServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcessAdminEditStallServlet() {
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
		String name = request.getParameter("name");
		String contactNoString = request.getParameter("contactNo");
		String imageDirectory = request.getParameter("imageDirectory");

		int stallId = Integer.parseInt(stallIdString);
		long contactNo = Long.parseLong(contactNoString);

		Stall stall = stallController.getStall(stallId);
		int canteenId = stall.getCanteen().getCanteenId();

		stall.setName(name);
		stall.setContactNo(contactNo);
		stall.setImageDirectory(imageDirectory);

		System.out.println("stallname: " + stall.getName());
		System.out.println("saving stall...");
		stallController.updateStall(stall);

		session.setAttribute("success", "Stall updated successfully.");

		response.sendRedirect("/eureka_webservice/LoadAdminViewStallsServlet?canteenId="
				+ canteenId);
	}

}
