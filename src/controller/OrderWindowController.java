package controller;

import java.util.ArrayList;

import model.Canteen;
import model.Company;
import model.OrderWindow;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.Duration;

import dao.OrderWindowDAO;

/**
 * Process the business logic of the order window for the application
 * 
 * @author SMU Team Eureka
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
	 * Creates a new order window period with start and end date and the discount for the specific company and
	 * canteen
	 * 
	 * @param startDate The start date of the order window
	 * @param endDate The end date of the order window
	 * @param company The Company object specified in the order window
	 * @param canteen The Canteen object specified in the order window
	 * @param discount The designated discount in the order window
	 */
	public void createNewOrderindow(DateTime startDate, DateTime endDate, Company company,
			Canteen canteen, double discount) {
		orderWindowDAO.saveOrderWindow(new OrderWindow(startDate, endDate, company, canteen,
				discount));
	}

	/**
	 * Creates a new order window period with start date, duration and the discount for the specific company and
	 * canteen
	 * 
	 * @param startDate The start date of the order window
	 * @param duration The duration of the order window
	 * @param company The Company object specified in the order window
	 * @param canteen The Canteen object specified in the order window
	 * @param discount The designated discount in the order window
	 */
	public void createNewOrderWindow(DateTime startDate, Duration duration, Company company,
			Canteen canteen, double discount) {
		orderWindowDAO.saveOrderWindow(new OrderWindow(startDate, duration, company, canteen,
				discount));
	}
	
	/**
	 * Creates a new order window period indicating the number of weeks, 
	 * with start and end date and the discount for the specific company and
	 * canteen 
	 * 
	 * @param startDate The start date of the order window
	 * @param endDate The end date of the order window
	 * @param company The Company object specified in the order window
	 * @param canteen The Canteen object specified in the order window
	 * @param discount The designated discount in the order window
	 * @param numberOfWeeks The number of weeks that this order window will be running
	 */
	public void createNewOrderindow(DateTime startDate, DateTime endDate, Company company,
			Canteen canteen, double discount, int numberOfWeeks) {
		if (numberOfWeeks > 0) {
			ArrayList<DateTime> startTimeList = new ArrayList<DateTime>();
			ArrayList<DateTime> endTimeList = new ArrayList<DateTime>();
			startTimeList.add(startDate);
			endTimeList.add(endDate);
			for (int i = 1; i < numberOfWeeks; i++) {
				DateTime tempStartDateTime = startDate.plusWeeks(1);
				startDate = tempStartDateTime;
				DateTime tempEndDateTime = endDate.plusWeeks(1);
				endDate = tempEndDateTime;
				startTimeList.add(tempStartDateTime);
				endTimeList.add(tempEndDateTime);
			}

			for (int i = 0; i < startTimeList.size(); i++) {
				orderWindowDAO.saveOrderWindow(new OrderWindow(startTimeList.get(i), endTimeList
						.get(i), company, canteen, discount));
			}

		} else {
			orderWindowDAO.saveOrderWindow(new OrderWindow(startDate, endDate, company, canteen,
					discount));
		}
	}
	
	public void createNewOrderindow(DateTime startDate, DateTime endDate, Company company,
			Canteen canteen, double discount, double discountValue, int numberOfWeeks) {
		if (numberOfWeeks > 0) {
			ArrayList<DateTime> startTimeList = new ArrayList<DateTime>();
			ArrayList<DateTime> endTimeList = new ArrayList<DateTime>();
			startTimeList.add(startDate);
			endTimeList.add(endDate);
			for (int i = 1; i < numberOfWeeks; i++) {
				DateTime tempStartDateTime = startDate.plusWeeks(1);
				startDate = tempStartDateTime;
				DateTime tempEndDateTime = endDate.plusWeeks(1);
				endDate = tempEndDateTime;
				startTimeList.add(tempStartDateTime);
				endTimeList.add(tempEndDateTime);
			}

			for (int i = 0; i < startTimeList.size(); i++) {
				orderWindowDAO.saveOrderWindow(new OrderWindow(startTimeList.get(i), endTimeList
						.get(i), company, canteen, discount, discountValue));
			}

		} else {
			orderWindowDAO.saveOrderWindow(new OrderWindow(startDate, endDate, company, canteen,
					discount, discountValue));
		}
	}
	
	
	/**
	 * Retrieve all the OrderWindows
	 * 
	 * @return An ArrayList of all OrderWindows regardless of status
	 */
	public ArrayList<OrderWindow> getAllOrderWindows() {
		return orderWindowDAO.getAllOrderWindows();
	}
	
	/**
	 * Creates a new order window period indicating the number of weeks,
	 * with start date, duration and the discount for the specific company and
	 * canteen
	 * 
	 * @param startDate The start date of the order window
	 * @param duration The duration of the order window
	 * @param company The Company object specified in the order window
	 * @param canteen The Canteen object specified in the order window
	 * @param discount The designated discount in the order window
	 * @param numberOfWeeks The number of weeks that this order window will be running
	 */
	public void createNewOrderWindow(DateTime startDate, Duration duration, Company company,
			Canteen canteen, double discount, int numberOfWeeks) {
		if (numberOfWeeks > 0) {
			ArrayList<DateTime> dateTimeList = new ArrayList<DateTime>();
			dateTimeList.add(startDate);
			for (int i = 1; i < numberOfWeeks; i++) {
				DateTime tempDateTime = startDate.plusWeeks(1);
				startDate = tempDateTime;
				dateTimeList.add(tempDateTime);
			}

			for (DateTime dateTimeAdd : dateTimeList) {
				orderWindowDAO.saveOrderWindow(new OrderWindow(dateTimeAdd, duration, company,
						canteen, discount));
			}

		} else {
			orderWindowDAO.saveOrderWindow(new OrderWindow(startDate, duration, company, canteen,
					discount));
		}
	}

	/**
	 * Retrieves the OrderWindow based on the provided ID
	 * 
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
