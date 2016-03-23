package servlet.process.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;

import controller.AccessController;
import dao.AdminDAO;
import model.Admin;
import services.AESEncryption;

/**
 * Servlet implementation class UpdateAdminPassword
 */
@WebServlet("/UpdateAdminPassword")
public class UpdateAdminPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateAdminPassword() {
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
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		Gson gson = new Gson();
		AdminDAO adminDAO = new AdminDAO();
		AccessController accessCtrl = new AccessController();
		JSONObject returnJson = new JSONObject();

		try {
			JSONParser parser = new JSONParser();
			JSONObject data = (JSONObject) parser.parse(request.getReader());

			String oldPassword = (String) data.get("oldPassword");
			String newPassword = (String) data.get("newPassword");
			String confirmPassword = (String) data.get("confirmPassword");

			Admin a = adminDAO.getAdmin(1);

			if (!newPassword.equals(confirmPassword)) {
				throw new Exception("Passwords do not match!");
			} else if (!AESEncryption.encrypt(oldPassword).equals(a.getPassword())) {
				throw new Exception("Old password is not correct!");
			}

			accessCtrl.updateAdminPassword(a, newPassword);

			returnJson.put("success", "Admin password successfully updated!");

		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put("error", e.getMessage());
		} finally {
			out.println(gson.toJson(returnJson));
		}

	}

}
