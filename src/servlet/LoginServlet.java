package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import model.*;
import controller.LoginController;

import java.security.*;
import java.util.UUID;

import dao.*;
import services.*;

/**
 * Servlet implementation class Hello
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		JSONObject returnObj = new JSONObject();
		try {
			response.setContentType("application/json");
			
			PrintWriter out = response.getWriter();

			// Getting User Input Parameters
			String username = (String) request.getParameter("username");
			String inputPwd = (String) request.getParameter("password");
			
			JSONObject jsonMessage = new JSONObject();
			
			jsonMessage.put("username", username);
			jsonMessage.put("inputPwd", inputPwd);
			returnObj.put("message", jsonMessage);
			
			// User Input Validation
			boolean validation = (username != null && inputPwd != null && !username.equals("") && !inputPwd
					.equals(""));

			if (validation) {

				Integer id = Integer.parseInt(username); // **@Boonhui, why need
															// to parse to int?
				
				LoginController loginController = new LoginController();
				Employee emp = loginController.authenticateUser(id,
						PasswordService.encryptPassword(inputPwd));

				
				// *** For Development only ***
				//creates a tokenID using UUID (Universalised Unique Identifier Object)
				//the user's username is tagged at the end of the token
				String tokenID = UUID.randomUUID().toString().toUpperCase() 
		            + "|" + emp.getUsername() + "|";
					
				HttpSession session = request.getSession();
				session.setAttribute("user", emp);
				session.setAttribute("tokenID", tokenID);
					
				response.sendRedirect("home.jsp");
				
			} else {
				// Error Response
				throw new Exception("Please ensure that you have inserted valid ID and password into the fields.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnObj.put("error", "Please ensure that you have inserted valid ID and password into the fields.");
			returnObj.put("status", "fail");
			
			HttpSession session = request.getSession();
			session.setAttribute("error", returnObj);
			response.sendRedirect("login.jsp");
		}
	}
}
