package connection;

import java.util.List;

import model.Canteen;
import model.FoodOrder;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.DetachedCriteria;

public class MyConnection {
	private static SessionFactory sessionFactory;
	private static Session session;

//	public MyConnection() {}
	
	public static Session startSession() {
		System.out.println("running start session");
		try {
			if (sessionFactory == null) {
				System.out.println("sessionfactory is null");
				sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
				System.out.println("sessionfactory is set");
				session = sessionFactory.openSession();
				System.out.println("session is set. Session: " + session);
				session.beginTransaction();
			}else{
				session = sessionFactory.openSession();
				session.beginTransaction();
			}
			System.out.println("sessionfactory: " + sessionFactory);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return session;
	}
	
//	for retrieve all
	public static List<Object> retrieveAllRecords(DetachedCriteria dc){
		startSession();
		Criteria criteria = dc.getExecutableCriteria(session);
		List<Object> list = criteria.list();
		session.getTransaction().commit();
//		closeAll();
		return list;
	}
	
//	same as retrieveAll but with limit
	public static List<Object> retrieveAllRecordsWithLimit(DetachedCriteria dc, int max){
		startSession();
		Criteria criteria = dc.getExecutableCriteria(session).setMaxResults(max);
		List<Object> list = criteria.list();
		session.getTransaction().commit();
//		closeAll();
		return list;
	}

	public static void delete(Object o) {
		startSession();
		session.delete(o);
		session.getTransaction().commit();
//		closeAll();
	}

	public static void update(Object o) {
		startSession();
		session.update(o);
		session.getTransaction().commit();
//		closeAll();
	}

	public static Object get(Class objClass, int id) {
		startSession();
		Object o = session.get(objClass, id);
//		closeAll();
		return o;
	}
	
	//BH added a new get method that parses in objClass and username
	public static Object get(Class objClass, String username) {
		startSession();
		Object o = session.get(objClass, username);
//		closeAll();
		return o;
	}

	public static List<Object> get(String sql) {
		startSession();
		
//		Criteria criteria = dc.getExecutableCriteria(session);
		Query query = session.createSQLQuery(sql).addEntity(FoodOrder.class);
		List<Object> list = (List<Object>) query.list();
		session.getTransaction().commit();
//		closeAll();
		return list;
	}
	public static void save(Object o) {
		System.out.println("running save...");
		startSession();
		session.save(o);
		session.getTransaction().commit();
//		closeAll();
	}

//	public static void saveOnly(Object o) {
//		session.save(o);
//	}

//	public static void commit() {
//		session.getTransaction().commit();
//	}

	public static void closeSession() {
		try {
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	public static void closeSessionFactory() {
		try {
			sessionFactory.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	public static void closeAll() {
		try {
			session.close();
			sessionFactory.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}
}
