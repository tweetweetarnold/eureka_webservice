package dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.Canteen;
import model.Employee;
import model.Stall;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import connection.MyConnection;

public class CanteenDAO {

	// Retrieve all Canteens from the DB
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
	
	// Retrieve Canteen from DB using canteen's name
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

	// Retrieve Canteen from the DB with canteenID
	public Canteen getCanteen(int canteenId) {
		return (Canteen) MyConnection.get(Canteen.class, canteenId);
	}
	
	//Add Stall to the current StallList and update existing canteen
	public void addStallToCanteen(Canteen c, Stall s) {
		Set<Stall> stallList = c.getStallList();
		if (stallList == null) {
			stallList = new HashSet<Stall>();
		}
		stallList.add(s);
		updateCanteen(c);
	}
	
	public Stall getStallFromCanteen(String canteenName, String stallName) {
		Canteen c = getCanteenByName(canteenName);
		Set<Stall> stallList = c.getStallList();
		System.out.println(stallList);
		for (Stall s : stallList) {
			if (s.getName().equals(stallName)) {
				return s;
			}
		}
		
		return null;
		
	}

	// Save new Canteen into DB
	public void saveCanteen(Canteen c) {
		MyConnection.save(c);
	}

	// Update existing Canteen in the DB
	public void updateCanteen(Canteen c) {
		MyConnection.update(c);
	}

	// Delete Canteen from the DB
	public void deleteCanteen(Canteen c) {
		MyConnection.delete(c);
	}
}
