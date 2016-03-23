package servlet.process.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import services.QuartzService;

/**
 * Servlet implementation class RunQuartzServlet
 */
@WebServlet("/RunQuartzServlet")
public class RunQuartzServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RunQuartzServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		QuartzService quartzService = new QuartzService();
		quartzService.doProcess();
		HttpSession session = request.getSession();
		session.setAttribute("success", "Scheduled notifications now active.");
		response.sendRedirect("/eureka_webservice/admin/homepage.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		QuartzService quartzService = new QuartzService();
		quartzService.doProcess();
		HttpSession session = request.getSession();
		session.setAttribute("success", "Scheduled notifications now active.");
		response.sendRedirect("/eureka_webservice/admin/homepage.jsp");
	}

}
