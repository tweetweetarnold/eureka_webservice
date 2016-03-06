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

import controller.CompanyController;

/**
 * Servlet implementation class SetCompanyServlet
 */
@WebServlet("/SetCompanyServlet")
public class SetCompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SetCompanyServlet() {
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
		CompanyController companyCtrl = new CompanyController();
		JSONObject returnJson = new JSONObject();

		try {

			JSONParser parser = new JSONParser();
			JSONObject data = (JSONObject) parser.parse(request.getReader());
			System.out.println(gson.toJson(data));

			int companyId = ((Long) data.get("companyId")).intValue();
			String name = (String) data.get("name");
			String code = (String) data.get("companyCode");

			companyCtrl.editCompany(companyId, name, code, null);

			returnJson.put("success", "Company " + name + " updated.");

		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put("error", e.getMessage());
		}

		out.println(gson.toJson(returnJson));
	}

}
