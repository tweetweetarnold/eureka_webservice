package servlet.load.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import controller.CanteenController;
import model.Food;
import model.Modifier;
import model.ModifierSection;
import model.PriceModifier;
import model.Stall;
import services.MyJSONSerializer;

/**
 * Servlet implementation class GetAllFoodsServlet
 */
@WebServlet("/GetAllFoodsServlet")
public class GetAllFoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetAllFoodsServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();

		CanteenController canteenCtrl = new CanteenController();

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
						|| (c.getDeclaringClass() == Modifier.class && c.getName().equals("food")
								|| c.getDeclaringClass() == PriceModifier.class
										&& c.getName().equals("orderWindow"));
			}

		}).registerTypeAdapter(Date.class, MyJSONSerializer.dateSerialize).create();

		try {
			List<Food> list = canteenCtrl.getAllFood();

			JSONArray arr = new JSONArray();
			arr.addAll(list);
			out.println(gson.toJson(arr));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
