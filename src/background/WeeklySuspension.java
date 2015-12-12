package background;

import java.util.ArrayList;
import java.util.List;

import org.quartz.*;
import org.slf4j.*;
import model.Employee;


import connection.MyConnection;
import controller.UserController;

public class WeeklySuspension implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			// Do all my stuff here
			UserController employeeController = new UserController();
			ArrayList<Object> objects = new ArrayList<Object>(MyConnection.getUsersWithOutstandingPaymentFromCompany(1));
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
