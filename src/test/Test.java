package test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.json.simple.JSONObject;

import com.javadocmd.simplelatlng.LatLng;

import connection.MyConnection;
import entity.Canteen;
import entity.Company;
import entity.Driver;
import entity.Food;
import entity.FoodOrder;
import entity.FoodOrderItem;
import entity.Hawker;
import entity.Employee;

public class Test {

	public static void main(String[] args) {

		Canteen canteen = new Canteen("Kopitiam", "123 Street", new LatLng(1.2,
				3.4), new Date(), null);
		Hawker hawker = new Hawker("abc", "123", "Chris Cheng", 456456,
				canteen, new Date(), null, null);
		Company company = new Company("Apple", null, new Date());
		Driver driver = new Driver("abc", "123", "Arnold", 456546, new Date());
		Food food = new Food("Chicken Wings", "Delicious", 2.5, null,
				new Date());
		Food food2 = new Food("French Fries", "Delicious", 2.5, null,
				new Date());
		FoodOrder order = new FoodOrder("Done", null, null, null, new Date());
		FoodOrderItem item = new FoodOrderItem(order, food, 2, 2.5, "Remarks",
				new Date());
		Set<Food> fav = new HashSet<Food>();
		fav.add(food);
		fav.add(food2);
		Employee employee = new Employee("abc", "123", "Boon Hui", "POSB",
				456456, company, fav, new Date());
		
		MyConnection.save(canteen);

//		SessionFactory sessionFactory = new Configuration().configure()
//				.buildSessionFactory();
//		Session session = sessionFactory.openSession();
//		session.beginTransaction();
//
//		session.save(canteen);
//		session.save(hawker);
//		session.save(company);
//		session.save(driver);
//		session.save(food);
//		session.save(order);
//		session.save(item);
//		session.save(employee);
//
//		// JSONObject obj = new JSONObject();
//
//		session.getTransaction().commit();
//		session.close();
//		sessionFactory.close();
	}

}
