package background;

import java.util.ArrayList;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import connection.MyConnection;
import controller.CompanyController;
import model.Company;
import model.Employee;
import services.SendEmail;

public class ChasePayment implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		try {
			String url = "PLEASE ENTER"; // TODO: this url needs to be updated
			SendEmail emailGen = new SendEmail();

			String subject = "Koh Bus LunchTime Ordering App - Payment Overdue";
			String messageBody = "Dear User,<br><br>"
					+ "Please note that you will not be able to place any new orders until you have cleared your payment!<br><br>"
					+ "<a href=" + url + ">" + url + "</a>" + "<br><br>" + "Regards,<br>"
					+ "Admin<br><br>"
					+ "This is a system-generated email; please DO NOT REPLY to this email.<br>";

			CompanyController companyController = new CompanyController();
			
			ArrayList<Company> companyList = companyController.getAllCompany();
			for(Company c : companyList){
			
	
				ArrayList<Object> objects = new ArrayList<Object>(
						MyConnection.getUsersToChasePayment(c));
				ArrayList<String> emailList = new ArrayList<String>();
	
				// Add employees with outstanding payment (haven pay past due date)
				for (Object o : objects) {
					Employee tempEmployee = (Employee) o;
					String tempEmail = tempEmployee.getEmail();
					emailList.add(tempEmail);
				}
	
				String[] toEmails = new String[emailList.size()];
				toEmails = emailList.toArray(toEmails);
	
				emailGen.sendEmail(subject, messageBody, null, null, toEmails);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}