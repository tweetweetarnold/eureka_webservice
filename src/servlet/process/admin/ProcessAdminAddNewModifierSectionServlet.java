package servlet.process.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ModifierSectionController;

/**
 * Servlet implementation class ProcessAdminAddNewModifierSectionServlet
 */
@WebServlet("/ProcessAdminAddNewModifierSectionServlet")
public class ProcessAdminAddNewModifierSectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessAdminAddNewModifierSectionServlet() {
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
		ModifierSectionController controller = new ModifierSectionController();
		
		try {
			String categoryName = request.getParameter("categoryName");
			String displayType = request.getParameter("displayType");
			String foodID = request.getParameter("foodID");
			
			controller.addModifierSection(foodID, categoryName, displayType);
			
			session.setAttribute("success", "ModifierSection added successfully.");
			response.sendRedirect("/eureka_webservice/admin/homepage.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
