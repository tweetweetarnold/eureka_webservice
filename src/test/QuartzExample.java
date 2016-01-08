package test;

import java.util.Date;

import org.joda.time.DateTime;
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
import services.Quartz;

public class QuartzExample {
	
	public static void main(String[] args){
		Quartz quartz = new Quartz();
		try {
			String windowID = "1";
			DateTime endDate = new DateTime(2016, 1, 7, 14, 58, 30);
			Date date = endDate.toDate();
			quartz.addClosingDate("Hello1", "HelloTrigger", date, windowID);
			quartz.addRepeatingJob("Hello", "HelloTrigger", "0/5 * * * * ?", HelloJob.class);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	
	
	

//	public void runThis() throws SchedulerException{
//		// TODO Auto-generated method stub
//		JobDetail job = JobBuilder.newJob(NewJob.class)
//				.withIdentity("dummyJobName", "group1").build();
//		
//		Trigger trigger = TriggerBuilder 
//				.newTrigger()
//				.withIdentity("dummyTriggerName", "group1")
//				.withSchedule(
//					SimpleScheduleBuilder.simpleSchedule()
//						.withIntervalInSeconds(5).repeatForever())
//				.build();
//
//		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
//		scheduler.start();
//		scheduler.scheduleJob(job, trigger);
//		
//	}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
}
