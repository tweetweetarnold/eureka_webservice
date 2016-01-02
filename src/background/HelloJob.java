package background;

import java.util.Date;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

public class HelloJob implements Job {

	public void execute(JobExecutionContext context) throws JobExecutionException{
		System.out.println("Hello Quartz!");
		
		Scheduler scheduler;
		try {
			scheduler = new StdSchedulerFactory().getScheduler();
		
		for (String groupName : scheduler.getJobGroupNames()) {

		     for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
						
			  String jobName = jobKey.getName();
			  String jobGroup = jobKey.getGroup();
						
			  //get job's trigger
			  List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
			  Date nextFireTime = triggers.get(0).getNextFireTime(); 

				System.out.println("[jobName] : " + jobName + " [groupName] : "
					+ jobGroup + " - " + nextFireTime);

			  }

		    }
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
