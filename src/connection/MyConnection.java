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

//	public MyConnection() {}
	
	public static Session startSession() {
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
		return session;
	}
	
//	for retrieve all
	public static List<Object> retrieveAllRecords(DetachedCriteria dc){
		startSession();
		Criteria criteria = dc.getExecutableCriteria(session);
		List<Object> list = criteria.list();
		session.getTransaction().commit();
		closeAll();
		return list;
	}
	
//	same as retrieveAll but with limit
	public static List<Object> retrieveAllRecordsWithLimit(DetachedCriteria dc, int max){
		startSession();
		Criteria criteria = dc.getExecutableCriteria(session).setMaxResults(max);
		List<Object> list = criteria.list();
		session.getTransaction().commit();
		closeAll();
		return list;
	}

	public static void delete(Object o) {
		startSession();
		session.delete(o);
		session.getTransaction().commit();
		closeAll();
	}

	public static void update(Object o) {
		startSession();
		session.update(o);
		session.getTransaction().commit();
		closeAll();
	}

	public static Object get(Class objClass, int id) {
		startSession();
		Object o = session.get(objClass, id);
		closeAll();
		return o;
	}
	
	//BH added a new get method that parses in objClass and username
	public static Object get(Class objClass, String username) {
		startSession();
		Object o = session.get(objClass, username);
		closeAll();
		return o;
	}

	public static void save(Object o) {
		startSession();
		session.save(o);
		session.getTransaction().commit();
		closeAll();
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
