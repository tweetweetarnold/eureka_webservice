package connection;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Admin;
import model.Company;
import model.Employee;
import model.FoodOrder;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class MyConnection {
	private static SessionFactory sessionFactory;

	static {
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
				System.out.println("SessionFactory is set: " + sessionFactory);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	public static Session getSession() {
		System.out.println("MyConnection getSession is called");
		try {
			Session session = sessionFactory.openSession();
			System.out.println("Session is set: " + session);
			return session;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void delete(Object o) {
		System.out.println("MyConnection: delete");
		Session session = getSession();
		session.beginTransaction();
		session.delete(o);
		session.getTransaction().commit();
		session.close();
	}

	public static void update(Object o) {
		System.out.println("MyConnection: update");
		Session session = getSession();
		session.beginTransaction();
		session.update(o);
		session.getTransaction().commit();
		session.close();
	}

	public static Object get(Class objClass, int id) {
		System.out.println("MyConnection: get");
		Session session = getSession();
		session.beginTransaction();
		Object o = session.get(objClass, id);
		session.close();
		return o;
	}

	public static void save(Object o) {
		System.out.println("MyConnection: save");
		Session session = getSession();
		session.beginTransaction();
		session.save(o);
		session.getTransaction().commit();
		session.close();
	}
	
	public static List<Object> getFoodOrderBetween(Date later, Date earlier) {
		Session session = getSession();
		System.out.println("here");
		session.beginTransaction();
		List<Object> list = new ArrayList<>();
		Criteria criteria = session.createCriteria(FoodOrder.class);
		System.out.println(earlier +" "+  later);
		criteria.add(Restrictions.between("createDate", earlier, later)).list();
		list = (List<Object>) criteria.list();

		// Criteria criteria = dc.getExecutableCriteria(session);

		session.getTransaction().commit();
		session.close();
		System.out.println("here " + list.size());
		return list;
		
	}
	
	// for retrieve all
	public static List<Object> retrieveAllRecords(DetachedCriteria dc) {
		System.out.println("MyConnection: retrieveAllRecords");
		Session session = getSession();
		List<Object> list = null;
		session.beginTransaction();
		Criteria criteria = dc.getExecutableCriteria(session);
		list = criteria.list();
		session.getTransaction().commit();
		session.close();
		return list;
	}

	// same as retrieveAll but with limit
	public static List<Object> retrieveAllRecordsWithLimit(DetachedCriteria dc, int max) {
		System.out.println("MyConnection: retrieveAllRecordsWithLimit");
		Session session = getSession();
		session.beginTransaction();
		Criteria criteria = dc.getExecutableCriteria(session).setMaxResults(max);
		List<Object> list = criteria.list();
		session.getTransaction().commit();
		session.close();
		return list;
	}

	public static List<Object> getFoodOrderList(Employee employee) {
		Session session = getSession();
		session.beginTransaction();
		List<Object> list = new ArrayList<>();
		Criteria criteria = session.createCriteria(FoodOrder.class);
		String username = employee.getUsername();
		criteria.add(Restrictions.eq("employee", employee)).list();
		list = (List<Object>) criteria.list();

		// Criteria criteria = dc.getExecutableCriteria(session);

		session.getTransaction().commit();
		session.close();
		return list;
	}

	// used for registration in registrationcontroller
	public static Object getCompanyByCompanyCode(String companyCode) {
		Session session = getSession();
		session.beginTransaction();
		List<Object> list = new ArrayList<>();
		Criteria criteria = session.createCriteria(Company.class);
		criteria.add(Restrictions.eq("companyCode", companyCode)).list();
		list = (List<Object>) criteria.list();

		session.getTransaction().commit();
		session.close();
		return list.get(0);
	}
	
	//getting list of users whose payment status is owed(the input parameter)
	public static List<Object> getPaymentOwedList(String status) {
		Session session = getSession();
		session.beginTransaction();
		List<Object> list = new ArrayList<>();
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.add(Restrictions.eq("status", status)).list();
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		list = (List<Object>) criteria.list();
		
		// Criteria criteria = dc.getExecutableCriteria(session);

		session.getTransaction().commit();
		session.close();
		
		return list;
	}
	
	public static List<Object> get(String sql) {
		Session session = getSession();
		session.beginTransaction();
		// Criteria criteria = dc.getExecutableCriteria(session);
		Query query = session.createSQLQuery(sql).addEntity(FoodOrder.class);
		List<Object> list = (List<Object>) query.list();
		session.getTransaction().commit();
		session.close();
		return list;
	}

	public static List<Object> get(String sql, String past, String present) {
		Session session = getSession();
		session.beginTransaction();
		// Criteria criteria = dc.getExecutableCriteria(session);
		Query query = session.createSQLQuery(sql).addEntity(FoodOrder.class);
		query.setParameter("date1", past);
		query.setParameter("date2", present);
		List<Object> list = (List<Object>) query.list();
		session.getTransaction().commit();
		// closeAll();
		return list;
	}

	public static List<Object> getEmployee(String sql) {
		Session session = getSession();
		session.beginTransaction();
		// Criteria criteria = dc.getExecutableCriteria(session);
		Query query = session.createSQLQuery(sql).addEntity(Employee.class);
		List<Object> list = (List<Object>) query.list();
		session.getTransaction().commit();
		session.close();
		return list;
	}

	public static List<Object> getAdmin(String sql) {
		Session session = getSession();
		session.beginTransaction();
		// Criteria criteria = dc.getExecutableCriteria(session);
		Query query = session.createSQLQuery(sql).addEntity(Admin.class);
		List<Object> list = (List<Object>) query.list();
		session.getTransaction().commit();
		session.close();
		return list;
	}

}
