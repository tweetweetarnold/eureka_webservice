package dao;

import connection.MyConnection;
import entity.Hawker;

public class HawkerDAO {
	
	public HawkerDAO() {
		
	}
	
	public static Hawker getHawker(int hawkerId) {
		return (Hawker) MyConnection.get(Hawker.class, hawkerId);
	}
	
	public static void saveHawker(Hawker h) {
		MyConnection.save(h);
	}
	
	public static void updateHawker(Hawker h) {
		MyConnection.update(h);
	}
	
	public static void deleteHawker(Hawker h) {
		MyConnection.delete(h);
	}
}
