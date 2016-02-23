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

import model.Stall;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

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
		StallController stallController = new StallController();
		String[] parameters = new String[3];

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
				}
				index++;
			}
			// End of parsing the request

			int stallId = Integer.parseInt(parameters[0]);
			Stall currentStall = stallController.getStall(stallId);
			int canteenId = currentStall.getCanteen().getCanteenId();

			stallController.processEditingStall(image, parameters, currentStall);

			session.setAttribute("success", "Stall updated successfully.");

			response.sendRedirect("/eureka_webservice/admin/stall/view.jsp?canteenId=" + canteenId);

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("error", e.getMessage());
			response.sendRedirect("/eureka_webservice/admin/stall/edit.jsp");
		}
	}

}
