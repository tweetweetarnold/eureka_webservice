package test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import entity.FoodOrder;
import entity.User;

public class Test {

	public static void main(String[] args) {
		
		User user = new User("arnold.lee.2013", "123", "Arnold", new Date());
		FoodOrder order = new FoodOrder(1, 2, new Date(), user);
		User user2 = new User("arnold.lee.2013", "123", "Jiamin", new Date());
		FoodOrder order2 = new FoodOrder(1, 2, new Date(), user2);
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.save(order);
		session.save(order2);
		
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
	}

}
