package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import controller.UserController;
import entity.Employee;

/**
 * Servlet implementation class retrieveUserServlet
 */
@WebServlet("/retrieveUserServlet")
public class retrieveUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public retrieveUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//int tempID = Integer.parseInt(request.getParameter("userID"));
		String tempID = request.getParameter("userID");
		UserController userController = new UserController();
		Employee tempE = userController.retrieveEmployeeTEMPORARY(tempID);
		Gson gson = new Gson();
		//convert the employee object(tempE) to a json object
		String jsonString = gson.toJson(tempE);
		//send json output back
		
		response.getWriter().write(jsonString);
		RequestDispatcher rd = request.getRequestDispatcher("testRetrieveUser.jsp");
		request.setAttribute("UserJson", jsonString);
		rd.forward(request,response);
		
	}

}
