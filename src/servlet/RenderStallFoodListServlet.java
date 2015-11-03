package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import connection.MyConnection;
import model.Food;
import model.Stall;
import dao.StallDAO;

/**
 * Servlet implementation class RenderStallFoodListServlet
 */
@WebServlet("/RenderStallFoodListServlet")
public class RenderStallFoodListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RenderStallFoodListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		
//		try {
			String stallId = request.getParameter("stallId");
			System.out.println("StallID: " + stallId);

			Session session2 = MyConnection.getSession();
			Stall s = StallDAO.getStall(Integer.parseInt(stallId));
			System.out.println("stall before update: " + s.toString());
			session2.update(s);
			System.out.println("stall after update: " + s.toString());
			System.out.println("stall s: " + s.toString());
			System.out.println("size: " + s.getFoodList().size());
			Set<Food> foodList = s.getFoodList();
			session2.close();

			HttpSession session = request.getSession();
			session.setAttribute("stallName", s.getName());
			session.setAttribute("stallFoodList", foodList);

			response.sendRedirect("stallFoodList.jsp");
//		} catch (Exception e) {
//			out.println(e.getMessage());
//		}

	}

}
