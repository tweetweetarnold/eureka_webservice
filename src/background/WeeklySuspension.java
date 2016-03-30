package background;

import java.util.ArrayList;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import connection.MyConnection;
import controller.CompanyController;
import controller.EmployeeController;
import model.Company;
import model.Employee;
import value.StringValues;

public class WeeklySuspension implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		try {
			EmployeeController employeeController = new EmployeeController();
			CompanyController companyController = new CompanyController();
			ArrayList<Company> companyList = companyController.getAllCompany();
			for(Company c : companyList){
				// retrieve users with outstanding balance
				ArrayList<Object> objects = new ArrayList<Object>(
						MyConnection.getUsersWithOutstandingPaymentFromCompany(c));

				// Update employee status to suspended if there is outstanding balance
				for (Object o : objects) {
					Employee tempEmployee = (Employee) o;
					tempEmployee.setStatus(StringValues.EMPLOYEE_SUSPENDED);
					employeeController.updateEmployee(tempEmployee);
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
