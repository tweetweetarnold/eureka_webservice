package controller;

import java.util.ArrayList;

import model.Canteen;
import model.Company;
import model.OrderWindow;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import dao.OrderWindowDAO;

/**
 * Process the business logic of the order window for the application
 * 
 * 
 */
public class OrderWindowController {
	OrderWindowDAO orderWindowDAO = new OrderWindowDAO();

	/**
	 * Creates a default constructor for the OrderWindowController
	 */
	public OrderWindowController() {
	}

	/**
	 * Creates a new order window period with start date and end date for the specified company and
	 * canteen
	 * 
	 * @param startDate The start date of the order window
	 * @param endDate The end date of the order window
	 * @param company The Company object specified in the order window
	 * @param canteen The Canteen object specified in the order window
	 */
	public void createNewOrderindow(DateTime startDate, DateTime endDate, Company company,
			Canteen canteen) {
		orderWindowDAO.saveOrderWindow(new OrderWindow(startDate, endDate, company, canteen));
	}

	/**
	 * Creates a new order window period with start date and duration for the specified company and
	 * canteen
	 * 
	 * @param startDate The start date of the order window
	 * @param duration The duration of the order window
	 * @param company The Company object specified in the order window
	 * @param canteen The Canteen object specified in the order window
	 */
	public void createNewOrderWindow(DateTime startDate, Duration duration, Company company,
			Canteen canteen) {
		orderWindowDAO.saveOrderWindow(new OrderWindow(startDate, duration, company, canteen));
	}
	
	/**
	 * Retrieves the OrderWindow based on the provided ID
	 * @param orderWindowId The ID of the OrderWindow
	 * @return The OrderWindow object that has the provided ID
	 */
	public OrderWindow getOrderWindow(Integer orderWindowId) {
		return orderWindowDAO.getOrderWindow(orderWindowId);
	}

	/**
	 * Get all OrderWindows with status "Opened" for the given Company
	 * 
	 * @param company The company whose "Opened" OrderWindows are to be retrieved
	 * @return Returns an ArrayList of OrderWindows with status "Opened" for the given Company
	 */
	public ArrayList<OrderWindow> getAllOpenedWindowsForCompany(Company company) {
		return orderWindowDAO.getAllOpenedWindowsForCompany(company);
	}

	/**
	 * Get all OrderWindows with status "Opened"
	 * 
	 * @return Returns an ArrayList of OrderWindows with status "Opened"
	 */
	public ArrayList<OrderWindow> getAllOpenedWindows() {
		return orderWindowDAO.getAllOpenedWindows();
	}

}
