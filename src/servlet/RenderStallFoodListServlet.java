package servlet;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		String stallId = request.getParameter("stallId");
		
		Stall s = StallDAO.getStall(Integer.parseInt(stallId));
		Set<Food> foodList = s.getFoodList();
		
		HttpSession session = request.getSession();
		session.setAttribute("stallFoodList", foodList);
		
		response.sendRedirect("stallFoodList.jsp");
		
	}

}
