package background;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

public class NewJob implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException{
		System.out.println("Hello Quartz!LOL");
	}
}
