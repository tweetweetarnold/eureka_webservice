package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CanteenDAO;
import model.Canteen;

/**
 * Servlet implementation class AddNewCanteenServlet
 */
@WebServlet("/AddNewCanteenServlet")
public class AddNewCanteenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewCanteenServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
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

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		System.out.println("****** AddNewCanteenServlet ******");
		HttpSession session = request.getSession();
		HashMap<String, String> userInput = new HashMap<>();
		
		
		try {
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			userInput.put("name", name);
			userInput.put("address", address);
			CanteenDAO canteenDAO = new CanteenDAO();
			ArrayList<Canteen> canteenList = canteenDAO.getAllCanteens();
			for(Canteen c: canteenList){
				if(name.equals(c.getName())){
					throw new Exception("CanteenAlreadyExists");
				}
			}
			Canteen canteen = new Canteen(name, address, new HashSet());
			
			canteenDAO.saveCanteen(canteen);
			response.sendRedirect("adminHomepage.jsp");
		}catch(Exception e){
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
			session.setAttribute("error", "Soemthing went wrong");
			session.setAttribute("userInput", userInput);
			response.sendRedirect("addNewCanteenTEST.jsp");
		}
	}
}
