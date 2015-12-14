package background;

import java.util.ArrayList;

import model.Employee;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.LoggerFactory;

import connection.MyConnection;
import controller.EmployeeController;

public class WeeklySuspension implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			// Do all my stuff here
			EmployeeController employeeController = new EmployeeController();
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
