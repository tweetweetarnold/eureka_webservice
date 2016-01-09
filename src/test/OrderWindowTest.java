package test;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import connection.MyConnection;
import controller.OrderWindowController;
import model.Canteen;
import model.Company;
import model.OrderWindow;

public class OrderWindowTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Company company2 = new Company("Eastman Chemicals", null, null, "ECM901");
		Canteen canteen2 = new Canteen("Taman Jurong Market and Food Centre",
				"3 Yung Sheng Rd, Singapore 618499", null);
		MyConnection.save(canteen2);
		MyConnection.save(company2);
		long durationl = 3600000*4;
		Duration duration = new Duration(durationl);
		//OrderWindow window2 = new OrderWindow(new DateTime(2015, 12, 2, 16, 36, 0), duration, company2, canteen2, 0.0);
		OrderWindowController orderWindowController = new OrderWindowController();
		orderWindowController.createNewOrderindow(new DateTime(2015, 12, 2, 16, 36, 0), new DateTime(2015, 12, 2, 17, 36, 0), company2, canteen2, 0.0,4);
	}

}
