package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Company;
import model.OrderWindow;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import connection.MyConnection;

/**
 * Performs the function of Data Access Object for the OrderWindow model
 * 
 * @author SMU Team Eureka
 */
public class OrderWindowDAO {

	/**
	 * Creates a default constructor for OrderWindowDAO
	 */
	public OrderWindowDAO() {

	}

	/**
	 * Removes the designated OrderWindow object from the Database
	 * 
	 * @param w The OrderWindow object to be removed
	 */
	public void deleteOrderWindow(OrderWindow w) {
		MyConnection.delete(w);
	}

	/**
	 * Get all OrderWindows with status "Opened"
	 * 
	 * @return Returns an ArrayList of OrderWindows with status "Opened"
	 */
	public ArrayList<OrderWindow> getAllOpenedWindows() {
		ArrayList<OrderWindow> returnList = new ArrayList<OrderWindow>();
		Date currentDatetime = new Date();

		DetachedCriteria dc = DetachedCriteria.forClass(OrderWindow.class);
		dc.add(Restrictions.ge("endDateFormatted", currentDatetime)).add(
				Restrictions.le("startDateFormatted", currentDatetime));
		dc.addOrder(Order.asc("endDateFormatted"));
		dc.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

		List<Object> l = MyConnection.queryWithCriteria(dc);

		for (Object o : l) {
			returnList.add((OrderWindow) o);
		}
		return returnList;
	}

	/**
	 * Get all OrderWindows with status "Opened" for the given Company
	 * 
	 * @param company The company whose "Opened" OrderWindows are to be retrieved
	 * @return Returns an ArrayList of OrderWindows with status "Opened" for the given Company
	 */
	public ArrayList<OrderWindow> getAllOpenedWindowsForCompany(Company company) {
		ArrayList<OrderWindow> returnList = new ArrayList<OrderWindow>();
		Date currentDatetime = new Date();

		DetachedCriteria dc = DetachedCriteria.forClass(OrderWindow.class);
		dc.add(Restrictions.ge("endDateFormatted", currentDatetime));
		dc.add(Restrictions.le("startDateFormatted", currentDatetime));
		dc.add(Restrictions.eq("company", company));
		dc.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

		List<Object> l = MyConnection.queryWithCriteria(dc);

		for (Object o : l) {
			returnList.add((OrderWindow) o);
		}
		return returnList;
	}

	/**
	 * Get all OrderWindows
	 * 
	 * @return Returns an ArrayList of all OrderWindows regardless of status
	 */
	public ArrayList<OrderWindow> getAllOrderWindows() {
		ArrayList<OrderWindow> returnList = new ArrayList<OrderWindow>();

		DetachedCriteria dc = DetachedCriteria.forClass(OrderWindow.class);
		dc.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

		List<Object> l = MyConnection.queryWithCriteria(dc);

		for (Object o : l) {
			returnList.add((OrderWindow) o);
		}
		return returnList;
	}

	public ArrayList<OrderWindow> getAllOrderWindowsUnderCompany(Company company) {
		ArrayList<OrderWindow> returnList = new ArrayList<OrderWindow>();
		DetachedCriteria dc = DetachedCriteria.forClass(OrderWindow.class);
		dc.add(Restrictions.eq("company", company));
		dc.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

		List<Object> l = MyConnection.queryWithCriteria(dc);

		for (Object o : l) {
			returnList.add((OrderWindow) o);
		}
		return returnList;
	}

	/**
	 * Retrieve the OrderWindow based on the provided ID
	 * 
	 * @param orderWindowId The ID used for retrieving the OrderWindow
	 * @return The OrderWindow object that has the provided ID
	 */
	public OrderWindow getOrderWindow(int orderWindowId) {
		return (OrderWindow) MyConnection.get(OrderWindow.class, orderWindowId);
	}

	/**
	 * Adds a new OrderWindow object to the Database
	 * 
	 * @param w The OrderWindow object to be added in
	 */
	public void saveOrderWindow(OrderWindow w) {
		MyConnection.save(w);
	}

	/**
	 * Updates the designated OrderWindow object in the Database
	 * 
	 * @param w The OrderWindow object to be updated
	 */
	public void updateOrderWindow(OrderWindow w) {
		MyConnection.update(w);
	}

}
