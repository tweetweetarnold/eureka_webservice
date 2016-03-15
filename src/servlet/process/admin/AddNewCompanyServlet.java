package servlet.process.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;

import controller.CompanyController;

/**
 * Servlet implementation class AddNewCompanyServlet
 */
@WebServlet("/AddNewCompanyServlet")
public class AddNewCompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddNewCompanyServlet() {
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

		CompanyController companyCtrl = new CompanyController();

		JSONObject returnJson = new JSONObject();
		Gson gson = new Gson();

		try {
			JSONParser parser = new JSONParser();
			JSONObject data = (JSONObject) parser.parse(request.getReader());
			System.out.println(gson.toJson(data));

			String name = (String) data.get("name");
			String code = (String) data.get("code");
			JSONArray arr = (JSONArray) data.get("deliveryPointList");

			Set<String> list = new HashSet<String>();
			for (Object o : arr) {
				list.add((String) o);
			}

			System.out.println(list);

			companyCtrl.addNewCompany(name, code, list);

			returnJson.put("success", "Company " + name + " has been added.");

		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put("error", e.getMessage());
		}

		System.out.println(gson.toJson(returnJson));
		out.print(gson.toJson(returnJson));
	}

}
