package dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import model.Canteen;
import model.Stall;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import connection.MyConnection;

/**
 * Performs the function of Data Access Object for the Canteen model
 * 
 * 
 */
public class CanteenDAO {

	/**
	 * Creates a default constructor for CanteenDAO
	 */
	public CanteenDAO() {

	}

	/**
	 * Retrieves all the Canteens from the Database
	 * 
	 * @return An ArrayList of Canteen objects
	 */
	public ArrayList<Canteen> getAllCanteens() {
		ArrayList<Canteen> returnList = null;

		DetachedCriteria dc = DetachedCriteria.forClass(Canteen.class);
		dc.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

		List<Object> l = MyConnection.queryWithCriteria(dc);

		returnList = new ArrayList<Canteen>();

		for (Object o : l) {
			returnList.add((Canteen) o);
		}
		return returnList;
	}

	/**
	 * Retrieve the Canteen based on the provided canteen name
	 * 
	 * @param canteenName The name of the Canteen
	 * @return The Canteen object that has the provided canteen name
	 */
	public Canteen getCanteenByName(String canteenName) {
		DetachedCriteria dc = DetachedCriteria.forClass(Canteen.class);
		dc.add(Restrictions.eq("name", canteenName));
		dc.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

		List<Object> l = MyConnection.queryWithCriteria(dc);
		if (l.size() == 0) {
			return null;
		}
		return (Canteen) l.get(0);
	}
	
	/**
	 * Retrieve the Canteen based on the provided address
	 * 
	 * @param address The address of the Canteen
	 * @return The Canteen object that has the provided address
	 */
	public Canteen getCanteenByAddress(String address) {
		DetachedCriteria dc = DetachedCriteria.forClass(Canteen.class);
		dc.add(Restrictions.eq("address", address));
		dc.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

		List<Object> l = MyConnection.queryWithCriteria(dc);
		if (l.size() == 0) {
			return null;
		}
		return (Canteen) l.get(0);
	}

	/**
	 * Retrieve the Canteen based on the provided ID
	 * 
	 * @param canteenId The ID used for retrieving the Canteen
	 * @return The Canteen object that has the provided ID
	 */
	public Canteen getCanteen(int canteenId) {
		return (Canteen) MyConnection.get(Canteen.class, canteenId);
	}

	// Add Stall to the current StallList and update existing canteen
	/**
	 * Adds a new Stall to the Canteen
	 * 
	 * @param c The designated Canteen to add the Stall
	 * @param s The Stall to be added to the Canteen
	 */
	public void addStallToCanteen(Canteen c, Stall s) {
		Set<Stall> stallList = c.getStallList();
		if (stallList == null) {
			stallList = new HashSet<Stall>();
		}
		stallList.add(s);
		updateCanteen(c);
	}

	/**
	 * Retrieve the Stall from the provided Canteen name and Stall name
	 * 
	 * @param canteenName The name of the Canteen
	 * @param stallName The name of the Stall
	 * @return The Stall object that has the provided Stall name and belongs to the provided Canteen
	 *         name , otherwise returns null
	 */
	public Stall getStallFromCanteen(String canteenName, String stallName) {
		Canteen c = getCanteenByName(canteenName);
		if (c != null) {
			Set<Stall> stallList = c.getStallList();
			System.out.println(stallList);
			for (Stall s : stallList) {
				if (s.getName().equals(stallName)) {
					return s;
				}
			}
		}

		return null;

	}

	/**
	 * Adds a new Canteen object to the database
	 * 
	 * @param c The Canteen object to be added in
	 */
	public void saveCanteen(Canteen c) {
		MyConnection.save(c);
	}

	/**
	 * Updates the designated Canteen object in the database
	 * 
	 * @param c The Canteen object to be updated
	 */
	public void updateCanteen(Canteen c) {
		MyConnection.update(c);
	}

	/**
	 * Removes the designated Canteen object from the database
	 * 
	 * @param c The Canteen object to be removed
	 */
	public void deleteCanteen(Canteen c) {
		MyConnection.delete(c);
	}

	/**
	 * Load the validated content of the Canteen.csv into the database
	 * 
	 * @param content The list of Canteen data to be loaded into the database
	 */
	public void loadCanteenData(List<String[]> content) {
		Iterator<String[]> iter = content.iterator();
		iter.next();
		while (iter.hasNext()) {
			String[] row = (String[]) iter.next();
			String canteenName = row[0].trim();
			String address = row[1].trim();

			Canteen newCanteen = new Canteen(canteenName, address, null);
			saveCanteen(newCanteen);
		}
	}
}
