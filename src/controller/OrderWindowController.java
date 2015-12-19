package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Canteen;
import model.Company;
import model.OrderWindow;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;
import org.joda.time.Duration;

import connection.MyConnection;
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

	// Check for any active window under given company
	/**
	 * Check for any active window under a given company
	 * 
	 * @param company The Company object to be assessed on
	 * @return true If there is an active window,otherwise false
	 */
//	public boolean checkForActiveWindow(Company company) {
//		DetachedCriteria dc = DetachedCriteria.forClass(OrderWindow.class);
//		dc.add(Restrictions.eq("company", 1));
//		// dc.add(Restrictions.gt("endDate", new Date()));
//		dc.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
//
//		List<Object> l = MyConnection.queryWithCriteria(dc);
//
//		return true;
//	}
	
	public List<OrderWindow> checkForActiveWindow(Company company) {
		OrderWindowDAO orderWindowDAO = new OrderWindowDAO();
		DateTime currentTime = new DateTime(); 
		System.out.println(currentTime);
		List<Object> orderWindowList = MyConnection.getWindowIfActive(company,currentTime.toDate());
		System.out.println(orderWindowList.size()+ "hi");
		List<OrderWindow> orderList = new ArrayList<OrderWindow>();
		for(Object o: orderWindowList){
			OrderWindow tempOrderWindow = (OrderWindow)o;
			orderList.add(tempOrderWindow);
		}
		return orderList;
	}
}
