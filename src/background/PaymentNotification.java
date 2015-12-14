package background;

import java.util.ArrayList;

import model.Company;
import model.Employee;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.LoggerFactory;

import services.EmailGenerator;
import connection.MyConnection;
import controller.CompanyController;

public class PaymentNotification implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			EmailGenerator emailGen = new EmailGenerator();
			String subject = "Dabao Weekly Payment Due";
			String messageBody = "Hey there! \nPlease note that your weekly payment for this week is due!\n\nRegards,\nDabao";

			// EmployeeController employeeController = new EmployeeController();
			CompanyController companyController = new CompanyController();
			Company c = companyController.getCompany(1);
			ArrayList<Object> objects = new ArrayList<Object>(
					MyConnection.getUsersWithOutstandingPaymentFromCompany(c));
			ArrayList<String> emailList = new ArrayList<String>();
			for (Object o : objects) {
				Employee tempEmployee = (Employee) o;
				String tempEmail = tempEmployee.getEmail();
				emailList.add(tempEmail);
			}
			String[] toEmails = new String[emailList.size()];
			toEmails = emailList.toArray(toEmails);
			emailGen.sendEmail(subject, messageBody, toEmails);
		} catch (Exception ex) {
			LoggerFactory.getLogger(getClass()).error(ex.getMessage() + "HELLO");
		}
	}
}
