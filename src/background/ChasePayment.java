package background;

import java.util.ArrayList;

import model.Company;
import model.Employee;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.LoggerFactory;

import services.SendEmail;
import connection.MyConnection;
import controller.CompanyController;

public class ChasePayment implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			String url = "PLEASE ENTER";
			SendEmail emailGen = new SendEmail();
			emailGen.setMailServerProperties();
			String subject = "Koh Bus LunchTime Ordering App - Payment Overdue";
			String messageBody = "Dear User,<br><br>"
					+ "Please note that you will not be able to place any new orders until you have cleared your payment!<br><br>"
					+"<a href="
					+ url
					+ ">"
					+ url
					+ "</a>"
					+ "<br><br>"
					+ "Regards,<br>"
					+ "Admin<br><br>"
					+ "This is a system-generated email; please DO NOT REPLY to this email.<br>";
					

			// EmployeeController employeeController = new EmployeeController();
			CompanyController companyController = new CompanyController();
			Company c = companyController.getCompany(2);
			
			ArrayList<Object> objects = new ArrayList<Object>(
					MyConnection.getUsersToChasePayment(c));
			ArrayList<String> emailList = new ArrayList<String>();

			for (Object o : objects) {
				Employee tempEmployee = (Employee) o;
				String tempEmail = tempEmployee.getEmail();
				emailList.add(tempEmail);
				
			}

			String[] toEmails = new String[emailList.size()];
			toEmails = emailList.toArray(toEmails);
			
			//temporary carbon copy recipients
			String[] ccEmails = {"sumon123may@eastman.com", "wch123ow@eastman.com"};
			emailGen.sendEmailWithCarbonCopy(subject, messageBody, toEmails, ccEmails);

		} catch (Exception ex) {
			LoggerFactory.getLogger(getClass()).error(ex.getMessage() + "HELLO");
		}
	}
}