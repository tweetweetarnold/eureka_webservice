package background;

import java.util.ArrayList;
import java.util.List;

import org.quartz.*;
import org.slf4j.*;

import model.Company;
import model.Employee;


import connection.MyConnection;
import controller.CompanyController;
import controller.UserController;

public class WeeklySuspension implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			// Do all my stuff here
			System.out.println("Doing");
			UserController employeeController = new UserController();
			CompanyController companyController = new CompanyController();
			Company c = companyController.getCompany(1);
			ArrayList<Object> objects = new ArrayList<Object>(MyConnection.getUsersWithOutstandingPaymentFromCompany(c));
			for(Object o: objects){
				Employee tempEmployee = (Employee)o;
				tempEmployee.setStatus("Suspended");
				employeeController.updateEmployee(tempEmployee);
			}

		} catch (Exception ex) {
			LoggerFactory.getLogger(getClass()).error(ex.getMessage() + "HELLO");
		}
	}

}
