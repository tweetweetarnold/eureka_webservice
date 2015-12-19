package dao;

import java.util.ArrayList;
import java.util.List;

import model.Canteen;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;

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

	// Retrieve Canteen from the DB with canteenID
	public Canteen getCanteen(int canteenId) {
		return (Canteen) MyConnection.get(Canteen.class, canteenId);
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
