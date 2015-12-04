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

	// retrieve Admin from the DB with adminId
	public Admin getAdmin(int adminId) {
		return (Admin) MyConnection.get(Admin.class, adminId);
	}

	public Admin getAdminByUsername(String username) {
		DetachedCriteria dc = DetachedCriteria.forClass(Admin.class);
		dc.add(Restrictions.eq("username", username));
		dc.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

		List<Object> l = (List<Object>) MyConnection.queryWithCriteria(dc);

		return (Admin) l.get(0);
	}

	// getting the list of payment that is based on the input parameter (i.e
	// "owed")
	public List<Employee> getListOfOwedPayment(String paymentOwedStatus) {
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
	public void saveAdmin(Admin d) {
		MyConnection.save(d);
	}

	// Update Admin in the DB
	public void updateAdmin(Admin d) {
		MyConnection.update(d);
	}

	// Delete Admin from the DB
	public static void deleteAdmin(Admin d) {
		MyConnection.delete(d);
	}
}
