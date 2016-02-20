package servlet.process.admin;

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

/**
 * Servlet implementation class EditOrderWindowServlet
 */
@WebServlet("/EditOrderWindowServlet")
public class EditOrderWindowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditOrderWindowServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		String startDatetimeString = request.getParameter("startDatetime");
		String endDatetimeString = request.getParameter("endDatetime");

		try {

			// **Important: to format the time to SG timezone
			DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MMMM-yyyy HH:mm")
					.withZone(DateTimeZone.forID("Asia/Singapore"));
			DateTime startDatetime = formatter.parseDateTime(startDatetimeString);
			DateTime endDatetime = formatter.parseDateTime(endDatetimeString);

			System.out.println("startDatetime: " + startDatetime);
			System.out.println("endDatetime: " + endDatetime);

			/*
			 * TODO:
			 * 
			 * CHRIS CHENG OVERE HERE!!
			 *
			 * WORK FOR CHRIS CHENG TO DO!!!!!!!!!!!!!!!!!!!!!
			 * 
			 * CHRIS CHENG OVER HERE!!
			 * 
			 */

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("error", e.getMessage());
			response.sendRedirect("/eureka_webservice/admin/orderwindow/view.jsp");
		}

	}

}
