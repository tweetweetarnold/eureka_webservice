package servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import controller.FileUploadController;

/**
 * Servlet implementation class FileUploadServlet
 */
@WebServlet("/FileUpload")
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		FileUploadController fileUploadController = new FileUploadController();
		//Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// Configure a repository (to ensure a secure temp location is used)
		ServletContext servletContext = this.getServletConfig().getServletContext();
		File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		factory.setRepository(repository);
		// Create a new file upload handler and set max size
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(1024 * 1024 * 1000);
		ArrayList<String> errorList = null;
        try {
            // Parse the request
            FileItemIterator iter = upload.getItemIterator(request);
            while (iter.hasNext()) {
                FileItemStream item = iter.next();
                InputStream is = item.openStream();
                if (!item.isFormField()) {
//					***working in-progress***         	
//                	String fileFormat = item.getContentType();
//                	if (fileFormat.equals(".csv")) {
//                		
//                	}
                    //pass it to controller to read file
                	String itemName = item.getName();
	                if (itemName.contains("Canteen.csv")) {
	                	errorList= fileUploadController.processCanteenUpload(is);
		               	  if (errorList.size() > 0) {
		               		  throw new Exception("There are Errors in the Canteen.csv file");
		               	  }
		            } else if (itemName.contains("Stall.csv")) {
	                	 // String canteenName = request.getParameter("canteenName");
		            	errorList = fileUploadController.processStallUpload(is);
	                	if (errorList.size() > 0) {
	                		throw new Exception("There are Errors in the Stall.csv file");
	                	}
	                } else if (itemName.contains("Food.csv")) {
	                	errorList = fileUploadController.processFoodUpload(is);
	                	if (errorList.size() > 0) {
	                		throw new Exception("There are Errors in the Food.csv file");
	                	}
	                } else if (itemName.contains("Modifier.csv")) {
	                	errorList = fileUploadController.processModifierUpload(is);
	                	if (errorList.size() > 0) {
	                		throw new Exception("There are Errors in the Modifier.csv file");
	                	}
	                } else {
	                	throw new Exception("Invalid file name. Please upload a correct file name.");
	                }
                }
            }
				
			session.setAttribute("success", "File Uploaded Successfully");
			response.sendRedirect("adminFileUpload.jsp");
		} catch (Exception e) {
			errorList.add(e.getMessage());
			session.setAttribute("error", errorList);
			response.sendRedirect("adminFileUpload.jsp");
		}
	}
	

}
