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

public class PaymentNotification implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			String url = "PLEASE ENTER";
			System.out.println("Sending Payment Notification");
			SendEmail emailGen = new SendEmail();
			emailGen.setMailServerProperties();
			String subject = "Koh Bus LunchTime Ordering App - Payment Due";
			String messageBody = "Dear User,<br><br>"
					+ "Please note that your weekly payment is due!<br><br>"
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
					MyConnection.getUsersWithOutstandingPaymentFromCompany(c));
			ArrayList<String> emailList = new ArrayList<String>();

			for (Object o : objects) {
				Employee tempEmployee = (Employee) o;
				String tempEmail = tempEmployee.getEmail();
				emailList.add(tempEmail);
			}
			String[] toEmails = new String[emailList.size()];
			toEmails = emailList.toArray(toEmails);
			System.out.println("-------------------------------EMAILLIST LENGTH: "+ toEmails.length);
			//temporary carbon copy recipients
			if(toEmails.length>0){
				String[] ccEmails = {"chris.cheng.2013@sis.smu.edu.sg", "wch123ow@eastman.com"};
				emailGen.sendEmailWithCarbonCopy(subject, messageBody, toEmails, ccEmails);
			}
			
		} catch (Exception ex) {
			LoggerFactory.getLogger(getClass()).error(ex.getMessage() + "HELLO");
		}
	}
}
