package background;


import java.util.ArrayList;

import org.quartz.*;
import org.slf4j.*;

import connection.MyConnection;
import controller.UserController;
import model.Employee;
import services.EmailGenerator;

public class PaymentNotification implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			EmailGenerator emailGen = new EmailGenerator();
			String subject = "Dabao Weekly Payment Due";
			String messageBody = "Hey there! \nPlease note that your weekly payment for this week is due!\n\nRegards,\nDabao";
			
			
			UserController employeeController = new UserController();
			ArrayList<Object> objects = new ArrayList<Object>(MyConnection.getUsersWithOutstandingPaymentFromCompany(1));
			ArrayList<String> emailList = new ArrayList<String>();
			for(Object o: objects){
				Employee tempEmployee = (Employee)o;
				String tempEmail = tempEmployee.getEmail();
				emailList.add(tempEmail);
			}
			String[] toEmails = new String[emailList.size()];
			toEmails = emailList.toArray(toEmails);
			emailGen.sendEmail(subject, messageBody, toEmails);
		} catch (Exception ex) {
			LoggerFactory.getLogger(getClass()).error(ex.getMessage()+"HELLO");
		}
	}
}
