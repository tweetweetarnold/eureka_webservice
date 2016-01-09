package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import model.Employee;
import model.FoodOrder;
import model.OrderWindow;
import connection.MyConnection;

/**
 * Performs the function of Data Access Object 
 * for the FoodOrder model
 * 
 * @author SMU Team Eureka
 */
public class FoodOrderDAO {

	/**
	 * Creates a default constructor for FoodOrderDAO
	 */
	public FoodOrderDAO() {
		
	}
	
	/**
	 * Retrieve the FoodOrder based on the provided ID
	 * 
	 * @param foodOrderId The ID used for retrieving the FoodOrder
	 * @return The FoodOrder object that has the provided ID
	 */
	public FoodOrder getFoodOrder(int foodOrderId) {
		return (FoodOrder) MyConnection.get(FoodOrder.class, foodOrderId);
	}

	/**
	 * Adds a new FoodOrder object to the Database
	 * 
	 * @param f The FoodOrder object to be added in
	 */
	public void saveFoodOrder(FoodOrder f) {
		MyConnection.save(f);
	}

	/**
	 * Updates the designated FoodOrder object in the Database
	 * 
	 * @param f The FoodOrder object to be updated
	 */
	public void updateFoodOrder(FoodOrder f) {
		MyConnection.update(f);
	}

	/**
	 * Removes the designated FoodOrder object from the Database
	 * 
	 * @param f The FoodOrder object to be removed
	 */
	public void deleteFoodOrder(FoodOrder f) {
		MyConnection.delete(f);
	}

	/**
	 * Retrieves the FoodOrder set of an Employee
	 * 
	 * @param employee The designated Employee to retrieve the FoodOrder set
	 * @return The List of FoodOrder set of the designated Employee
	 */
	public List<FoodOrder> getFoodOrderSet(Employee employee) {
		List<FoodOrder> returnList = new ArrayList<>();

		List<Object> hiberList = MyConnection.getFoodOrderList(employee);
		if (hiberList.size() != 0) {
			for (Object o : hiberList) {
				returnList.add((FoodOrder) o);
			}
		}
		return returnList;
	}

	// Retrive FoodOrders from the DB between two datetimes with format
	// "yyyy-MM-dd hh:mm:ss"
	/**
	 * Retrieve FoodOrders from the database that falls between two provided Dates (in "yyyy-MM-dd hh:mm:ss")
	 * 
	 * @param past The starting Date of the range
	 * @param present The ending Date of the range
	 * @return The List of FoodOrders that falls between the two provided Dates
	 */
	public List<FoodOrder> getFoodOrderByDate(Date past, Date present) {
		List<FoodOrder> returnList = new ArrayList<>();
		List<Object> lister = MyConnection.getFoodOrderBetween(past, present);
		if (lister.size() != 0) {
			for (Object o : lister) {
				returnList.add((FoodOrder) o);
			}
		}
		return returnList;
	}
	
	/**
	 * Retrieves the FoodOrder based on the provided Dates and Employee
	 * 
	 * @param earlierDate The starting range of the Date
	 * @param laterDate The ending range of the Date
	 * @param tempEmployee The designated Employee for retrieving the FoodOrder
	 * @return An ArrayList of FoodOrder objects that belongs to the Employee 
	 * and falls within the provided Dates
	 */
	public ArrayList<FoodOrder> getFoodOrderByDateUsername(Date earlierDate, Date laterDate,
			Employee tempEmployee) {

		ArrayList<FoodOrder> returnList = new ArrayList<>();
		List<Object> lister = MyConnection.getFoodForDatesAndUser(earlierDate, laterDate,
				tempEmployee);
		if (lister.size() != 0) {
			for (Object o : lister) {
				returnList.add((FoodOrder) o);
			}
		}
		return returnList;
	}
	
	/**
	 * Retrieve all FoodOrders based on the provided OrderWindow
	 * 
	 * @param orderWindow The provided OrderWindow for retrieving all FoodOrders
	 * @return An ArrayList of FoodOrder that is under the provided OrderWindow
	 */
	public ArrayList<FoodOrder> getAllFoodOrderOfOrderWindow(OrderWindow orderWindow) {
		ArrayList<FoodOrder> returnList = new ArrayList<FoodOrder>();

		DetachedCriteria dc = DetachedCriteria.forClass(FoodOrder.class);
		dc.add(Restrictions.eq("orderWindow", orderWindow));
		dc.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

		List<Object> l = MyConnection.queryWithCriteria(dc);

		for (Object o : l) {
			returnList.add((FoodOrder) o);
		}
		return returnList;
	}

}
