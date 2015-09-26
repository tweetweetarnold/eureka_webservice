package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;
import controller.LoginController;

import java.security.*;

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
		// request.setCharacterEncoding("UTF-8");
		// response.setCharacterEncoding("UTF-8");
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println();
			// if(request.getParameter("function").equals("newLogin")){

			String username = (String) request.getParameter("username");
			String inputPwd = (String) request.getParameter("password");
			if (username != null && inputPwd != null && !username.equals("")
					&& !inputPwd.equals("")) {
				Integer id = Integer.parseInt(username);
				out.println(id);
				LoginController loginController = new LoginController();
				// String inputPwd = (String) request.getParameter("password");
				// System.out.println(pwd);
				Employee emp = loginController.authenticateUser(id,
						PasswordService.encryptPassword(inputPwd));
				if (emp != null) {
					out.println("hello " + id);
					out.println("Encrypted: "
							+ PasswordService.encryptPassword(inputPwd));
					out.println("Decrypted: "
							+ PasswordService.decryptPassword(PasswordService
									.encryptPassword(inputPwd)));
					out.println("You got it!");

					RequestDispatcher rd = request
							.getRequestDispatcher("home.jsp");
					request.setAttribute("user", emp);
					rd.forward(request, response);
				} else {
					out.println("SALA");
					// services.PasswordService.getInstance().encrypt(pwd);
					out.println(inputPwd);
				}
			} else {
				response.sendRedirect("login.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
