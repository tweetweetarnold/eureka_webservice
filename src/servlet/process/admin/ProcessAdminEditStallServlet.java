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

import model.Food;
import model.Stall;
import controller.StallController;

/**
 * Servlet implementation class ProcessAdminEditStallServlet
 */
@WebServlet("/ProcessAdminEditStallServlet")
public class ProcessAdminEditStallServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcessAdminEditStallServlet() {
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
					image = item.get();
					if (image.length != 0) {
						String contentType = item.getContentType();
						if (!contentType.equals("image/jpeg")) {
							throw new Exception("Invalid image format");
						}
					}

				} else {

					String inputValues = item.getString();
					parameters[index] = inputValues;
					System.out.println(item.getFieldName());
					System.out.println(inputValues);

				}
				index++;
			}

			int stallId = Integer.parseInt(parameters[0]);
			long contactNo = stallController.validatesContactNumber(parameters[2]);

			Stall stall = stallController.getStall(stallId);
			int canteenId = stall.getCanteen().getCanteenId();

			boolean hasChanges = stallController.haveChangesInParameters(stall, parameters[1], contactNo);
			if (hasChanges) {
				stallController.deleteStall(stall);
				if (image.length == 0) {
					Stall newStall = new Stall(parameters[1], contactNo, stall.getCanteen(), new HashSet<Food>(),
							stall.getImageDirectory(), stall.getPublicId());

					stallController.updateFoodListToStall(stall.getFoodList(), newStall);

					stallController.updateStall(newStall);
				} else {
					output = stallController.replaceOldImage(stall.getPublicId(), image);
					Stall newStall = new Stall(parameters[1], contactNo, stall.getCanteen(), new HashSet<Food>(),
							output[0], output[1]);
					stallController.updateFoodListToStall(stall.getFoodList(), newStall);
					stallController.saveStall(newStall);
				}
			} else {
				if (image.length == 0) {
					throw new Exception("The details entered are the same as the current food details");
				} else {
					output = stallController.replaceOldImage(stall.getPublicId(), image);
					stall.setImageDirectory(output[0]);
					stall.setPublicId(output[1]);
					stallController.updateStall(stall);
				}
			}

			session.setAttribute("success", "Stall updated successfully.");

			response.sendRedirect("/eureka_webservice/LoadAdminViewStallsServlet?canteenId=" + canteenId);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// String stallIdString = request.getParameter("stallId");
		// String name = request.getParameter("name");
		// String contactNoString = request.getParameter("contactNo");
		// String imageDirectory = request.getParameter("imageDirectory");
		//
		// int stallId = Integer.parseInt(stallIdString);
		// long contactNo = Long.parseLong(contactNoString);
		//
		// Stall stall = stallController.getStall(stallId);
		// int canteenId = stall.getCanteen().getCanteenId();
		//
		// stall.setName(name);
		// stall.setContactNo(contactNo);
		// stall.setImageDirectory(imageDirectory);
		//
		// System.out.println("stallname: " + stall.getName());
		// System.out.println("saving stall...");
		// stallController.updateStall(stall);
		//
		// session.setAttribute("success", "Stall updated successfully.");
		//
		// response.sendRedirect("/eureka_webservice/LoadAdminViewStallsServlet?canteenId="
		// + canteenId);
	}

}
