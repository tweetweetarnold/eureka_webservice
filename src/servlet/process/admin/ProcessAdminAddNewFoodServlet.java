package servlet.process.admin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import controller.FoodController;

/**
 * Servlet implementation class ProcessAdminAddNewFoodServlet
 */
@WebServlet("/ProcessAdminAddNewFoodServlet")
public class ProcessAdminAddNewFoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcessAdminAddNewFoodServlet() {
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
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");

		FoodController foodController = new FoodController();

		// Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// Configure a repository (to ensure a secure temp location is used)
		ServletContext servletContext = this.getServletConfig().getServletContext();

		File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		factory.setRepository(repository);

		// Create a new file upload handler and set max size
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(1024 * 1024 * 1000);

		int stallId = 0;

		try {
			JSONParser parser = new JSONParser();
			JSONObject data = (JSONObject) parser.parse(request.getReader());
			System.out.println(data);

			JSONObject food = (JSONObject) data.get("food");
			String name = (String) food.get("name");
			String chineseName = (String) food.get("chineseName");
			String description = (String) food.get("description");
			String weatherConditions = (String) food.get("weatherConditions");
			double price = Double.parseDouble((String) food.get("price"));
			stallId = Integer.parseInt((String) food.get("stallId"));
			byte[] image = food.get("file").toString().getBytes("utf-8");
			JSONArray modifierList = (JSONArray) food.get("modifierList");

			foodController.addFood2(name, chineseName, description, weatherConditions, image,
					modifierList, price, stallId);
			// stallId = foodController.addFood(upload, request);

			session.setAttribute("success", "Food added successfully.");

			// response.sendRedirect("/eureka_webservice/LoadAdminViewFoodsServlet?stallId=" +
			// stallId);

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("error", e.getMessage());
			response.sendRedirect("/eureka_webservice/admin/food/add.jsp?stallId=" + stallId);
		}

	}
}
