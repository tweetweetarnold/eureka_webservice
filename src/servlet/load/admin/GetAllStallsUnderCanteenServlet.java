package servlet.load.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import controller.CanteenController;
import controller.StallController;
import model.Canteen;
import model.Food;
import model.Modifier;
import model.ModifierSection;
import model.Stall;

/**
 * Servlet implementation class GetAllStallsUnderCanteenServlet
 */
@WebServlet("/GetAllStallsUnderCanteenServlet")
public class GetAllStallsUnderCanteenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	final JsonSerializer<Date> dateSerialize = new JsonSerializer<Date>() {

		@Override
		public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
			final long dateString = src.getTime();
			return new JsonPrimitive(dateString);
		}

	};

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetAllStallsUnderCanteenServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		CanteenController canteenCtrl = new CanteenController();
		StallController stallCtrl = new StallController();

		JSONObject returnJson = new JSONObject();
		Gson gson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {

			@Override
			public boolean shouldSkipClass(Class<?> c) {
				return false;
			}

			@Override
			public boolean shouldSkipField(FieldAttributes c) {
				return (c.getDeclaringClass() == Stall.class && c.getName().equals("canteen"))
						|| (c.getDeclaringClass() == Food.class && c.getName().equals("stall"))
						|| (c.getDeclaringClass() == ModifierSection.class
								&& c.getName().equals("food"))
						|| (c.getDeclaringClass() == Modifier.class
								&& c.getName().equals("modifierSection"))
						|| (c.getDeclaringClass() == Modifier.class && c.getName().equals("food"));
			}

		}).registerTypeAdapter(Date.class, dateSerialize).create();

		try {
			String canteenIdString = request.getParameter("canteenId");
			int canteenId = Integer.parseInt(canteenIdString);
			System.out.println("canteenID: " + canteenId);

			Canteen canteen = canteenCtrl.getCanteen(canteenId);
			ArrayList<Stall> list = stallCtrl.getAllActiveStallsUnderCanteen(canteen);

			returnJson.put("canteen", canteen);
			returnJson.put("stalls", list);
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put("error", e.getMessage());
		}

		out.print(gson.toJson(returnJson));

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
