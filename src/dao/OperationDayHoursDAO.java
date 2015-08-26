package dao;

import connection.MyConnection;
import entity.OperationDayHours;

public class OperationDayHoursDAO {
	
	public OperationDayHoursDAO() {
		
	}
	
	public static OperationDayHours getOperationDayHours(int operationDayHoursId) {
		return (OperationDayHours) MyConnection.get(OperationDayHours.class, operationDayHoursId);
	}
	
	public static void saveOperationDayHours(OperationDayHours o) {
		MyConnection.save(o);
	}
	
	public static void updateOperationDayHours(OperationDayHours o) {
		MyConnection.update(o);
	}
	
	public static void deleteOperationDayHours(OperationDayHours o) {
		MyConnection.delete(o);
	}
}
