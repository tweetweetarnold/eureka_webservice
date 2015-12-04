package controller;

import java.util.List;

import model.Canteen;
import model.Company;
import model.Employee;
import model.OrderWindow;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;
import org.joda.time.Duration;

import connection.MyConnection;
import dao.OrderWindowDAO;

public class OrderWindowController {

	public static void createNewOrderindow(DateTime startDate, DateTime endDate, Company company,
			Canteen canteen) {
		OrderWindowDAO.saveOrderWindow(new OrderWindow(startDate, endDate, company, canteen));
	}

	public static void createNewOrderWindow(DateTime startDate, Duration duration, Company company,
			Canteen canteen) {
		OrderWindowDAO.saveOrderWindow(new OrderWindow(startDate, duration, company, canteen));
	}

	// Check for any active window under given company
	public static boolean checkForActiveWindow(Company company) {
		DetachedCriteria dc = DetachedCriteria.forClass(OrderWindow.class);
		dc.add(Restrictions.eq("companyId", company.getCompanyId()));
//		dc.add(Restrictions.eq("companyId", company.getCompanyId()));
		dc.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

		List<Object> l = MyConnection.queryWithCriteria(dc);

		return true;
	}
}
