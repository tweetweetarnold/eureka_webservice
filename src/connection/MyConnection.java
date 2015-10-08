package connection;

import java.util.List;

import model.Admin;
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

	public static void startSession() {
		System.out.println("MyConnection startSession is called");

		try {
			if (sessionFactory == null) {
				// check if application is on OpenShift, if on OpenShift, sets
				// onOpenshift to true
				boolean onOpenshift = false;
				if (System.getenv("OPENSHIFT_MYSQL_DB_HOST") != null) {
					onOpenshift = true;
				}

				// Setting SessionFactory
				if (onOpenshift) {
					// if application on OpenShift
					sessionFactory = new Configuration().configure("hibernate.cfg.xml")
							.buildSessionFactory();
					System.out.println("hibernate.cfg.xml is loaded");
				} else {
					// if application on localhost
					sessionFactory = new Configuration().configure("hibernate-local.cfg.xml")
							.buildSessionFactory();
					System.out.println("hibernate-local.cfg.xml is loaded");
				}

				System.out.println("SessionFactory is set.");
				session = sessionFactory.openSession();
				System.out.println("Session is set.");
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	public static void delete(Object o) {
		System.out.println("MyConnection: delete");
		startSession();
		session.beginTransaction();
		session.delete(o);
		session.getTransaction().commit();
		// session.close();
	}

	public static void update(Object o) {
		System.out.println("MyConnection: update");
		startSession();
		session.beginTransaction();
		// Session session = startSession();
		session.update(o);
		session.getTransaction().commit();
		// session.close();
	}

	public static Object get(Class objClass, int id) {
		System.out.println("MyConnection: get");
		startSession();
		session.beginTransaction();
		// Session session = startSession();
		Object o = session.get(objClass, id);
		// session.close();
		return o;
	}

	public static void save(Object o) {
		System.out.println("MyConnection: save");
		startSession();
		session.beginTransaction();
		// Session session = startSession();
		session.save(o);
		session.getTransaction().commit();
		// session.close();
	}

	// for retrieve all
	public static List<Object> retrieveAllRecords(DetachedCriteria dc) {
		System.out.println("MyConnection: retrieveAllRecords");
		startSession();
		List<Object> list = null;
		session.beginTransaction();
		// Session session = startSession();
		Criteria criteria = dc.getExecutableCriteria(session);
		list = criteria.list();
		session.getTransaction().commit();
		// closeAll();
		return list;
	}

	// same as retrieveAll but with limit
	public static List<Object> retrieveAllRecordsWithLimit(DetachedCriteria dc, int max) {
		System.out.println("MyConnection: retrieveAllRecordsWithLimit");
		startSession();
		session.beginTransaction();
		// Session session = startSession();
		Criteria criteria = dc.getExecutableCriteria(session).setMaxResults(max);
		List<Object> list = criteria.list();
		session.getTransaction().commit();
		// closeAll();
		return list;
	}

	public static List<Object> get(String sql) {
		startSession();
		session.beginTransaction();
		// Criteria criteria = dc.getExecutableCriteria(session);
		Query query = session.createSQLQuery(sql).addEntity(FoodOrder.class);
		List<Object> list = (List<Object>) query.list();
		session.getTransaction().commit();
		// closeAll();
		return list;
	}

	public static List<Object> getEmployee(String sql) {
		startSession();
		session.beginTransaction();
		// Criteria criteria = dc.getExecutableCriteria(session);
		Query query = session.createSQLQuery(sql).addEntity(Employee.class);
		List<Object> list = (List<Object>) query.list();
		session.getTransaction().commit();
		// closeAll();
		return list;
	}

	public static List<Object> getAdmin(String sql) {
		startSession();
		session.beginTransaction();
		// Criteria criteria = dc.getExecutableCriteria(session);
		Query query = session.createSQLQuery(sql).addEntity(Admin.class);
		List<Object> list = (List<Object>) query.list();
		session.getTransaction().commit();
		// closeAll();
		return list;
	}

	public static void close() {
		System.out.println("MyConnection close");
		try {
			if (session.isOpen()) {
				session.close();
				System.out.println("MyConnection: Session has been closed");
			}
			if (!sessionFactory.isClosed()) {
				sessionFactory.close();
				System.out.println("MyConnection: SessionFactory has been closed");
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}
}
