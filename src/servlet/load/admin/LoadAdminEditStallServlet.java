package servlet.load.admin;

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
 * Servlet implementation class LoadAdminEditStallDetailsServlet
 */
@WebServlet("/LoadAdminEditStallServlet")
public class LoadAdminEditStallServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadAdminEditStallServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		StallController stallController = new StallController();

		String stallIdString = request.getParameter("stallId");
		int stallId = Integer.parseInt(stallIdString);

		Stall stall = stallController.getStall(stallId);

		session.setAttribute("stallId", stallId);
		session.setAttribute("name", stall.getName());
		session.setAttribute("contactNo", stall.getContactNo());
		session.setAttribute("imageDirectory", stall.getImageDirectory());

		response.sendRedirect("/eureka_webservice/admin/stall/edit.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
