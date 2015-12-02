package controller;

import model.Canteen;
import model.Company;
import model.OrderWindow;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import dao.OrderWindowDAO;

public class OrderWindowController {

	public void createNewOrderWindow(DateTime startDate, DateTime endDate, Company company, Canteen canteen) {
		OrderWindowDAO.saveOrderWindow(new OrderWindow(startDate, endDate, company, canteen));
	}

	public void createNewOrderWindow(DateTime startDate, Duration duration, Company company, Canteen canteen) {
		OrderWindowDAO.saveOrderWindow(new OrderWindow(startDate, duration, company, canteen));
	}
}
