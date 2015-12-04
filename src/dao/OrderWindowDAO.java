package dao;

import model.OrderWindow;
import connection.MyConnection;

public class OrderWindowDAO {

	public OrderWindow getOrderWindow(int orderWindowId) {
		return (OrderWindow) MyConnection.get(OrderWindow.class, orderWindowId);
	}

	// Save new OrderWindow to the DB
	public void saveOrderWindow(OrderWindow w) {
		MyConnection.save(w);
	}

	// Update existing Company in the DB
	public void updateCompany(OrderWindow w) {
		MyConnection.update(w);
	}

	// Delete Company from the DB
	public void deleteCompany(OrderWindow w) {
		MyConnection.delete(w);
	}
}
