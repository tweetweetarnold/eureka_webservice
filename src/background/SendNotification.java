package background;

import java.util.ArrayList;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.LoggerFactory;

import controller.OrderWindowController;
import dao.EmployeeDAO;
import model.Employee;
import model.OrderWindow;
import services.SendEmail;
import value.StringValues;

public class SendNotification implements Job {
	public static final String ORDERWINDOW_ID = "1";
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		
		OrderWindowController orderWindowController = new OrderWindowController();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		ArrayList<String> emailList = new ArrayList<String>();
		try {
			
			String url = "http://lunchtime.dal.jelastic.vps-host.net/eureka_webservice/pages/login.jsp";
			System.out.println("Sending Payment Notification");
			SendEmail emailGen = new SendEmail();
			emailGen.setMailServerProperties();
			String subject = "Koh Bus LunchTime Ordering App - Order Window Available!";
			String messageBody = "Dear User,<br><br>" + "A new order window is now available!<br><br>" + "<a href="
					+ url + ">" + url + "</a>" + "<br><br>" + "Regards,<br>" + "Admin<br><br>"
					+ "This is a system-generated email; please DO NOT REPLY to this email.<br>";

			JobDataMap data = context.getJobDetail().getJobDataMap();
			String orderWindowId = data.getString(ORDERWINDOW_ID);
			System.out.println("performing quartz functions Order Window ID = " + orderWindowId );

			OrderWindow orderWindow = orderWindowController.getOrderWindow(Integer.parseInt(orderWindowId));
			ArrayList<Employee> employeeList = employeeDAO
					.getAllEmployeesFromCompany(orderWindow.getCompany().getCompanyId());
			for (Employee e : employeeList) {
				if (!e.getStatus().equals(StringValues.EMPLOYEE_SUSPENDED)
						|| !e.getStatus().equals(StringValues.EMPLOYEE_DESTROYED)) {
					emailList.add(e.getEmail());
				}
			}
			String[] toEmails = new String[emailList.size()];
			toEmails = emailList.toArray(toEmails);
			System.out.println("-------------------------------EMAILLIST LENGTH: " + toEmails.length);
			// temporary carbon copy recipients
			if (toEmails.length > 0) {
				String[] ccEmails = { "chris.cheng.2013@sis.smu.edu.sg" };
				emailGen.sendEmailWithCarbonCopy(subject, messageBody, toEmails, ccEmails);
			}

		} catch (Exception ex) {
			LoggerFactory.getLogger(getClass()).error(ex.getMessage() + "HELLO");
		}

	}

}
