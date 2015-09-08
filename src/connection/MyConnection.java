package connection;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.DetachedCriteria;

import entity.Canteen;

public class MyConnection {
	private static SessionFactory sessionFactory;
	private static Session session;

	public MyConnection() {
		try {
			if (session == null) {
				sessionFactory = new Configuration().configure()
						.buildSessionFactory();
				session = sessionFactory.openSession();
				session.beginTransaction();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}
	
//	for retrieve all
	public static List<Object> retrieveAllRecords(DetachedCriteria dc){
		session.beginTransaction();
		Criteria criteria = dc.getExecutableCriteria(session);
		List<Object> list = criteria.list();
		session.getTransaction().commit();
		return list;
	}
	
//	same as retrieveAll but with limit
	public static List<Object> retrieveAllRecordsWithLimit(DetachedCriteria dc, int max){
		session.beginTransaction();
		Criteria criteria = dc.getExecutableCriteria(session).setMaxResults(max);
		List<Object> list = criteria.list();
		session.getTransaction().commit();
		return list;
	}

	public static void delete(Object o) {
		session.delete(o);
		session.getTransaction().commit();
	}

	public static void update(Object o) {
		session.update(o);
		session.getTransaction().commit();
	}

	public static Object get(Class objClass, int id) {
		Object o = session.get(objClass, id);
		return o;
	}
	
	//BH added a new get method that parses in objClass and username
	public static Object get(Class objClass, String username) {
		Object o = session.get(objClass, username);
		return o;
	}

	public static void save(Object o) {
		session.save(o);
		session.getTransaction().commit();
	}

	public static void saveOnly(Object o) {
		session.save(o);
	}

	public static void commit() {
		session.getTransaction().commit();
	}

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
