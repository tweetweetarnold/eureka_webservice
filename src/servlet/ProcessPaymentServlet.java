package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.FoodOrderController;
import dao.EmployeeDAO;
import model.Employee;
import model.FoodOrder;

/**
 * Servlet implementation class ProcessPaymentServlet
 */
@WebServlet("/ProcessPaymentServlet")
public class ProcessPaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EmployeeDAO employeeDAO = new EmployeeDAO();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessPaymentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		try {
			String transactionId = (String) request.getParameter("tx");
			String transactionStatus = (String) request.getParameter("st");
			String transactionAmount = (String) request.getParameter("amt");
			
			System.out.println(transactionId);
			System.out.println(transactionAmount);
			Employee employee = (Employee) session.getAttribute("user");
			FoodOrderController foodOrderController = new FoodOrderController();
			List<FoodOrder> foodOrderList = foodOrderController.getFoodOrderSet(employee.getEmail());
			//if transaction is successful, it will update the amount owed and status of the order to "Paid"
			if (transactionStatus.equals("Completed")) {
			//retrieve the user to update its amount owed
				employee.setAmountOwed(0.00);
				employeeDAO.updateEmployee(employee);
				
				//update the status of the user's orders from "Submitted" to "Paid"
				//iterate through user's list of food orders and update the status to "Paid"
				for (FoodOrder f: foodOrderList) {
					String orderStatus = f.getStatus();
					if (orderStatus.equals("Submitted")) {
						f.setStatus("Paid");
						foodOrderController.updateFoodOrder(f);
					}
				}
				System.out.println("Updated amount owed: " + employee.getAmountOwed());
				session.setAttribute("paymentSuccess", "Thank you for your payment.Your transaction has been completed and a receipt for your purchase has been emailed to you."
						+ " You may log in to your account at www.paypal.com/sg to view details of this transaction.");
			} else if (transactionStatus.equals("Failed")) {
				for (FoodOrder f: foodOrderList) {
					String orderStatus = f.getStatus();
					if (orderStatus.equals("Submitted")) {
						f.setStatus("Failed");
						foodOrderController.updateFoodOrder(f);
					}
				}
				throw new Exception("Payment transaction has encountered some problems. Please check your Paypal account or place a new order.");
			} else if (transactionStatus.equals("Pending")) {
				for (FoodOrder f: foodOrderList) {
					String orderStatus = f.getStatus();
					if (orderStatus.equals("Submitted")) {
						f.setStatus("Pending");
						foodOrderController.updateFoodOrder(f);
					}
				}
				throw new Exception("This payment is being processed. Allow up to 4 days for it to complete.");
			} else if (transactionStatus.equals("Held")) {
				for (FoodOrder f: foodOrderList) {
					String orderStatus = f.getStatus();
					if (orderStatus.equals("Submitted")) {
						f.setStatus("Held");
						foodOrderController.updateFoodOrder(f);
					}
				}
				throw new Exception("This payment is being held. The payment may be under review by Paypal.");
			}
			response.sendRedirect("payment.jsp");
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
			session.setAttribute("error", e.getMessage());
			response.sendRedirect("payment.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}