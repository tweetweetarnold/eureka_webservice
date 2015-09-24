package servlet;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Canteen;

import org.json.simple.JSONArray;

import controller.CanteenController;
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
		JSONArray jsonArray = new JSONArray();
		Iterator iter = canteenList.iterator();
		
		while(iter.hasNext()){
			Canteen tempCanteen = (Canteen) iter.next();
			jsonArray.add(tempCanteen);
		}
		//for test app
		RequestDispatcher rd = request.getRequestDispatcher("TestApp.jsp");
		request.setAttribute("canteenArray", jsonArray);
		rd.forward(request,response);
		
		PrintWriter out = response.getWriter();
		// Assuming your json object is **jsonObject**, perform the following, it will return your json object  
		out.print(jsonArray);
		out.flush();
		
		
		 
	}

}
