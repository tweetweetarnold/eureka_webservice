package dao;

import java.util.ArrayList;
import java.util.List;

import model.Admin;
import model.Employee;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import connection.MyConnection;

public class AdminDAO {

	public AdminDAO() {
	}

	// retrieve Admin from the DB with adminId
	public static Admin getAdmin(int adminId) {
		return (Admin) MyConnection.get(Admin.class, adminId);
	}

	public static Admin getAdminByUsername(String username) {
		DetachedCriteria dc = DetachedCriteria.forClass(Admin.class);
		dc.add(Restrictions.eq("username", username));
		dc.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

		List<Object> l = (List<Object>) MyConnection.queryWithCriteria(dc);

		return (Admin) l.get(0);
	}

	// getting the list of payment that is based on the input parameter (i.e
	// "owed")
	public static List<Employee> getListOfOwedPayment(String paymentOwedStatus) {
		List<Employee> returnList = new ArrayList<>();

		List<Object> hiberList = MyConnection.getPaymentOwedList(paymentOwedStatus);
		if (hiberList.size() != 0) {
			for (Object o : hiberList) {
				returnList.add((Employee) o);
			}
		}
		return returnList;
	}

	// Save Admin to the DB
	public static void saveAdmin(Admin d) {
		MyConnection.save(d);
	}

	// Update Admin in the DB
	public static void updateAdmin(Admin d) {
		MyConnection.update(d);
	}

	// Delete Admin from the DB
	public static void deleteAdmin(Admin d) {
		MyConnection.delete(d);
	}

	// Retrieve Admin from DB using username
	// public static Admin getadminByUsername(String username) {
	// String sqlQuery = "SELECT * FROM admin where username=\"" + username +
	// "\"";
	// List<Object> lister = MyConnection.getAdmin(sqlQuery);
	//
	// for (Object o : lister) {
	// Admin admin = (Admin) o;
	// if (admin.getUsername().equals(username)) {
	// return admin;
	// }
	// }
	// return null;
	// }
}
