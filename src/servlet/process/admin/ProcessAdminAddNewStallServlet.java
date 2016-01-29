package servlet.process.admin;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
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

import model.Canteen;
import model.Food;
import model.Stall;
import controller.CanteenController;
import controller.StallController;

/**
 * Servlet implementation class ProcessAdminAddNewStallServlet
 */
@WebServlet("/ProcessAdminAddNewStallServlet")
public class ProcessAdminAddNewStallServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcessAdminAddNewStallServlet() {
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
		StallController stallController = new StallController();
		CanteenController canteenController = new CanteenController();

		String[] parameters = new String[3];
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

				} else {

					String inputValues = item.getString();
					parameters[index] = inputValues;
					System.out.println(item.getFieldName());
					System.out.println(inputValues);

				}
				index++;
			}

			int canteenId = Integer.parseInt(parameters[0]);
			long contactNo = stallController.validatesContactNumber(parameters[2]);

			Canteen c = canteenController.getCanteen(canteenId);
			boolean stallExists = stallController.checkStallExists(parameters[1], c);
			if (stallExists) {
				throw new Exception(parameters[1] + " already exists in " + c.getName());
			}
			output = stallController.imageUpload(image);

			Stall s = new Stall(parameters[1], contactNo, c, new HashSet<Food>(), output[0], output[1]);

			System.out.println("stallname: " + s.getName());
			System.out.println("saving food...");
			stallController.saveStall(s);

			session.setAttribute("success", "Stall added successfully.");

			response.sendRedirect("/eureka_webservice/LoadAdminViewStallsServlet?canteenId=" + canteenId);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// String name = request.getParameter("name");
		// String contactNoString = request.getParameter("contactNo");
		// String canteenIdString = request.getParameter("canteenId");
		// System.out.println("String: " + canteenIdString);
		//
		// String imageDirectory = request.getParameter("imageDirectory");
		//
		// try {
		// int canteenId = Integer.parseInt(canteenIdString);
		// long contactNo = Long.parseLong(contactNoString);
		//
		// Canteen c = canteenController.getCanteen(canteenId);
		// Stall s = new Stall(name, contactNo, c, null, imageDirectory);
		//
		// System.out.println("stallname: " + s.getName());
		// System.out.println("saving food...");
		// stallController.saveStall(s);
		//
		// session.setAttribute("success", "Stall added successfully.");
		//
		// response.sendRedirect("/eureka_webservice/LoadAdminViewStallsServlet?canteenId="
		// + canteenId);
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

	}
}
