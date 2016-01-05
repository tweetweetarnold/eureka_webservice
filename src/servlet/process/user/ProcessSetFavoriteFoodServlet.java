package servlet.process.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EmployeeDAO;
import dao.FoodDAO;
import model.Employee;
import model.Food;

/**
 * Servlet implementation class ProcessSetFavoriteFood
 */
@WebServlet("/ProcessSetFavoriteFoodServlet")
public class ProcessSetFavoriteFoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcessSetFavoriteFoodServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	public void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		System.out.println("");
		System.out.println("****** ProcessSetFavoriteFoodServlet ******");
		out.println("ProcessSetFavoriteFoodServlet");
		HttpSession session = request.getSession();
		FoodDAO foodDAO = new FoodDAO();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		try {
			Employee employee = (Employee) session.getAttribute("user");
			// Retrieve foodId from parameters
			// ** might need to change in future because its from foodlist array
			String foodId = request.getParameter("foodId");
			Food favoriteFood = foodDAO.getFood(Integer.parseInt(foodId));
			System.out.println("food retrieved: " + favoriteFood.getName());
			// Set Favorite Food
			employee.setFavoriteFood(favoriteFood);
			employeeDAO.updateEmployee(employee);
			// Redirect back to homepage
			session.setAttribute("success", "Favorite food has been updated!");
			response.sendRedirect("PLEASECHANGEME.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
			session.setAttribute("error", "Oops! Something went wrong! Please try again later");
			//Change the redirect
			response.sendRedirect("PLEASECHANGEME.jsp");
		}

	}

}
