package background;

import java.util.ArrayList;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import controller.OrderPeriodController;
import dao.EmployeeDAO;
import model.Employee;
import model.OrderPeriod;
import services.SendEmail;
import value.StringValues;

public class SendNotification implements Job {
	public static final String ORDERPERIOD_ID = "1";

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		OrderPeriodController orderPeriodController = new OrderPeriodController();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		ArrayList<String> emailList = new ArrayList<String>();

		try {
			String url = StringValues.DEFAULT_URL;
			System.out.println("Sending Payment Notification");

			SendEmail emailGen = new SendEmail();

			String subject = "Koh Bus LunchTime Ordering App - Order Period Available!";
			String messageBody = "Dear User,<br><br>"
					+ "A new order period is now available!<br><br>" + "<a href=" + url + ">" + url
					+ "</a>" + "<br><br>" + "Regards,<br>" + "Admin<br><br>"
					+ "This is a system-generated email; please DO NOT REPLY to this email.<br>";

			JobDataMap data = context.getJobDetail().getJobDataMap();
			String orderPeriodId = data.getString(ORDERPERIOD_ID);
			System.out.println("performing quartz functions Order Period ID = " + orderPeriodId);

			OrderPeriod orderPeriod = orderPeriodController
					.getOrderPeriod(Integer.parseInt(orderPeriodId));

			ArrayList<Employee> employeeList = employeeDAO
					.getAllEmployeesFromCompany(orderPeriod.getCompany().getCompanyId());

			// Add employees to email list
			for (Employee e : employeeList) {
				if (!e.getStatus().equals(StringValues.EMPLOYEE_SUSPENDED)
						|| !e.getStatus().equals(StringValues.EMPLOYEE_DESTROYED)) {
					emailList.add(e.getEmail());
				}
			}
			String[] toEmails = new String[emailList.size()];
			toEmails = emailList.toArray(toEmails);

			System.out.println("Number of recipients: " + toEmails.length);

			if (toEmails.length > 0) {
				emailGen.sendEmail(subject, messageBody, null, null, toEmails);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
