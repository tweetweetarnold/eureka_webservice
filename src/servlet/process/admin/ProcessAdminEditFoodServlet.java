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

import controller.FoodController;
import dao.FoodDAO;
import model.Food;

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
		// boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		response.setCharacterEncoding("UTF-8");
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

		int index = 0;
		byte[] image = null;
		String[] parameters = new String[8];

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
				} else {
					if (item.getFieldName().equals("chineseName")) {
						String inputValues = item.getString("UTF-8");
						parameters[index] = inputValues;
					} else {
						String inputValues = item.getString();
						parameters[index] = inputValues;
						System.out.println(item.getFieldName());
						System.out.println("here: "  + index);
						System.out.println(inputValues);
					}
				}
				index++;
			}
			int foodId = Integer.parseInt(parameters[0]);
			Food food = foodController.getFood(foodId);
			int stallId = food.getStall().getStallId();
			foodController.editFood(image, parameters, food, stallId);

			session.setAttribute("success", "Food updated successfully.");

			response.sendRedirect("/eureka_webservice/admin/food/view.jsp?stallId=" + stallId);

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("error", e.getMessage());
			response.sendRedirect("/eureka_webservice/admin/food/edit.jsp");
		}
	}
}
