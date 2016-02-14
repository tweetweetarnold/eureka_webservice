package servlet.load.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import controller.OrderWindowController;
import model.Food;
import model.Modifier;
import model.ModifierSection;
import model.OrderWindow;
import model.PriceModifier;
import model.Stall;

/**
 * Servlet implementation class GetAllOrderWindowsServlet
 */
@WebServlet("/GetAllOrderWindowsServlet")
public class GetAllOrderWindowsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetAllOrderWindowsServlet() {
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
						|| (c.getDeclaringClass() == ModifierSection.class
								&& c.getName().equals("food"))
						|| (c.getDeclaringClass() == Modifier.class
								&& c.getName().equals("modifierSection"))
						|| (c.getDeclaringClass() == Modifier.class && c.getName().equals("food")
								|| c.getDeclaringClass() == PriceModifier.class
										&& c.getName().equals("orderWindow"));
			}

		}).create();

		OrderWindowController orderWindowController = new OrderWindowController();

		ArrayList<OrderWindow> list = orderWindowController.getAllNonDeletedOrderWindows();

		out.print(gson.toJson(list));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
