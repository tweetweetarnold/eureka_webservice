package dao;

import java.util.ArrayList;
import java.util.List;

import model.Canteen;

import org.hibernate.criterion.DetachedCriteria;

import connection.MyConnection;

public class CanteenDAO {

	// Retrieve all Canteens from the DB
	public List<Canteen> getAllCanteens() {
		List<Canteen> returnList = new ArrayList<>();
		List<Object> list = MyConnection
				.queryWithCriteria(DetachedCriteria.forClass(Canteen.class));
		for (Object o : list) {
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
