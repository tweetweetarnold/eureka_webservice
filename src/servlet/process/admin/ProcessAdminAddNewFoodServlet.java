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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
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
		int index = 0;
		try {
			// Parse the request
			List<FileItem> items = upload.parseRequest(request);
			Iterator<FileItem> iter = items.iterator();
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				if (!item.isFormField()) {

					String contentType = item.getContentType();
					if (!contentType.equals("image/jpeg")) {
						throw new Exception("Invalid image format");
					}

					image = item.get();

					// //
					// parameters[index] = output[2];

					// session.setAttribute("url", url);
					// response.sendRedirect("result.jsp");
				} else {
					if (item.getFieldName().equals("chineseName")) {
						// String inputValues = item.getString();
						// parameters[index] = inputValues;
					} else {
						String inputValues = item.getString();
						parameters[index] = inputValues;
						System.out.println(item.getFieldName());
						System.out.println(inputValues);
					}
				}
				index++;
			}

			double price = Double.parseDouble(parameters[4]);
			int stallId = Integer.parseInt(parameters[0]);

			output = foodController.imageUpload(image);

			Stall stall = stallController.getStall(stallId);
			Food food = new Food(parameters[1], parameters[3], price, output[0], output[1], stall);

			food.setWeatherConditions(parameters[5]);

			System.out.println("foodname: " + food.getName());
			System.out.println("saving food...");
			foodController.saveFood(food);

			session.setAttribute("success", "Food added successfully.");

			response.sendRedirect("/eureka_webservice/LoadAdminViewFoodsServlet?stallId=" + stallId);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// String name = request.getParameter("name");
		// String description = request.getParameter("description");
		// String priceString = request.getParameter("price");
		// String stallIdString = request.getParameter("stallId");
		// System.out.println("String: " + stallIdString);
		//
		// String imageDirectory = request.getParameter("imageDirectory");
		// String weatherConditions = request.getParameter("weatherConditions");
		//
		// try {
		// double price = Double.parseDouble(priceString);
		// int stallId = Integer.parseInt(stallIdString);
		//
		// Stall stall = stallController.getStall(stallId);
		// Food food = new Food(name, description, price, imageDirectory,
		// stall);
		//
		// food.setWeatherConditions(weatherConditions);
		//
		// System.out.println("foodname: " + food.getName());
		// System.out.println("saving food...");
		// foodController.saveFood(food);
		//
		// session.setAttribute("success", "Food added successfully.");
		//
		// response.sendRedirect("/eureka_webservice/LoadAdminViewFoodsServlet?stallId="
		// + stallId);
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}

}
