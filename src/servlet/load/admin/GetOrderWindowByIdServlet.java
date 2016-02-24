package servlet.load.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import controller.OrderWindowController;
import model.Food;
import model.Modifier;
import model.ModifierSection;
import model.OrderWindow;
import model.PriceModifier;
import model.Stall;

/**
 * Servlet implementation class GetOrderWindowByIdServlet
 */
@WebServlet("/GetOrderWindowByIdServlet")
public class GetOrderWindowByIdServlet extends HttpServlet {
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
	public GetOrderWindowByIdServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("applcation/json");
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
						|| (c.getDeclaringClass() == ModifierSection.class
								&& c.getName().equals("food"))
						|| (c.getDeclaringClass() == Modifier.class
								&& c.getName().equals("modifierSection"))
						|| (c.getDeclaringClass() == Modifier.class && c.getName().equals("food")
								|| c.getDeclaringClass() == PriceModifier.class
										&& c.getName().equals("orderWindow"));
			}

		}).registerTypeAdapter(Date.class, dateSerialize).create();

		OrderWindowController orderWindowCtrl = new OrderWindowController();
		OrderWindow window = null;

		try {
			int orderWindowId = Integer.parseInt(request.getParameter("windowId"));
			System.out.println("orderWindowId: " + orderWindowId);

			window = orderWindowCtrl.getOrderWindow(orderWindowId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		out.print(gson.toJson(window));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
