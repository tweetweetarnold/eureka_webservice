package servlet.load.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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

import controller.FinanceController;
import model.Employee;
import model.Food;
import model.Modifier;
import model.ModifierSection;
import model.Stall;
import services.MyJSONSerializer;

/**
 * Servlet implementation class GetUsersWithOutstandingPaymentServlet
 */
@WebServlet("/GetUsersWithOutstandingPaymentServlet")
public class GetUsersWithOutstandingPaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetUsersWithOutstandingPaymentServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		FinanceController financeController = new FinanceController();

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

		}).registerTypeAdapter(Date.class, MyJSONSerializer.dateSerialize).create();

		try {
			ArrayList<Employee> list = financeController.getAllUsersWithOutstandingPayment(0,
					false);

			out.print(gson.toJson(list));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
