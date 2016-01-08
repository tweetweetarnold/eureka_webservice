package services;

import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.SimpleTriggerImpl;


import background.HelloJob;


public class Quartz {
	
	public void addRepeatingJob(String jobIdentity, String triggerIdentity, String cron, Class newClass) throws SchedulerException{
		// TODO Auto-generated method stub
		JobDetail job = JobBuilder.newJob(newClass)
				.withIdentity(jobIdentity, "group1").build();
		
		Trigger trigger = TriggerBuilder 
				.newTrigger()
				.withIdentity(triggerIdentity, "group1")
				.withSchedule(
						CronScheduleBuilder.cronSchedule(cron))
				.build();

		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
		scheduler.start();
		scheduler.scheduleJob(job, trigger);
		
	}
	
	public void addClosingDate(String jobIdentity, String triggerIdentity, Date date, String id) throws SchedulerException, InterruptedException{
		// TODO Auto-generated method stub
		JobDetail job = JobBuilder.newJob(HelloJob.class)
				.withIdentity(jobIdentity, "group1").build();
		Date after = new Date(date.getTime()+10*300);
		SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger().withIdentity("rahimtrigger", "group1")
			    .startAt(date).build();
				
				
		System.out.println(date + "Here");
		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
//		job.getJobDataMap().put(CloseWindow.ID, id);
		scheduler.scheduleJob(job, trigger);
		scheduler.start();
		try {
			//wait for 30 seconds to finish the job
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
		System.out.println("Here");
	}
	
}
