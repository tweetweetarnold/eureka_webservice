package controller;

import model.Company;
import model.OrderWindow;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import dao.OrderWindowDAO;

public class OrderWindowController {

	public void createNewOrderWindow(DateTime startDate, DateTime endDate, Company company) {
		OrderWindowDAO.saveOrderWindow(new OrderWindow(startDate, endDate, company));
	}

	public void createNewOrderWindow(DateTime startDate, Duration duration, Company company) {
		OrderWindowDAO.saveOrderWindow(new OrderWindow(startDate, duration, company));
	}
}
