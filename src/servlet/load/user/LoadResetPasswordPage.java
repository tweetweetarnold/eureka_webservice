package servlet.load.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import services.AESAlgorithm;

/**
 * Servlet implementation class ProcessResetPasswordServlet
 */
@WebServlet("/LoadResetPasswordPage")
public class LoadResetPasswordPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadResetPasswordPage() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		AESAlgorithm aes = new AESAlgorithm();

		try {
			String email = (String) request.getParameter("email");
			String newEmail = email.replaceAll(" ", "+");
			String eDecrypt = aes.decrypt(newEmail);
			String token = (String)request.getParameter("token");

			session.setAttribute("token",token);
			session.setAttribute("email", eDecrypt);
			response.sendRedirect("/eureka_webservice/pages/set-new-password.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("error ", e.getMessage());
			response.sendRedirect("/eureka_webservice/pages/reset-password.jsp");
		}
	}
}
