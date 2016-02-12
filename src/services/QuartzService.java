package services;

import static org.quartz.JobBuilder.newJob;

import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;
import static org.quartz.TriggerBuilder.newTrigger;

import background.SendNotification;


public class QuartzService {

	public QuartzService() {

	}

	public void setupNewJobAndTrigger(String orderWindowId, Date startTime) throws SchedulerException{
	    Scheduler sched = StdSchedulerFactory.getDefaultScheduler();
	    JobDetail job1 = newJob(SendNotification.class).withIdentity(orderWindowId,"group2").build();
	    job1.getJobDataMap().put(SendNotification.ORDERWINDOW_ID, orderWindowId);
	    SimpleTrigger trigger1 = (SimpleTrigger) newTrigger() 
	    	    .withIdentity(orderWindowId, "group2")
	    	    .startAt(startTime) // some Date 
	    	    .forJob(orderWindowId, "group2") // identify job with name, group strings
	    	    .build();
	    sched.scheduleJob(job1, trigger1);
	    sched.start();
	    System.out.println("Job Scheduled");
	}

}
