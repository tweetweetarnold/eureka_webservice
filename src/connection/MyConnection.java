package connection;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Company;
import model.Employee;
import model.Food;
import model.FoodOrder;
import model.FoodOrderItem;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class MyConnection {
	private static SessionFactory sessionFactory;

	// ****** Start of MyConnection methods ******

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

	public static Session startSession() {
		System.out.println("MyConnection startSession is called");
		try {
			Session session = getSession();
			System.out.println("Session is set: " + session);
			session.beginTransaction();
			System.out.println("Session transaction started");
			return session;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean endSession(Session session) {
		System.out.println("MyConnection endSession is called");
		try {
			session.getTransaction().commit();
			System.out.println("Session is committed");
			session.close();
			System.out.println("Session is closed");
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void closeSessionFactory() {
		try {
			sessionFactory.getCurrentSession().close();
			sessionFactory.close();
			System.out.println("SessionFactory is closed.");
		} catch (Exception e) {
			System.out.println("SessionFactory is already closed.");
		}
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

	// ****** End of MyConnection methods ******

	public static List<Object> getFoodOrderBetween(Date earlier, Date later) {
		Session session = startSession();
		List<Object> list = new ArrayList<>();
		Criteria criteria = session.createCriteria(FoodOrder.class);
		System.out.println(earlier + " " + later);
		criteria.add(Restrictions.between("createDate", earlier, later)).list();
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		list = (List<Object>) criteria.list();

		// Criteria criteria = dc.getExecutableCriteria(session);

		session.getTransaction().commit();
		session.close();
		return list;
	}

	// get objects with criteria
	public static List<Object> queryWithCriteria(DetachedCriteria dc) {
		System.out.println("MyConnection: getWithCriteria");
		Session session = startSession();

		Criteria criteria = dc.getExecutableCriteria(session).setResultTransformer(
				Criteria.DISTINCT_ROOT_ENTITY);
		List<Object> l = criteria.list();

		endSession(session);
		return l;
	}

	// same as retrieveAll but with limit
	public static List<Object> retrieveAllRecordsWithLimit(DetachedCriteria dc, int max) {
		System.out.println("MyConnection: retrieveAllRecordsWithLimit");
		Session session = startSession();
		Criteria criteria = dc.getExecutableCriteria(session).setMaxResults(max)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Object> list = (List<Object>) criteria.list();
		session.getTransaction().commit();
		session.close();
		return list;
	}

	// retrieve all records
	public static List<Object> retrieveAll(DetachedCriteria dc) {
		System.out.println("MyConnection: retrieveAll");
		Session session = startSession();
		Criteria criteria = dc.getExecutableCriteria(session).setResultTransformer(
				Criteria.DISTINCT_ROOT_ENTITY);
		List<Object> list = (List<Object>) criteria.list();
		session.getTransaction().commit();
		session.close();
		return list;
	}

	public static List<Object> getFoodOrderList(Employee employee) {
		Session session = startSession();
		List<Object> list = new ArrayList<>();
		Criteria criteria = session.createCriteria(FoodOrder.class).setResultTransformer(
				Criteria.DISTINCT_ROOT_ENTITY);
		String email = employee.getEmail();
		criteria.add(Restrictions.eq("employee", employee)).list();
		list = (List<Object>) criteria.list();

		session.getTransaction().commit();
		session.close();
		return list;
	}

	public static List<Object> getFoodOrderItemList(Food food, Date earlierDate, Date laterDate) {
		Session session = startSession();
		List<Object> list = new ArrayList<>();
		Criteria criteria = session.createCriteria(FoodOrderItem.class);
		Criterion thirdCondition = Restrictions.conjunction()
				.add(Restrictions.between("createDate", earlierDate, laterDate))
				.add(Restrictions.eq("food", food));
		criteria.add(thirdCondition).list();
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		list = (List<Object>) criteria.list();

		session.getTransaction().commit();
		session.close();
		return list;
	}

	public static List<Object> getFoodForDatesAndUser(Date earlierDate, Date laterDate,
			Employee tempEmployee) {
		Session session = startSession();
		List<Object> list = new ArrayList<>();
		Criteria criteria = session.createCriteria(FoodOrder.class);
		Criterion thirdCondition = Restrictions.conjunction()
				.add(Restrictions.between("createDate", earlierDate, laterDate))
				.add(Restrictions.eq("employee", tempEmployee));
		criteria.add(thirdCondition).list();
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		list = (List<Object>) criteria.list();

		session.getTransaction().commit();
		session.close();

		return list;

	}

	// used for registration in registrationcontroller
	public static Object getCompanyByCompanyCode(String companyCode) {
		Session session = startSession();
		List<Object> list = new ArrayList<>();
		Criteria criteria = session.createCriteria(Company.class);
		criteria.add(Restrictions.eq("companyCode", companyCode)).list();
		list = (List<Object>) criteria.list();

		session.getTransaction().commit();
		session.close();
		if (list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	// getting list of users whose payment status is owed(the input parameter)
	public static List<Object> getPaymentOwedList(String status) {
		Session session = startSession();
		List<Object> list = new ArrayList<>();
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.add(Restrictions.eq("status", status)).list();
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		list = (List<Object>) criteria.list();

		session.getTransaction().commit();
		session.close();

		return list;
	}

	public static List<Object> getUsersWithOutstandingPaymentFromCompany(Company company) {
		Session session = startSession();

		List<Object> list = new ArrayList<>();
		Criteria criteria = session.createCriteria(Employee.class);
		Criterion thirdCondition = Restrictions.conjunction()
				.add(Restrictions.eq("company", company)).add(Restrictions.ge("amountOwed", 0.01));
		criteria.add(thirdCondition).list();
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		list = (List<Object>) criteria.list();

		session.getTransaction().commit();
		session.close();

		return list;
	}

	public static List<Object> getUsersToChasePayment(Company company) {
		Session session = startSession();

		List<Object> list = new ArrayList<>();
		Criteria criteria = session.createCriteria(Employee.class);
		Criterion thirdCondition = Restrictions.conjunction()
				.add(Restrictions.eq("company", company))
				.add(Restrictions.eq("status", "Suspended"))
				.add(Restrictions.ge("amountOwed", 0.01));
		criteria.add(thirdCondition).list();
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		list = (List<Object>) criteria.list();

		session.getTransaction().commit();
		session.close();

		return list;
	}

}
