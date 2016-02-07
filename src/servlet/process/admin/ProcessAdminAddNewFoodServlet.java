package servlet.process.admin;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

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
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import model.Food;
import model.Stall;
import controller.FoodController;
import controller.StallController;

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
		// boolean isMultipart = ServletFileUpload.isMultipartContent(request);
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
			foodController.addFood(upload, request);

			session.setAttribute("success", "Food added successfully.");

			response.sendRedirect("/eureka_webservice/LoadAdminViewFoodsServlet?stallId=" + stallId);

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("error", e.getMessage());
			response.sendRedirect("/eureka_webservice/admin/food/add.jsp?stallId=" + stallId);
		}

	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setCharacterEncoding("UTF-8");

		request.setCharacterEncoding("UTF-8");
		// boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		FoodController foodController = new FoodController();
		StallController stallController = new StallController();
		String[] parameters = new String[6];
		String[] output = new String[2];
		byte[] image = null;

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
		int index = 0;

		try {
			JSONParser parser = new JSONParser();
			JSONObject data = (JSONObject) parser.parse(request.getReader());
			System.out.println(data);

			stallId = Integer.parseInt((String) data.get("stallId"));
			String name = (String) data.get("name");
			String chineseName = (String) data.get("chineseName");
			String description = (String) data.get("description");
			double price = Double.parseDouble((String) data.get("price"));
			String weatherConditions = (String) data.get("weatherConditions");

			// Parse the request
			// List<FileItem> items = upload.parseRequest(request);
			// Iterator<FileItem> iter = items.iterator();
			// while (iter.hasNext()) {
			// FileItem item = (FileItem) iter.next();
			// if (!item.isFormField()) {
			//
			// String contentType = item.getContentType();
			// if (!contentType.equals("image/jpeg")) {
			// throw new Exception("Invalid image format");
			// }
			//
			// image = item.get();
			//
			// } else {
			// if (item.getFieldName().equals("chineseName")) {
			// String inputValues = item.getString("UTF-8");
			// parameters[index] = inputValues;
			// } else {
			// String inputValues = item.getString();
			// parameters[index] = inputValues;
			// System.out.println(item.getFieldName());
			// System.out.println(inputValues);
			// }
			// }
			// index++;
			// }

			// double price = Double.parseDouble(parameters[4]);
			// stallId = Integer.parseInt(parameters[0]);

			Stall stall = stallController.getStall(stallId);
			// if (!chineseName.isEmpty()) {
			// boolean isChinese = foodController.checkChineseWords(chineseName);
			// if (!isChinese) {
			// throw new Exception(chineseName + " is not a valid chinese word.");
			// }
			// }
			boolean foodExists = foodController.checkFoodExists(name, stall);
			if (foodExists) {
				throw new Exception(name + " already exists in " + stall.getName());
			}

			// output = foodController.imageUpload(image);
			Food food = new Food(name, chineseName, description, price, null, null, stall);

			food.setWeatherConditions(weatherConditions);

			System.out.println("foodname: " + food.getName());
			System.out.println("saving food...");
			foodController.saveFood(food);

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
