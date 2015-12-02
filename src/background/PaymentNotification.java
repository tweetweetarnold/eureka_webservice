package background;


import org.quartz.*;
import org.slf4j.*;

public class PaymentNotification implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {

			// Do all my stuff here
			System.out.println("http://www.splinter.com.au/using-quartz-scheduler-in-a-java-web-app-serv/");

		} catch (Exception ex) {
			LoggerFactory.getLogger(getClass()).error(ex.getMessage()+"HELLO");
		}
	}
}
