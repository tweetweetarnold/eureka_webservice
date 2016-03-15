package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Canteen;
import model.Company;
import model.OrderPeriod;
import value.StringValues;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import connection.MyConnection;

/**
 * Performs the function of Data Access Object for the OrderPeriod model
 * 
 * @author SMU Team Eureka
 */
public class OrderPeriodDAO {

	/**
	 * Creates a default constructor for OrderPeriodDAO
	 */
	public OrderPeriodDAO() {

	}

	/**
	 * Removes the designated OrderPeriod object from the Database
	 * 
	 * @param w The OrderPeriod object to be removed
	 */
	public void deleteOrderPeriod(OrderPeriod w) {
		MyConnection.delete(w);
	}

	public ArrayList<OrderPeriod> getAllClosedPeriods() {
		ArrayList<OrderPeriod> returnList = new ArrayList<OrderPeriod>();
		Date currentDatetime = new Date();

		DetachedCriteria dc = DetachedCriteria.forClass(OrderPeriod.class);
		dc.add(Restrictions.le("endDateFormatted", currentDatetime));
		dc.addOrder(Order.asc("startDateFormatted"));
		dc.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

		List<Object> l = MyConnection.queryWithCriteria(dc);

		for (Object o : l) {
			returnList.add((OrderPeriod) o);
		}
		return returnList;
	}

	public ArrayList<OrderPeriod> getAllNonDeletedOrderPeriods() {
		ArrayList<OrderPeriod> returnList = new ArrayList<OrderPeriod>();

		DetachedCriteria dc = DetachedCriteria.forClass(OrderPeriod.class);
		dc.add(Restrictions.ne("status", StringValues.ARCHIVED));
		dc.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

		List<Object> l = MyConnection.queryWithCriteria(dc);

		for (Object o : l) {
			returnList.add((OrderPeriod) o);
		}
		return returnList;
	}

	/**
	 * Get all OrderPeriods with status "Opened"
	 * 
	 * @return Returns an ArrayList of OrderPeriods with status "Opened"
	 */
	public ArrayList<OrderPeriod> getAllOpenedPeriods() {
		ArrayList<OrderPeriod> returnList = new ArrayList<OrderPeriod>();
		Date currentDatetime = new Date();

		DetachedCriteria dc = DetachedCriteria.forClass(OrderPeriod.class);
		dc.add(Restrictions.ge("endDateFormatted", currentDatetime))
				.add(Restrictions.le("startDateFormatted", currentDatetime))
				.add(Restrictions.ne("status", StringValues.ARCHIVED));
		dc.addOrder(Order.asc("startDateFormatted"));
		dc.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

		List<Object> l = MyConnection.queryWithCriteria(dc);

		for (Object o : l) {
			returnList.add((OrderPeriod) o);
		}
		return returnList;
	}

	/**
	 * Get all OrderPeriods with status "Opened" for the given Company
	 * 
	 * @param company The company whose "Opened" OrderPeriods are to be retrieved
	 * @return Returns an ArrayList of OrderPeriods with status "Opened" for the given Company
	 */
	public ArrayList<OrderPeriod> getAllOpenedPeriodsForCompany(Company company) {
		ArrayList<OrderPeriod> returnList = new ArrayList<OrderPeriod>();
		Date currentDatetime = new Date();

		DetachedCriteria dc = DetachedCriteria.forClass(OrderPeriod.class);
		dc.add(Restrictions.ge("endDateFormatted", currentDatetime));
		dc.add(Restrictions.le("startDateFormatted", currentDatetime));
		dc.add(Restrictions.eq("company", company));
		dc.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

		List<Object> l = MyConnection.queryWithCriteria(dc);

		for (Object o : l) {
			returnList.add((OrderPeriod) o);
		}
		return returnList;
	}

	/**
	 * Get all OrderPeriods
	 * 
	 * @return Returns an ArrayList of all OrderPeriods regardless of status
	 */
	public ArrayList<OrderPeriod> getAllOrderPeriods() {
		ArrayList<OrderPeriod> returnList = new ArrayList<OrderPeriod>();

		DetachedCriteria dc = DetachedCriteria.forClass(OrderPeriod.class);
		dc.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

		List<Object> l = MyConnection.queryWithCriteria(dc);

		for (Object o : l) {
			returnList.add((OrderPeriod) o);
		}
		return returnList;
	}

	public ArrayList<OrderPeriod> getAllOrderPeriodsUnderCompany(Company company) {
		ArrayList<OrderPeriod> returnList = new ArrayList<OrderPeriod>();
		DetachedCriteria dc = DetachedCriteria.forClass(OrderPeriod.class);
		dc.add(Restrictions.eq("company", company));
		dc.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

		List<Object> l = MyConnection.queryWithCriteria(dc);

		for (Object o : l) {
			returnList.add((OrderPeriod) o);
		}
		return returnList;
	}

	public ArrayList<OrderPeriod> getAllPeriodsForCanteen(Canteen canteen) {
		ArrayList<OrderPeriod> returnList = new ArrayList<OrderPeriod>();

		DetachedCriteria dc = DetachedCriteria.forClass(OrderPeriod.class);
		dc.add(Restrictions.eq("canteen", canteen));
		dc.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

		List<Object> l = MyConnection.queryWithCriteria(dc);

		for (Object o : l) {
			returnList.add((OrderPeriod) o);
		}
		return returnList;
	}

	/**
	 * Retrieve the OrderPeriod based on the provided ID
	 * 
	 * @param orderPeriodId The ID used for retrieving the OrderPeriod
	 * @return The OrderPeriod object that has the provided ID
	 */
	public OrderPeriod getOrderPeriod(int orderPeriodId) {
		return (OrderPeriod) MyConnection.get(OrderPeriod.class, orderPeriodId);
	}

	/**
	 * Adds a new OrderPeriod object to the Database
	 * 
	 * @param w The OrderPeriod object to be added in
	 */
	public void saveOrderPeriod(OrderPeriod w) {
		MyConnection.save(w);
	}

	/**
	 * Updates the designated OrderPeriod object in the Database
	 * 
	 * @param w The OrderPeriod object to be updated
	 */
	public int updateOrderPeriod(OrderPeriod w) {
		MyConnection.update(w);
		int periodID = w.getPeriodId();
		return periodID;
	}

}
