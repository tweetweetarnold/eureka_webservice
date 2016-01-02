package test;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import background.HelloJob;
import background.NewJob;

public class QuartzExample {

	public void runThis() throws SchedulerException{
		// TODO Auto-generated method stub
		JobDetail job = JobBuilder.newJob(NewJob.class)
				.withIdentity("dummyJobName", "group1").build();
		
		Trigger trigger = TriggerBuilder 
				.newTrigger()
				.withIdentity("dummyTriggerName", "group1")
				.withSchedule(
					SimpleScheduleBuilder.simpleSchedule()
						.withIntervalInSeconds(5).repeatForever())
				.build();

		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
		scheduler.start();
		scheduler.scheduleJob(job, trigger);
		
	}

}
