package dao;

import model.OrderWindow;
import connection.MyConnection;

public class OrderWindowDAO {

	public OrderWindowDAO() {
	}

	public static OrderWindow getOrderWindow(int orderWindowId) {
		return (OrderWindow) MyConnection.get(OrderWindow.class, orderWindowId);
	}

	// Save new OrderWindow to the DB
	public static void saveOrderWindow(OrderWindow w) {
		MyConnection.save(w);
	}

	// Update existing Company in the DB
	public static void updateCompany(OrderWindow w) {
		MyConnection.update(w);
	}

	// Delete Company from the DB
	public static void deleteCompany(OrderWindow w) {
		MyConnection.delete(w);
	}
}
