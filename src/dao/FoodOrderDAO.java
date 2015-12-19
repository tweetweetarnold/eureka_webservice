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

public class FoodOrderDAO {

	// Retrieve FoodOrder from the DB with foodOrderID
	public FoodOrder getFoodOrder(int foodOrderId) {
		return (FoodOrder) MyConnection.get(FoodOrder.class, foodOrderId);
	}

	// Save new FoodOrder to DB
	public void saveFoodOrder(FoodOrder f) {
		MyConnection.save(f);
	}

	// Update existing FoodOrder in DB
	public void updateFoodOrder(FoodOrder f) {
		MyConnection.update(f);
	}

	// Delete FoodOrder from DB
	public void deleteFoodOrder(FoodOrder f) {
		MyConnection.delete(f);
	}

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
