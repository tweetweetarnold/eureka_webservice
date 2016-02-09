package servlet.load.admin;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DummyDB;

/**
 * Servlet implementation class LoadAdminSearchData
 */
@WebServlet("/LoadAdminSearchData")
public class LoadAdminSearchData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadAdminSearchData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DummyDB db = new DummyDB();
		 
	    String query = request.getParameter("q");
	     
	    List<String> countries = db.getData(query);
	 
	    Iterator<String> iterator = countries.iterator();
	    while(iterator.hasNext()) {
	        String country = (String)iterator.next();
	        System.out.println(country);
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DummyDB db = new DummyDB();
		 
	    String query = request.getParameter("q");
	     
	    List<String> countries = db.getData(query);
	 
	    Iterator<String> iterator = countries.iterator();
	    while(iterator.hasNext()) {
	        String country = (String)iterator.next();
	        System.out.println(country);
	    }
	}

}
