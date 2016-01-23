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

	public boolean checkForOrderWindowAvailability(DateTime startTime, DateTime endTime,
			Company coy, int weeks) throws Exception {
		ArrayList<OrderWindow> allOrderWindows = orderWindowDAO.getAllOrderWindowsUnderCompany(coy);
		// ArrayList<OrderWindow> occupiedSlots = new ArrayList<OrderWindow>();
		System.out.println("startDate: " + startTime);
		System.out.println("endTime: " + endTime);
		ArrayList<DateTime> startTimes = new ArrayList<DateTime>();
		ArrayList<DateTime> endTimes = new ArrayList<DateTime>();

		if (weeks > 1) {
			startTimes.add(startTime);
			endTimes.add(endTime);
			for (int i = 1; i < weeks; i++) {
				DateTime tempDateTime = startTime.plusWeeks(1);
				DateTime tempEndTime = endTime.plusWeeks(1);
				startTime = tempDateTime;
				endTime = tempEndTime;
				startTimes.add(tempDateTime);
				endTimes.add(tempEndTime);
			}
			for (int i = 0; i < startTimes.size(); i++) {
				DateTime startTimeTemp = startTimes.get(i);
				DateTime endTimeTemp = endTimes.get(i);

				if (startTimeTemp.isBefore(endTimeTemp)) {
					for (OrderWindow w : allOrderWindows) {
						DateTime wStart = w.getStartDate();
						DateTime wEnd = w.getEndDate();
						if (startTimeTemp.isEqual(wStart)) {
							return false;
						}
						if ((startTimeTemp.isAfter(wStart))) {
							System.out.println("***Alog 1*****");
							System.out.println((startTimeTemp.isBefore(endTimeTemp)));
							if (startTimeTemp.isBefore(wEnd)) {
								if (endTimeTemp.isBefore(wEnd)) {
									return false;
								} else if (endTimeTemp.isAfter(wEnd)) {
									return false;
								}
							}
						}

						if ((startTimeTemp.isBefore(wStart))) {
							System.out.println("***Alog 2*****");

							if (endTimeTemp.isAfter(wStart)) {
								if (endTimeTemp.isBefore(wEnd)) {
									return false;
								} else if (endTimeTemp.isAfter(wEnd)) {
									return false;
								}
							}
						}
					}
				} else {
					throw new Exception("Invalid start time and end time");
				}
			}
		} else {

			if (startTime.isBefore(endTime)) {
				for (OrderWindow w : allOrderWindows) {
					DateTime wStart = w.getStartDate();
					DateTime wEnd = w.getEndDate();
					if (startTime.isEqual(wStart)) {
						return false;
					}
					if ((startTime.isAfter(wStart))) {
						System.out.println("***Alog 1*****");
						System.out.println((startTime.isBefore(endTime)));
						if (startTime.isBefore(wEnd)) {
							if (endTime.isBefore(wEnd)) {
								return false;
							} else if (endTime.isAfter(wEnd)) {
								return false;
							}
						}
					}

					if ((startTime.isBefore(wStart))) {
						System.out.println("***Alog 2*****");

						if (endTime.isAfter(wStart)) {
							if (endTime.isBefore(wEnd)) {
								return false;
							} else if (endTime.isAfter(wEnd)) {
								return false;
							}
						}
					}
				}
			} else {
				throw new Exception("Invalid start time and end time");
			}
		}
		return true;
	}

	public ArrayList<OrderWindow> checkOrderWindowAvailability(DateTime startTime,
			DateTime endTime, Company coy) throws Exception {
		ArrayList<OrderWindow> allOrderWindows = orderWindowDAO.getAllOrderWindowsUnderCompany(coy);
		ArrayList<OrderWindow> occupiedSlots = new ArrayList<OrderWindow>();
		System.out.println("startDate: " + startTime);
		System.out.println("endTime: " + endTime);

		if (startTime.isBefore(endTime)) {
			for (OrderWindow w : allOrderWindows) {
				DateTime wStart = w.getStartDate();
				DateTime wEnd = w.getEndDate();

				if ((startTime.isAfter(wStart))) {
					System.out.println("***Alog 1*****");
					System.out.println((startTime.isBefore(endTime)));
					if (startTime.isBefore(wEnd)) {
						if (endTime.isBefore(wEnd)) {
							occupiedSlots.add(w);
						} else if (endTime.isAfter(wEnd)) {
							occupiedSlots.add(w);
						}
					}
				}

				if ((startTime.isBefore(wStart))) {
					System.out.println("***Alog 2*****");

					if (endTime.isAfter(wStart)) {
						if (endTime.isBefore(wEnd)) {
							occupiedSlots.add(w);
						} else if (endTime.isAfter(wEnd)) {
							occupiedSlots.add(w);
						}
					}
				}
			}
		} else {
			throw new Exception("Invalid start time and end time");
		}

		return occupiedSlots;
	}

	public void createNewOrderWindow(DateTime startDate, DateTime endDate, Company company,
			Canteen canteen, double discount, double discountValue, int numberOfWeeks,
			String remarks) {
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
						.get(i), company, canteen, discount, discountValue, remarks, null));
			}

		} else {
			orderWindowDAO.saveOrderWindow(new OrderWindow(startDate, endDate, company, canteen,
					discount, discountValue, remarks, null));
		}
	}

	/**
	 * Creates a new order window period indicating the number of weeks, with start and end date and
	 * the discount for the specific company and canteen
	 * 
	 * @param startDate The start date of the order window
	 * @param endDate The end date of the order window
	 * @param company The Company object specified in the order window
	 * @param canteen The Canteen object specified in the order window
	 * @param discount The designated discount in the order window
	 * @param numberOfWeeks The number of weeks that this order window will be running
	 */
	public void createNewOrderWindow(DateTime startDate, DateTime endDate, Company company,
			Canteen canteen, double discount, int numberOfWeeks, String remarks) {

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
						.get(i), company, canteen, discount, remarks, null));
			}

		} else {
			orderWindowDAO.saveOrderWindow(new OrderWindow(startDate, endDate, company, canteen,
					discount, remarks, null));
		}
	}

	/**
	 * Creates a new order window period indicating the number of weeks, with start date, duration
	 * and the discount for the specific company and canteen
	 * 
	 * @param startDate The start date of the order window
	 * @param duration The duration of the order window
	 * @param company The Company object specified in the order window
	 * @param canteen The Canteen object specified in the order window
	 * @param discount The designated discount in the order window
	 * @param numberOfWeeks The number of weeks that this order window will be running
	 */
	public void createNewOrderWindow(DateTime startDate, Duration duration, Company company,
			Canteen canteen, double discount, int numberOfWeeks, String remarks) {

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
						canteen, discount, remarks, null));
			}

		} else {
			orderWindowDAO.saveOrderWindow(new OrderWindow(startDate, duration, company, canteen,
					discount, remarks, null));
		}
	}

	/**
	 * Get all OrderWindows with status "Opened"
	 * 
	 * @return Returns an ArrayList of OrderWindows with status "Opened"
	 */
	public ArrayList<OrderWindow> getAllOpenedWindows() {
		return orderWindowDAO.getAllOpenedWindows();
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
	 * Retrieve all the OrderWindows
	 * 
	 * @return An ArrayList of all OrderWindows regardless of status
	 */
	public ArrayList<OrderWindow> getAllOrderWindows() {
		return orderWindowDAO.getAllOrderWindows();
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

}
