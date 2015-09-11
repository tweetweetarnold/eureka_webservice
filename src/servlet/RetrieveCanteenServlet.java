package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import controller.CanteenController;
import entity.Canteen;
/**
 * Servlet implementation class RetrieveCanteenServlet
 */
@WebServlet("/RetrieveCanteenServlet")
public class RetrieveCanteenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetrieveCanteenServlet() {
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
		CanteenController canteenController = new CanteenController();
		List<Canteen> canteenList = canteenController.retrieveAll();
		JSONArray JsonArray = new JSONArray();
		JsonArray.add(canteenList);
		
		RequestDispatcher rd = request.getRequestDispatcher("testRetrieveUser.jsp");
		request.setAttribute("canteenArray", JsonArray);
		rd.forward(request,response);
	}

}
