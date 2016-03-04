package dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

import model.Canteen;
import model.Company;
import model.Employee;
import model.Food;
import model.FoodOrder;
import model.OrderWindow;
import connection.MyConnection;

/**
 * Performs the function of Data Access Object for the FoodOrder model
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
	 * Removes the designated FoodOrder object from the Database
	 * 
	 * @param f The FoodOrder object to be removed
	 */
	public void deleteFoodOrder(FoodOrder f) {
		MyConnection.delete(f);
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

	public ArrayList<FoodOrder> getAllFoodOrderOfOrderWindowForUser(Employee employee,
			OrderWindow orderWindow) {
		ArrayList<FoodOrder> returnList = new ArrayList<FoodOrder>();

		DetachedCriteria dc = DetachedCriteria.forClass(FoodOrder.class);
		dc.add(Restrictions.eq("orderWindow", orderWindow));
		dc.add(Restrictions.eq("employee", employee));
		dc.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

		List<Object> l = MyConnection.queryWithCriteria(dc);

		for (Object o : l) {
			returnList.add((FoodOrder) o);
		}
		return returnList;
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
	 * Retrieve FoodOrders from the database that falls between two provided Dates (in
	 * "yyyy-MM-dd hh:mm:ss")
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
	 * @return An ArrayList of FoodOrder objects that belongs to the Employee and falls within the
	 *         provided Dates
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
		Collections.sort(returnList, new Comparator<FoodOrder>() {
		    public int compare(FoodOrder f1, FoodOrder f2) {
		        return f2.getCreateDate().compareTo(f1.getCreateDate());
		    }
		});
		
		return returnList;
	}
	
	public List<FoodOrder> getCompanyFoodOrderSet(Company company) {
		List<FoodOrder> returnList = new ArrayList<>();

		List<Object> hiberList = MyConnection.getCompanyFoodOrderList(company);
		if (hiberList.size() != 0) {
			for (Object o : hiberList) {
				returnList.add((FoodOrder) o);
			}
		}
		Collections.sort(returnList, new Comparator<FoodOrder>() {
		    public int compare(FoodOrder f1, FoodOrder f2) {
		        return f2.getCreateDate().compareTo(f1.getCreateDate());
		    }
		});
		
		return returnList;
	}

	public List<Object> getUniqueMonthYearInFoodOrderForCompany(Company company) {
		List<Object> returnList = new ArrayList<>();

		DetachedCriteria dc = DetachedCriteria.forClass(FoodOrder.class, "FoodOrder");
		//dc.add(Restrictions.eq("orderWindow", orderWindow));
		//dc.add(Restrictions.eq("company", company));
		dc.createCriteria("employee")
		   .add(Restrictions.eq("company", company));
		dc.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		
		ProjectionList projectionList = Projections.projectionList();
		   projectionList.add(Projections.sqlGroupProjection(
		          "Date_format({alias}.createDate, '%Y-%m') as yearmonth",
		         "Date_format({alias}.createDate, '%Y-%m')", new String[] { "yearmonth" },
		            new Type[] { StandardBasicTypes.STRING }));
		   
//		   projectionList.add(Projections.sqlGroupProjection(
//			          "YEAR(createDate) as year",
//			         "YEAR(createDate)", new String[] { "year" },
//			            new Type[] { StandardBasicTypes.STRING }));
		
		    //projectionList.add(Projections.rowCount());
		 dc.setProjection(projectionList);
		 dc.addOrder(org.hibernate.criterion.Order.desc("createDate"));
		    //return criteria.list();

		List<Object> l = MyConnection.queryWithCriteria(dc);

		for (Object o : l) {
			returnList.add((Object) o);
		}
		return returnList;
	}
	
	
	
	
	public List<Object> getUniqueMonthYearInFoodOrderForUser(Employee employee) {
		List<Object> returnList = new ArrayList<>();

		DetachedCriteria dc = DetachedCriteria.forClass(FoodOrder.class);
		//dc.add(Restrictions.eq("orderWindow", orderWindow));
		dc.add(Restrictions.eq("employee", employee));
		dc.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		
		ProjectionList projectionList = Projections.projectionList();
		   projectionList.add(Projections.sqlGroupProjection(
		          "Date_format(createDate, '%Y-%m') as yearmonth",
		         "Date_format(createDate, '%Y-%m')", new String[] { "yearmonth" },
		            new Type[] { StandardBasicTypes.STRING }));
		   
//		   projectionList.add(Projections.sqlGroupProjection(
//			          "YEAR(createDate) as year",
//			         "YEAR(createDate)", new String[] { "year" },
//			            new Type[] { StandardBasicTypes.STRING }));
		
		    //projectionList.add(Projections.rowCount());
		 dc.setProjection(projectionList);
		 dc.addOrder(org.hibernate.criterion.Order.desc("createDate"));
		    //return criteria.list();

		List<Object> l = MyConnection.queryWithCriteria(dc);

		for (Object o : l) {
			returnList.add((Object) o);
		}
		return returnList;
	}

	public List<Object> getUniqueYearInFoodOrderForCompany(Company company) {
		List<Object> returnList = new ArrayList<>();

		DetachedCriteria dc = DetachedCriteria.forClass(FoodOrder.class, "FoodOrder");
		//dc.add(Restrictions.eq("orderWindow", orderWindow));
		//dc.add(Restrictions.eq("company", company));
		dc.createCriteria("employee")
		   .add(Restrictions.eq("company", company));
		
		dc.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		
		ProjectionList projectionList = Projections.projectionList();
		//projectionList.add(Projections.property("FoodOrder.createDate"), "FoodOrderCreateDate");
		   projectionList.add(Projections.sqlGroupProjection(
		          "Date_format({alias}.createDate, '%Y') as year",
		         "Date_format({alias}.createDate, '%Y')", new String[] { "year" },
		            new Type[] { StandardBasicTypes.STRING }));
		 
		   
		 dc.setProjection(projectionList);
		
		 dc.addOrder(org.hibernate.criterion.Order.desc("createDate"));
		    //return criteria.list();

		List<Object> l = MyConnection.queryWithCriteria(dc);

		for (Object o : l) {
			returnList.add((Object) o);
		}
		return returnList;
	}
	
	public List<Object> getUniqueYearInFoodOrderForUser(Employee employee) {
		List<Object> returnList = new ArrayList<>();

		DetachedCriteria dc = DetachedCriteria.forClass(FoodOrder.class);
		//dc.add(Restrictions.eq("orderWindow", orderWindow));
		dc.add(Restrictions.eq("employee", employee));
		dc.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		
		ProjectionList projectionList = Projections.projectionList();
		   projectionList.add(Projections.sqlGroupProjection(
		          "Date_format(createDate, '%Y') as year",
		         "Date_format(createDate, '%Y')", new String[] { "year" },
		            new Type[] { StandardBasicTypes.STRING }));
		   
//		   projectionList.add(Projections.sqlGroupProjection(
//			          "YEAR(createDate) as year",
//			         "YEAR(createDate)", new String[] { "year" },
//			            new Type[] { StandardBasicTypes.STRING }));
		
		    //projectionList.add(Projections.rowCount());
		 dc.setProjection(projectionList);
		 dc.addOrder(org.hibernate.criterion.Order.desc("createDate"));
		    //return criteria.list();

		List<Object> l = MyConnection.queryWithCriteria(dc);

		for (Object o : l) {
			returnList.add((Object) o);
		}
		return returnList;
	}
	
	public List<String> getUniqueWeekInFoodOrderForCompany(Company company) {
		List<String> returnList = new ArrayList<>();

		DetachedCriteria dc = DetachedCriteria.forClass(FoodOrder.class, "FoodOrder");
		//dc.add(Restrictions.eq("orderWindow", orderWindow));
		//dc.add(Restrictions.eq("company", company));
		dc.createCriteria("employee")
		   .add(Restrictions.eq("company", company));
		dc.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		
		ProjectionList projectionList = Projections.projectionList();
		   projectionList.add(Projections.sqlGroupProjection(
				   
				  "Concat(Date_format(DATE_ADD({alias}.createDate, INTERVAL(2-DAYOFWEEK({alias}.createDate)) DAY), '%Y-%m-%d'), ' to ', Date_format(DATE_ADD({alias}.createDate, INTERVAL(8-DAYOFWEEK({alias}.createDate)) DAY), '%Y-%m-%d')) as week",
		         // "DATE_ADD(createDate, INTERVAL(1-DAYOFWEEK(createDate)) DAY) as start, DATE_ADD(createDate, INTERVAL(7-DAYOFWEEK(createDate)) DAY) as end",
		         "WEEK({alias}.createDate)", new String[] { "week" },
		            new Type[] { StandardBasicTypes.STRING }));
		   
		 dc.setProjection(projectionList);
		 dc.addOrder(org.hibernate.criterion.Order.desc("createDate"));
		    //return criteria.list();

		List<Object> l = MyConnection.queryWithCriteria(dc);

		for (Object o : l) {
			returnList.add((String) o);
		}
		return returnList;
	}
	
	public List<String> getUniqueWeekInFoodOrderForUser(Employee employee) {
		List<String> returnList = new ArrayList<>();

		DetachedCriteria dc = DetachedCriteria.forClass(FoodOrder.class);
		//dc.add(Restrictions.eq("orderWindow", orderWindow));
		dc.add(Restrictions.eq("employee", employee));
		dc.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		
		ProjectionList projectionList = Projections.projectionList();
		   projectionList.add(Projections.sqlGroupProjection(
				   
				  "Concat(Date_format(DATE_ADD(createDate, INTERVAL(2-DAYOFWEEK(createDate)) DAY), '%Y-%m-%d'), ' to ', Date_format(DATE_ADD(createDate, INTERVAL(8-DAYOFWEEK(createDate)) DAY), '%Y-%m-%d')) as week",
		         // "DATE_ADD(createDate, INTERVAL(1-DAYOFWEEK(createDate)) DAY) as start, DATE_ADD(createDate, INTERVAL(7-DAYOFWEEK(createDate)) DAY) as end",
		         "WEEK(createDate)", new String[] { "week" },
		            new Type[] { StandardBasicTypes.STRING }));
		   
//		   projectionList.add(Projections.sqlGroupProjection(
//			          "YEAR(createDate) as year",
//			         "YEAR(createDate)", new String[] { "year" },
//			            new Type[] { StandardBasicTypes.STRING }));
		
		    //projectionList.add(Projections.rowCount());
		 dc.setProjection(projectionList);
		 dc.addOrder(org.hibernate.criterion.Order.desc("createDate"));
		    //return criteria.list();

		List<Object> l = MyConnection.queryWithCriteria(dc);

		for (Object o : l) {
			returnList.add((String) o);
		}
		return returnList;
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
	
	
//	public List<FoodOrder> searchCompanyFoodOrders(Company company){
//		DetachedCriteria dc = DetachedCriteria.forClass(FoodOrder.class);;
//		   //.add(Restrictions.like("name","%"+query+"%"));
//		
//		dc.createCriteria("employee")
//		   .add(Restrictions.eq("company", company));
//		
//		dc.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
//
//		List<Object> l = MyConnection.queryWithCriteria(dc);
//		List<FoodOrder> returnList = new ArrayList<FoodOrder>();
//		for (Object o : l) {
//			returnList.add((FoodOrder) o);
//		}
//		return returnList;
//	}
}
