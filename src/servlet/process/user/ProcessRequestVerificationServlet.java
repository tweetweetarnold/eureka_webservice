package servlet.process.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.AccessController;

/**
 * Servlet implementation class ProcessRequestVerificationServlet
 */
@WebServlet("/ProcessRequestVerificationServlet")
public class ProcessRequestVerificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessRequestVerificationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String email = request.getParameter("email");
		AccessController accessController = new AccessController();
		String[] toEmail = {email};
		try {
			
			accessController.constructVerifyEmail(request.getServerName(), request.getServerPort(), request.getContextPath(), email, toEmail);
			session.removeAttribute("email");

			session.setAttribute("success",
					"An email has been sent to you. Please check your email within 5 minutes and follow the instructions on verifying your account.");
			response.sendRedirect("/eureka_webservice/pages/login.jsp");
		} catch (Exception e) {
			session.setAttribute("error", e.getMessage());
			response.sendRedirect("/eureka_webservice/pages/login.jsp");
			
		}
	}

}
