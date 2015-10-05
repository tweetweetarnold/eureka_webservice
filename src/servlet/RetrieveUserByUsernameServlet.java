package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import model.*;
import dao.*;
import controller.*;

/**
 * Servlet implementation class RetrieveUserByUsernameServlet
 */
@WebServlet("/RetrieveUserByUsernameServlet")
public class RetrieveUserByUsernameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetrieveUserByUsernameServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		JSONObject returnObj = new JSONObject();
		
		try {
			response.setContentType("application/json");
			
			String parameter = request.getParameter("username");
			//temporary codes for retrieving employee object via id
			
			UserController userController = new UserController();
			Employee employee = userController.retrieveEmployeeViaUsername(parameter);
			//end of temporary codes

			JSONObject jsonMessage = new JSONObject();
			
			jsonMessage.put("id", employee.getEmployeeId());
			jsonMessage.put("name", employee.getName());
			jsonMessage.put("username", employee.getUsername());
			jsonMessage.put("contactNo", employee.getContactNo());
			jsonMessage.put("eDollars", employee.geteDollars());
			
			returnObj.put("message", jsonMessage);
			
			RequestDispatcher rd  = request.getRequestDispatcher("displayUserInfo.jsp");
			request.setAttribute("employeeDetails", returnObj);
			rd.forward(request, response);
			
		} catch (Exception e) {
			returnObj.put("error", "Error in retrieving user info");
			RequestDispatcher rd = request.getRequestDispatcher("displayUserInfo.jsp");
			request.setAttribute("error", returnObj);
			rd.forward(request, response);
			
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}
	
	

}
