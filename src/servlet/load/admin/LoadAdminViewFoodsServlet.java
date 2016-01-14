package servlet.load.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Food;
import model.Stall;
import controller.FoodController;
import controller.StallController;

/**
 * Servlet implementation class LoadAdminViewFoodsServlet
 */
@WebServlet("/LoadAdminViewFoodsServlet")
public class LoadAdminViewFoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadAdminViewFoodsServlet() {
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
		HttpSession session = request.getSession();

		StallController stallController = new StallController();
		FoodController foodController = new FoodController();

		String stallIdString = request.getParameter("stallId");
		int stallId = Integer.parseInt(stallIdString);

		Stall stall = stallController.getStall(stallId);
		ArrayList<Food> list = foodController.getAllFoodsUnderStall(stall);

		session.setAttribute("stallId", stallId);
		session.setAttribute("stallName", stall.getName());
		session.setAttribute("foodList", list);

		response.sendRedirect("/eureka_webservice/admin/food/view.jsp");
	}

}
