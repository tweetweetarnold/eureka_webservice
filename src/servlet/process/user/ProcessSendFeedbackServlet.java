package servlet.process.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Employee;
import services.SendEmail;

/**
 * Servlet implementation class ProcessSendFeedbackServlet
 */
@WebServlet("/ProcessSendFeedbackServlet")
public class ProcessSendFeedbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcessSendFeedbackServlet() {
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
		SendEmail emailer = new SendEmail();

		try {
			Employee user = (Employee) session.getAttribute("user");
			String message = request.getParameter("message");

			String bodyMessage = "THIS IS AN AUTOMATED EMAIL FROM LUNCHTIME.<br><br>"
					+ "Feedback received from User: " + user.getName() + " (" + user.getEmail()
					+ ")<br><br>***************** START OF MESSAGE *****************<br><br>"
					+ message + "<br><br>" + "***************** END OF MESSAGE *****************";

			String[] emails = new String[] { "chris.cheng.2013@sis.smu.edu.sg",
					"boonhui.koh.2013@sis.smu.edu.sg", "arnold.lee.2013@sis.smu.edu.sg" };

			emailer.setMailServerProperties();
			emailer.sendEmail("User Feedback from LunchTime", bodyMessage, emails);

			session.setAttribute("success", "Thank you for your feedback!");

			response.sendRedirect("/eureka_webservice/pages/contact-us.jsp");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
