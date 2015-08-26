package dao;

import connection.MyConnection;
import entity.Canteen;

public class CanteenDAO {
	
	public CanteenDAO() {
		
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
