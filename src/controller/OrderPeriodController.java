package controller;

import java.util.ArrayList;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import dao.OrderPeriodDAO;
import model.Canteen;
import model.Company;
import model.OrderPeriod;
import model.PriceModifier;
import services.QuartzService;
import value.StringValues;

/**
 * Process the business logic of the order period for the application
 * 
 * @author SMU Team Eureka
 * 
 */
public class OrderPeriodController {
	OrderPeriodDAO orderPeriodDAO = new OrderPeriodDAO();

	/**
	 * Creates a default constructor for the OrderPeriodController
	 */
	public OrderPeriodController() {
	}

	/**
	 * Checks if there is any OrderPeriods between the specified start time and end time for this
	 * Company.
	 * 
	 * @param startTime The starting DateTime of the checking period
	 * @param endTime The ending DateTime of the checking period
	 * @param company The Company for which this check is under
	 * @param weeks The number of weeks to check this range of period
	 * @return Returns true if there are no OrderPeriods between the specified startTime and endTime
	 *         for this Company. Otherwise, returns false;
	 */
	public boolean checkForOrderPeriodAvailability(DateTime startTime, DateTime endTime,
			Company company, int weeks) throws Exception {

		ArrayList<OrderPeriod> allOrderPeriods = orderPeriodDAO
				.getAllOrderPeriodsUnderCompany(company);

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
					for (OrderPeriod w : allOrderPeriods) {
						System.out.println(w.getCanteen().getName());
						if (w.getStatus().equals(StringValues.ACTIVE)) {
							DateTime wStart = w.getStartDate();
							DateTime wEnd = w.getEndDate();

							if (startTimeTemp.isEqual(wStart)) {
								return false;
							}
							if ((startTimeTemp.isAfter(wStart))) {
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

								if (endTimeTemp.isAfter(wStart)) {
									if (endTimeTemp.isBefore(wEnd)) {
										return false;
									} else if (endTimeTemp.isAfter(wEnd)) {
										return false;
									}
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

				for (OrderPeriod w : allOrderPeriods) {

					if (w.getStatus().equals(StringValues.ACTIVE)) {

						DateTime wStart = w.getStartDate();
						System.out.println(wStart + "  vs  " + startTime);
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
				}
			} else {

				throw new Exception("Invalid start time and end time");
			}
		}
		return true;
	}
	/**
	 * Returns a list of orderPeriods which clash with a specified startTime and endTime
	 * @param startTime the starting time of the new period 
	 * @param endTime the ending time of the new period
	 * @param coy the company to check the periods
	 * @return list of orderPeriods which clash with a specified startTime and endTime
	 * @throws Exception
	 */
	public ArrayList<OrderPeriod> checkOrderPeriodAvailability(DateTime startTime, DateTime endTime,
			Company coy) throws Exception {

		ArrayList<OrderPeriod> allOrderPeriods = orderPeriodDAO.getAllOrderPeriodsUnderCompany(coy);
		ArrayList<OrderPeriod> occupiedSlots = new ArrayList<OrderPeriod>();

		System.out.println("startDate: " + startTime);
		System.out.println("endTime: " + endTime);

		if (startTime.isBefore(endTime)) {
			for (OrderPeriod w : allOrderPeriods) {
				if (w.getStatus().equals(StringValues.ORDERPERIOD_ACTIVE)) {
					DateTime wStart = w.getStartDate();
					DateTime wEnd = w.getEndDate();

					if ((startTime.isAfter(wStart))) {
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

						if (endTime.isAfter(wStart)) {
							if (endTime.isBefore(wEnd)) {
								occupiedSlots.add(w);
							} else if (endTime.isAfter(wEnd)) {
								occupiedSlots.add(w);
							}
						}
					}
				}
			}
		} else {
			throw new Exception("Invalid start time and end time");
		}

		return occupiedSlots;
	}
	/**
	 * Creates a new order period 
	 * @param startDate the startdate of the new order period
	 * @param endDate the enddate of the new order period
	 * @param company the company to create the order period for
	 * @param canteen the canteen that the orderperiod serves
	 * @param numberOfWeeks the number of weeks to repeat for
	 * @param remarks 
	 * @param discountAbsolute the amount of discount to apply to this order period
	 * @throws Exception if there are any clashes with this order period throw exception
	 */
	public void createNewOrderPeriod(DateTime startDate, DateTime endDate, Company company,
			Canteen canteen, int numberOfWeeks, String remarks, double discountAbsolute)
			throws Exception {

		QuartzService quartzService = new QuartzService();

		for (int i = 0; i < numberOfWeeks; i++) {
			// To get the startTime and endTime for the particular week
			DateTime newStartTime = startDate.plusWeeks(i);
			DateTime newEndTime = endDate.plusWeeks(i);
			Date startDateForQuartz = newStartTime.toDate();

			OrderPeriod period = new OrderPeriod(newStartTime, newEndTime, company, canteen,
					remarks, null);

			// To add the discount amount to this order period
			ArrayList<PriceModifier> list = new ArrayList<PriceModifier>();
			PriceModifier discountAbsoluteModifier = new PriceModifier("Discount",
					StringValues.PRICEMODIFIER_ABSOLUTE, discountAbsolute, period);

			list.add(discountAbsoluteModifier);

			period.setPriceModifierList(list);

			orderPeriodDAO.saveOrderPeriod(period);
			int orderPeriodID = orderPeriodDAO.updateOrderPeriod(period);
			System.out.println("Order Period " + orderPeriodID + " has been created.");

			// quartz stuff
			quartzService.setupNewJobAndTrigger("" + orderPeriodID, startDateForQuartz);
		}

	}

	/**
	 * Delete the OrderPeriod with the given Id
	 * 
	 * @param orderPeriodId The Id of the OrderPeriod to be deleted
	 */
	public void deleteOrderPeriod(int orderPeriodId) throws Exception {

		FoodOrderController foodOrderCtrl = new FoodOrderController();
		OrderPeriod orderPeriodToArchive = getOrderPeriod(orderPeriodId);

		if (foodOrderCtrl.getAllFoodOrderOfOrderPeriodGroupedByStall(orderPeriodToArchive)
				.size() == 0) {
			orderPeriodToArchive.setStatus(StringValues.ARCHIVED);
			orderPeriodDAO.updateOrderPeriod(orderPeriodToArchive);
		} else {
			throw new Exception("A Food Order has been made. Order Period cannot be deleted.");
		}
	}
	/**
	 * Edits an existing order period
	 * @param orderPeriodId the id of the order period to edit
	 * @param newStartDate the new startdate of the orderperiod
	 * @param newEndDate the new enddate of the orderperiod
	 * @throws Exception
	 */
	public void editOrderPeriod(int orderPeriodId, DateTime newStartDate, DateTime newEndDate)
			throws Exception {
		OrderPeriodDAO orderPeriodDAO = new OrderPeriodDAO();
		OrderPeriod orderPeriodToEdit = getOrderPeriod(orderPeriodId);
		DateTime dateEnd = orderPeriodToEdit.getEndDate();
		DateTime dateStart = orderPeriodToEdit.getStartDate();
		DateTimeZone dateTimeZone = DateTimeZone.forID("Asia/Singapore");
		DateTime currentTime = DateTime.now(dateTimeZone);

		// to check for clashing Order Period
		ArrayList<OrderPeriod> orderPeriodList = checkOrderPeriodAvailability(newStartDate,
				newEndDate, orderPeriodToEdit.getCompany());

		if (dateStart.isAfter(currentTime)) {
			// edit OrderPeriod orderPeriodToEdit
			if (newStartDate.isBefore(currentTime)) {
				throw new Exception("Cannot set start date to before current time");
				// check if new order period clash with other order periods
			} else {

				// if list is empty no clash
				if (orderPeriodList.isEmpty()) {
					orderPeriodToEdit.setStartDate(newStartDate);
					orderPeriodToEdit.setEndDate(newEndDate);
					orderPeriodDAO.updateOrderPeriod(orderPeriodToEdit);
					// if list has one order period and it is the same order
					// period go ahead and edit it
				} else if ((orderPeriodList.size() == 1)
						&& (orderPeriodList.get(0).getPeriodId() == orderPeriodId)) {
					orderPeriodToEdit.setStartDate(newStartDate);
					orderPeriodToEdit.setEndDate(newEndDate);
					orderPeriodDAO.updateOrderPeriod(orderPeriodToEdit);
					// if both conditions not satisfied then it clashes with one
					// or more periods
				} else {
					throw new Exception("New order period dates clash with another order period");
				}
			}
		} else if (dateStart.isBefore(currentTime) && dateEnd.isAfter(currentTime)) {
			// can only edit endDate
			// if list is empty no clash
			if (orderPeriodList.isEmpty()) {
				orderPeriodToEdit.setEndDate(newEndDate);
				orderPeriodDAO.updateOrderPeriod(orderPeriodToEdit);

				// if list has one order period and it is the same order period
				// go ahead and edit it
			} else if ((orderPeriodList.size() == 1)
					&& (orderPeriodList.get(0).getPeriodId() == orderPeriodId)) {
				orderPeriodToEdit.setEndDate(newEndDate);
				orderPeriodDAO.updateOrderPeriod(orderPeriodToEdit);

			}
			// if both conditions not satisfied then it clashes with one or more
			// periods
			else {
				throw new Exception("Dates clash with an existing Order Period.");
			}

		} else {
			throw new Exception("Order Period Cannot be edited");
		}

	}

	/**
	 * Retrieves all OrderPeriods which are before now
	 * 
	 * @return A list of OrderPeriods which are before now
	 */
	public ArrayList<OrderPeriod> getAllClosedPeriods() {
		return orderPeriodDAO.getAllClosedPeriods();
	}

	/**
	 * Retrieves all OrderPeriods that are not deleted
	 * 
	 * @return A list of OrderPeriods that are not deleted
	 */
	public ArrayList<OrderPeriod> getAllNonDeletedOrderPeriods() {
		return orderPeriodDAO.getAllNonDeletedOrderPeriods();
	}

	/**
	 * Get all OrderPeriods with status "Opened"
	 * 
	 * @return Returns an ArrayList of OrderPeriods with status "Opened"
	 */
	public ArrayList<OrderPeriod> getAllOpenedPeriods() {
		return orderPeriodDAO.getAllOpenedPeriods();
	}

	/**
	 * Get all OrderPeriods with status "Opened" for the given Company
	 * 
	 * @param company The company whose "Opened" OrderPeriods are to be retrieved
	 * @return Returns an ArrayList of OrderPeriods with status "Opened" for the given Company
	 */
	public ArrayList<OrderPeriod> getAllOpenedPeriodsForCompany(Company company) {
		return orderPeriodDAO.getAllOpenedPeriodsForCompany(company);
	}

	/**
	 * Retrieve all the OrderPeriods
	 * 
	 * @return An ArrayList of all OrderPeriods regardless of status
	 */
	public ArrayList<OrderPeriod> getAllOrderPeriods() {
		return orderPeriodDAO.getAllOrderPeriods();
	}

	/**
	 * Retrieves the OrderPeriod based on the provided ID
	 * 
	 * @param orderPeriodId The ID of the OrderPeriod
	 * @return The OrderPeriod object that has the provided ID
	 */
	public OrderPeriod getOrderPeriod(Integer orderPeriodId) {
		return orderPeriodDAO.getOrderPeriod(orderPeriodId);
	}

}
