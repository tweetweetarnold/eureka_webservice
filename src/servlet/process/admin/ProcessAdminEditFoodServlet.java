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

import model.Food;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import controller.FoodController;
import dao.FoodDAO;

/**
 * Servlet implementation class ProcessAdminEditFoodServlet
 */
@WebServlet("/ProcessAdminEditFoodServlet")
public class ProcessAdminEditFoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FoodDAO foodDAO = new FoodDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcessAdminEditFoodServlet() {
		super();
		// TODO Auto-generated constructor stub
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
		// boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		response.setCharacterEncoding("UTF-8");

		request.setCharacterEncoding("UTF-8");
		FoodController foodController = new FoodController();
		String[] parameters = new String[8];
		String[] output = new String[2];

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
		byte[] image = null;
		try {
			// Parse the request
			List<FileItem> items = upload.parseRequest(request);
			Iterator<FileItem> iter = items.iterator();
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				if (!item.isFormField()) {
					image = item.get();
					if (image.length != 0) {
						String contentType = item.getContentType();
						System.out.println(contentType);
						if (!contentType.equals("image/jpeg")) {
							throw new Exception("Invalid image format");
						}
					}

					// //
					// parameters[index] = output[2];

					// session.setAttribute("url", url);
					// response.sendRedirect("result.jsp");
				} else {
					if (item.getFieldName().equals("chineseName")) {
						String inputValues = item.getString("UTF-8");
						parameters[index] = inputValues;
					} else {
						String inputValues = item.getString();
						parameters[index] = inputValues;
						System.out.println(item.getFieldName());
						System.out.println(inputValues);
					}
				}
				index++;
			}

			int foodId = Integer.parseInt(parameters[0]);
			double price = Double.parseDouble(parameters[4]);

			Food food = foodController.getFood(foodId);
			int stallId = food.getStall().getStallId();
			boolean isChinese = foodController.checkChineseWords(parameters[2]);
			if (!isChinese) {
				throw new Exception(parameters[2] + " is not a valid chinese word.");
			}
			boolean hasChanges = foodController.haveChangesInParameters(food, parameters[1], parameters[2], parameters[3], price,
					parameters[5]);
			if (hasChanges) {
				foodDAO.deleteFood(food);
				if (image.length == 0) {
					Food newFood = new Food(parameters[1], parameters[2], parameters[3], price,
							food.getImageDirectory(), food.getPublicId(), food.getStall());
					if (!food.getModifierList().isEmpty()) {
					foodController.updateModifierListToFood(newFood, food.getModifierSectionList());
					} else {
					 foodController.saveFood(newFood);
					}
				} else {
					output = foodController.replaceImage(food.getPublicId(), image);

					Food newFood = new Food(parameters[1], parameters[2], parameters[3], price, output[0], output[1],
							food.getStall());
					newFood.setWeatherConditions(parameters[5]);
					if (!food.getModifierList().isEmpty()) {
					foodController.updateModifierListToFood(newFood, food.getModifierSectionList());
					} else {
					 foodController.saveFood(newFood);
					}
				}
			} else {
				output = foodController.replaceImage(food.getPublicId(), image);
				food.setImageDirectory(output[0]);
				food.setPublicId(output[1]);
				foodController.updateFood(food);
			}

			// String currentPublicId = food.getPublicId();
			// if (currentPublicId != null) {
			// foodController.deleteImage(currentPublicId);
			// }
			//
			// if (image != null) {
			// output = foodController.imageUpload(image);
			// food.setImageDirectory(output[0]);
			// food.setPublicId(output[1]);
			// }
			//
			// food.setName(parameters[1]);
			// food.setDescription(parameters[3]);
			// food.setPrice(price);
			//
			//
			//
			// food.setWeatherConditions(parameters[5]);

			System.out.println("foodname: " + food.getName());
			// System.out.println("saving food...");
			// foodController.updateFood(food);

			session.setAttribute("success", "Food updated successfully.");

			response.sendRedirect("/eureka_webservice/LoadAdminViewFoodsServlet?stallId=" + stallId);

			// double price = Double.parseDouble(parameters[4]);
			// int stallId = Integer.parseInt(parameters[0]);
			//
			// Stall stall = stallController.getStall(stallId);
			// Food food = new Food(parameters[1], parameters[3], price,
			// output[0], output[1],stall);
			//
			// food.setWeatherConditions(parameters[5]);
			//
			// System.out.println("foodname: " + food.getName());
			// System.out.println("saving food...");
			// foodController.saveFood(food);
			//
			// session.setAttribute("success", "Food added successfully.");
			//
			// response.sendRedirect("/eureka_webservice/LoadAdminViewFoodsServlet?stallId="
			// + stallId);

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("error", e.getMessage());
			response.sendRedirect("/eureka_webservice/admin/food/edit.jsp");
		}

		// String foodIdString = request.getParameter("foodId");
		//
		// String name = request.getParameter("name");
		// String description = request.getParameter("description");
		// String priceString = request.getParameter("price");
		//
		// String imageDirectory = request.getParameter("imageDirectory");
		// String weatherConditions = request.getParameter("weatherConditions");
		//
		// try {
		// int foodId = Integer.parseInt(foodIdString);
		// double price = Double.parseDouble(priceString);
		//
		// Food food = foodController.getFood(foodId);
		// int stallId = food.getStall().getStallId();
		//
		// food.setName(name);
		// food.setDescription(description);
		// food.setPrice(price);
		// food.setImageDirectory(imageDirectory);
		// food.setWeatherConditions(weatherConditions);
		//
		// System.out.println("foodname: " + food.getName());
		// System.out.println("saving food...");
		// foodController.updateFood(food);
		//
		// session.setAttribute("success", "Food updated successfully.");
		//
		// response.sendRedirect("/eureka_webservice/LoadAdminViewFoodsServlet?stallId="
		// + stallId);
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

	}
}
