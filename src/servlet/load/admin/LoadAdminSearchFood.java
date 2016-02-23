package servlet.load.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Food;
import services.Indexer;

/**
 * Servlet implementation class LoadAdminSearchFood
 */
@WebServlet("/LoadAdminSearchFood")
public class LoadAdminSearchFood extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadAdminSearchFood() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String searchQuery = request.getParameter("food");
		System.out.println(searchQuery);
		Indexer indexer = new Indexer();
		System.out.println("Servlet");
		List<Food> foodList = indexer.search(searchQuery);
		System.out.println(foodList.size());
		RequestDispatcher rd = request.getRequestDispatcher("/eureka_webservice/admin/login.jsp");
		request.setAttribute("searchResults", foodList);
		rd.forward(request, response);
		
		
	}

}
