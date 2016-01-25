package dao;

import java.util.ArrayList;
import java.util.List;

import model.Admin;
import model.Employee;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import connection.MyConnection;

/**
 * Performs the function of Data Access Object for the Admin model
 * 
 * @author SMU Team Eureka
 */
public class AdminDAO {
	
	/**
	 * Removes the designated Admin object from the Database
	 * 
	 * @param d The Admin object to be removed
	 */
	public static void deleteAdmin(Admin d) {
		MyConnection.delete(d);
	}
	
	/**
	 * Creates a default constructor for AdminDAO
	 */
	public AdminDAO() {
		
	}
	
	/**
	 * Retrieve the Admin based on the provided ID
	 * @param adminId The ID used for retrieving the Admin
	 * @return The Admin object that has the provided ID
	 */
	public Admin getAdmin(int adminId) {
		return (Admin) MyConnection.get(Admin.class, adminId);
	}

	/**
	 * Retrieve the Admin based on the provided username
	 * @param username The username of the Admin
	 * @return The Admin object that has the provided username
	 */
	public Admin getAdminByUsername(String username) {
		DetachedCriteria dc = DetachedCriteria.forClass(Admin.class);
		dc.add(Restrictions.eq("username", username));
		dc.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

		List<Object> l = (List<Object>) MyConnection.queryWithCriteria(dc);

		return (Admin) l.get(0);
	}

	// getting the list of payment that is based on the input parameter (i.e
	// "owed")
	/**
	 * Retrieve the list outstanding payments based on the provided payment status
	 * @param paymentOwedStatus The payment status
	 * @return The List of Employee objects whom still have outstanding payments
	 */
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

	/**
	 * Adds a new Admin object to the Database
	 * 
	 * @param d The Admin object to be added in
	 */
	public void saveAdmin(Admin d) {
		MyConnection.save(d);
	}

	/**
	 * Updates the designated Admin object in the Database
	 * 
	 * @param d The Admin object to be updated
	 */
	public void updateAdmin(Admin d) {
		MyConnection.update(d);
	}
}
