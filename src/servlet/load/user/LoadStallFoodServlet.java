package servlet.load.user;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Food;
import model.Stall;

import org.hibernate.Session;

import value.StringValues;
import connection.MyConnection;
import dao.StallDAO;

/**
 * Servlet implementation class LoadStallFoodServlet
 */
@WebServlet("/LoadStallFoodServlet")
public class LoadStallFoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	StallDAO stallDAO = new StallDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadStallFoodServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		String stallId = request.getParameter("stallId");
		System.out.println("StallID: " + stallId);

		Session session2 = MyConnection.getSession();
		Stall s = stallDAO.getStall(Integer.parseInt(stallId));
		System.out.println("stall before update: " + s.toString());
		session2.update(s);
		System.out.println("stall after update: " + s.toString());
		System.out.println("stall s: " + s.toString());
		System.out.println("size: " + s.getFoodList().size());

		Set<Food> foodList = s.getFoodList();
		Set<Food> displayFoodList = new HashSet<Food>();
		Iterator<Food> iter = foodList.iterator();
		while (iter.hasNext()) {
			Food food = (Food) iter.next();
			String status = food.getStatus();
			if (!status.equals(StringValues.ARCHIVED)) {
				displayFoodList.add(food);
			}
		}

		session2.close();

		HttpSession session = request.getSession();
		session.setAttribute("stallName", s.getName());
		session.setAttribute("foodList", displayFoodList);

		response.sendRedirect("/eureka_webservice/pages/stall-foods.jsp");

	}
}
