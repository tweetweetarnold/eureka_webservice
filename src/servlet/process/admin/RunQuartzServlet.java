package servlet.process.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.QuartzTest;

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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		QuartzTest quartzTest = new QuartzTest();
		quartzTest.doProcess();
		response.sendRedirect("/eureka_webservice/pages/login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		QuartzTest quartzTest = new QuartzTest();
		quartzTest.doProcess();
		response.sendRedirect("/eureka_webservice/pages/login.jsp");
	}

}
