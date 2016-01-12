package servlet.process.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Company;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import controller.AccessController;
import controller.CompanyController;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/ProcessRegistrationServlet")
public class ProcessRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcessRegistrationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		System.out.println("*********** RegistrationServlet ***********");

		HttpSession session = request.getSession();
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		AccessController accessController = new AccessController();
		CompanyController companyController = new CompanyController();
		// JSONObject userInput = new JSONObject();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		HashMap<String, String> userInput = new HashMap<>();

		String test = request.getParameter("test");

		try {
			String employeeName = (String) request.getParameter("name").trim();
			String password = (String) request.getParameter("password");
			String confirmPwd = (String) request.getParameter("confirmPwd");
			String contactNo = (String) request.getParameter("contactNo");
			String email = (String) request.getParameter("email").trim();
			String companyCode = (String) request.getParameter("companyCode");

			userInput.put("name", employeeName);
			userInput.put("email", email);
			userInput.put("contactNo", contactNo);
			userInput.put("companyCode", companyCode);

			// Check parameters validity
			ArrayList<String> errorMessages = accessController.checkUserInputs(email, employeeName,
					password, confirmPwd, contactNo);

			if (errorMessages != null) { // if parameters don't meet requirements
				String msg = "";
				for (String s : errorMessages) {
					msg = s + "\n" + msg;
					System.out.println(msg);
				}
				throw new Exception(msg);
			}

			userInput.put("password", password);
			session.setAttribute("userInput", userInput);

			Company company = companyController.getCompanyByCompanyCode(companyCode);
			Set<String> buildingSet = company.getDeliveryPointSet();
			System.out.println("Building size: " + buildingSet.size());

			if (test != null && test.equals("true")) {
				userInput.put("status", "ok");
				out.print(gson.toJson(userInput));
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("defaultDeliveryPoint.jsp");
				request.setAttribute("buildingSet", buildingSet);
				rd.forward(request, response);
			}

		} catch (Exception e) {
			System.out.println("Exception@RegistrationServlet: " + e.getMessage());
			e.printStackTrace();

			if (test != null && test.equals("true")) {
				try {
					JSONObject obj = new JSONObject();
					obj.put("status", "error");
					obj.put("error", e.getMessage());
					out.print(gson.toJson(obj));
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				if (!e.getMessage().isEmpty()) {
					session.setAttribute("error", e.getMessage());
				} else {
					session.setAttribute("error",
							"Oops! Something went wrong! Please check your inputs.");
				}
				session.setAttribute("userInput", userInput);

				response.sendRedirect("registration.jsp");
			}
		}
	}
}
