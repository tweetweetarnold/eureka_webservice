package dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Company;
import model.OrderWindow;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import connection.MyConnection;

public class OrderWindowDAO {

	public OrderWindow getOrderWindow(int orderWindowId) {
		return (OrderWindow) MyConnection.get(OrderWindow.class, orderWindowId);
	}

	// Save new OrderWindow to the DB
	public void saveOrderWindow(OrderWindow w) {
		MyConnection.save(w);
	}

	// Update existing Company in the DB
	public void updateOrderWindow(OrderWindow w) {
		MyConnection.update(w);
	}

	// Delete Company from the DB
	public void deleteOrderWindow(OrderWindow w) {
		MyConnection.delete(w);
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
		dc.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

		List<Object> l = MyConnection.queryWithCriteria(dc);

		for (Object o : l) {
			returnList.add((OrderWindow) o);
		}
		return returnList;
	}
}
