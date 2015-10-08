package test;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import model.Admin;
import model.Canteen;
import model.Company;
import model.Employee;
import model.Food;
import model.FoodOrder;
import model.FoodOrderItem;
import model.Stall;
import connection.MyConnection;

public class Testt {

	public static void main(String[] args) {

		Canteen canteen = new Canteen("xiaodingdang", "123", new Date(), null);
		Stall stall = new Stall("stall", "123", "foodrepublic", 123, canteen,
				new Date(), null);
		Food food = new Food("chickenrice", "damn nice", 2.50, stall,
				new Date());
		Admin admin = new Admin("admin", "123", "admin123", 123, new Date());
		Company company = new Company("xiaodingdang co.", null, new Date(),
				null, null);
		Employee employee = new Employee("arnold123", "123", "arnold", 123, 10,
				123, company, null, null, new Date());
		FoodOrder order = new FoodOrder("done", employee, admin, null,
				new Date());
		FoodOrderItem foodItem = new FoodOrderItem(order, food, 2, 2.5,
				"remarks", new Date());
		
		List<Canteen> canteenList = new ArrayList<>();
		canteenList.add(canteen);
		company.setCanteenList(canteenList);
		List<FoodOrder> orderList = new ArrayList<>();
		orderList.add(order);
		employee.setOrderHistory(orderList);
		List<FoodOrderItem> foodOrderList = new ArrayList<>();
		foodOrderList.add(foodItem);
		order.setFoodOrderList(foodOrderList);
		List<Employee> employeeList = new ArrayList<>();
		employeeList.add(employee);
		company.setEmployeeList(employeeList);
		List<Food> foodList = new ArrayList<>();
		employee.setFavouriteList(foodList);
		foodList.add(food);
		List<Stall> stallList = new ArrayList<>();
		stallList.add(stall);
		stall.setFoodList(foodList);
		canteen.setStallList(stallList);

		MyConnection.save(canteen);
		MyConnection.save(stall);
		MyConnection.save(food);
		MyConnection.save(admin);
		MyConnection.save(company);
		MyConnection.save(employee);
		MyConnection.save(order);
		MyConnection.save(foodItem);

	}

}
