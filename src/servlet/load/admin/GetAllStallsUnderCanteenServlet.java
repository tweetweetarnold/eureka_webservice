package servlet.load.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Food;
import model.Modifier;
import model.ModifierSection;
import model.Stall;

import org.json.simple.JSONObject;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class GetAllStallsUnderCanteenServlet
 */
@WebServlet("/GetAllStallsUnderCanteenServlet")
public class GetAllStallsUnderCanteenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetAllStallsUnderCanteenServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		Gson gson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {

			@Override
			public boolean shouldSkipClass(Class<?> c) {
				return false;
			}

			@Override
			public boolean shouldSkipField(FieldAttributes c) {
				return (c.getDeclaringClass() == Stall.class && c.getName().equals("canteen"))
						|| (c.getDeclaringClass() == Food.class && c.getName().equals("stall"))
						|| (c.getDeclaringClass() == ModifierSection.class && c.getName().equals(
								"food"))
						|| (c.getDeclaringClass() == Modifier.class && c.getName().equals(
								"modifierSection"))
						|| (c.getDeclaringClass() == Modifier.class && c.getName().equals("food"));
			}

		}).create();

		String canteenId = request.getParameter("canteenId");
		System.out.println("canteenID: " + canteenId);

		JSONObject obj = new JSONObject();
		obj.put("canteenId", canteenId);
		// obj.put("name", "Arnold");
		out.println(obj);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
