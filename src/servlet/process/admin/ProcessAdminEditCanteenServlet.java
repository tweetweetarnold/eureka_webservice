package servlet.process.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.CanteenController;

/**
 * Servlet implementation class ProcessAdminEditCanteenServlet
 */
@WebServlet("/ProcessAdminEditCanteenServlet")
public class ProcessAdminEditCanteenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessAdminEditCanteenServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		// boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		CanteenController canteenController = new CanteenController();
		String canteenId = request.getParameter("canteenId");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		

		try {
			canteenController.editCanteen(canteenId,name,address);
			session.setAttribute("success", "Canteen updated successfully.");
			//redirect
			response.sendRedirect("DO SOMETHING");

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("error", e.getMessage());
			//redirect
			response.sendRedirect("DO SOMETHING");
		}
	}

}
