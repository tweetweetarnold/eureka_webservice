package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import connection.MyConnection;
import entity.Canteen;

public class CanteenDAO {
	
	public CanteenDAO() {
		
	}
	
	public static List<Canteen> getAllCanteens(){
		List<Canteen> returnList = new ArrayList<>();
		List<Object> list = MyConnection.retrieveAllRecords(DetachedCriteria.forClass(Canteen.class));
		for(Object o : list) {
			returnList.add((Canteen) o);
		}
		return returnList;
	}
	
	public static Canteen getCanteen(int canteenId) {
		return (Canteen) MyConnection.get(Canteen.class, canteenId);
	}
	
	public static void saveCanteen(Canteen c) {
		MyConnection.save(c);
	}
	
	public static void updateCanteen(Canteen c) {
		MyConnection.update(c);
	}
	
	public static void deleteCanteen(Canteen c) {
		MyConnection.delete(c);
	}
}
