package test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import model.Canteen;
import model.Company;
import model.Admin;
import model.Employee;
import model.Food;
import model.FoodOrder;
import model.FoodOrderItem;
import model.Stall;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test {

	public static void main(String[] args) {

		Canteen canteen = new Canteen("xiaodingdang", "123", new Date(), null);
		Stall stall = new Stall("stall", "123", "foodrepublic", 123, canteen, new Date(), null);
		Food food = new Food("chickenrice", "damn nice", 2.50, stall, new Date());
		Admin admin = new Admin("admin", "123", "admin123", 123, new Date());
		Company company = new Company("xiaodingdang co.", null, new Date(), null, null);
		Employee employee = new Employee("arnold123", "123", "arnold", 123, 10, 123, company, null, null, new Date());
		FoodOrder order = new FoodOrder("done", employee, admin, null, new Date());
		FoodOrderItem foodItem = new FoodOrderItem(order, food, 2, 2.5, "remarks", new Date());
		
		Set<Canteen> canteenList = new HashSet<>();
		canteenList.add(canteen);
		company.setCanteenList(canteenList);
		Set<FoodOrder> orderList = new HashSet<>();
		orderList.add(order);
		employee.setOrderHistory(orderList);
		Set<FoodOrderItem> foodOrderList = new HashSet<>();
		foodOrderList.add(foodItem);
		order.setFoodOrderList(foodOrderList);
		Set<Employee> employeeList = new HashSet<>();
		employeeList.add(employee);
		company.setEmployeeList(employeeList);
		Set<Food> foodList = new HashSet<>();
		employee.setFavouriteList(foodList);
		foodList.add(food);
		Set<Stall> stallList = new HashSet<>();
		stallList.add(stall);
		stall.setFoodList(foodList);
		canteen.setStallList(stallList);
		
		
		
		
		
		

		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		// JSONObject obj = new JSONObject();

		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
	}

}
