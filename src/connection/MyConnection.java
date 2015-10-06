package connection;

import java.util.List;

import model.Employee;
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

	public MyConnection() {
	}

	public static Session startSession() {
		System.out.println("MyConnection: startSession");
		try {
			if (sessionFactory == null) {
				sessionFactory = new Configuration().configure("hibernate.cfg.xml")
						.buildSessionFactory();
				System.out.println("SessionFactory is set.");
			}
			session = sessionFactory.openSession();
			System.out.println("Session is set.");
			session.beginTransaction();

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return session;
	}

	public static void delete(Object o) {
		System.out.println("MyConnection: delete");
		Session session = startSession();
		session.delete(o);
		session.getTransaction().commit();
		 session.close();
	}

	public static void update(Object o) {
		System.out.println("MyConnection: update");
		Session session = startSession();
		session.update(o);
		session.getTransaction().commit();
		 session.close();
	}

	public static Object get(Class objClass, int id) {
		System.out.println("MyConnection: get");
		Session session = startSession();
		Object o = session.get(objClass, id);
		 session.close();
		return o;
	}

	public static void save(Object o) {
		System.out.println("MyConnection: save");
		Session session = startSession();
		session.save(o);
		session.getTransaction().commit();
		 session.close();
	}

	// for retrieve all
	public static List<Object> retrieveAllRecords(DetachedCriteria dc) {
		System.out.println("MyConnection: retrieveAllRecords");
		Session session = startSession();
		Criteria criteria = dc.getExecutableCriteria(session);
		List<Object> list = criteria.list();
		session.getTransaction().commit();
		 closeAll();
		return list;
	}

	// same as retrieveAll but with limit
	public static List<Object> retrieveAllRecordsWithLimit(DetachedCriteria dc, int max) {
		System.out.println("MyConnection: retrieveAllRecordsWithLimit");
		Session session = startSession();
		Criteria criteria = dc.getExecutableCriteria(session).setMaxResults(max);
		List<Object> list = criteria.list();
		session.getTransaction().commit();
		 closeAll();
		return list;
	}

	public static List<Object> get(String sql) {
		Session session = startSession();

		// Criteria criteria = dc.getExecutableCriteria(session);
		Query query = session.createSQLQuery(sql).addEntity(FoodOrder.class);
		List<Object> list = (List<Object>) query.list();
		session.getTransaction().commit();
		 closeAll();
		return list;
	}
	
	public static List<Object> getEmployee(String sql) {
		Session session = startSession();

		// Criteria criteria = dc.getExecutableCriteria(session);
		Query query = session.createSQLQuery(sql).addEntity(Employee.class);
		List<Object> list = (List<Object>) query.list();
		session.getTransaction().commit();
		closeAll();
		return list;
	}

	public static void closeAll() {
		try {
			session.close();
//			sessionFactory.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}
}
