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
		int index = 0;
		String[] parameters = new String[6];
		byte[] image = null;
		
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
			//End of parsing request
			
			stallId = Integer.parseInt(parameters[0]);
			foodController.processAddingFood(image, parameters, stallId);

			session.setAttribute("success", "Food added successfully.");

			response.sendRedirect("/eureka_webservice/LoadAdminViewFoodsServlet?stallId=" + stallId);

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("error", e.getMessage());
			response.sendRedirect("/eureka_webservice/admin/food/add.jsp?stallId=" + stallId);
		}

	}
}