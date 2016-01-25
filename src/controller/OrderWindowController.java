package controller;

import java.util.ArrayList;

import model.Canteen;
import model.Company;
import model.OrderWindow;
import model.PriceModifier;

import org.joda.time.DateTime;

import value.StringValues;
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
			Canteen canteen, int numberOfWeeks, String remarks, double discountPercentage,
			double discountAbsolute) {
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
						.get(i), company, canteen, discountPercentage, discountAbsolute, remarks,
						null));
			}

		} else {
			orderWindowDAO.saveOrderWindow(new OrderWindow(startDate, endDate, company, canteen,
					discountPercentage, discountAbsolute, remarks, null));
		}
	}

	public void createNewOrderWindow2(DateTime startDate, DateTime endDate, Company company,
			Canteen canteen, int numberOfWeeks, String remarks, double discountAbsolute) {

		for (int i = 0; i < numberOfWeeks; i++) {
			OrderWindow window = new OrderWindow(startDate.plusWeeks(i), endDate.plusWeeks(i),
					company, canteen, remarks, null);

			ArrayList<PriceModifier> list = new ArrayList<PriceModifier>();
			PriceModifier discountAbsoluteModifier = new PriceModifier("Discount",
					StringValues.PRICEMODIFIER_ABSOLUTE, discountAbsolute * -1, window);
			list.add(discountAbsoluteModifier);
			window.setPriceModifierList(list);
			orderWindowDAO.saveOrderWindow(window);
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

	public ArrayList<OrderWindow> getAllClosedWindows() {
		return orderWindowDAO.getAllClosedWindows();
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
