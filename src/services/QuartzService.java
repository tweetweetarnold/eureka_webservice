package services;

import static org.quartz.CronScheduleBuilder.dailyAtHourAndMinute;
import static org.quartz.CronScheduleBuilder.weeklyOnDayAndHourAndMinute;
import static org.quartz.JobBuilder.newJob;

import java.util.Date;
import java.util.TimeZone;

import org.quartz.DateBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import static org.quartz.TriggerBuilder.newTrigger;

import background.ChasePayment;
import background.PaymentNotification;
import background.SendNotification;
import background.WeeklySuspension;


public class QuartzService {

	public QuartzService() {

	}
	public void doProcess(){

        try {
            // Grab the Scheduler instance from the Factory 
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            
            // and start it off
            scheduler.start();
            
            // define the job and tie it to our HelloJob class
            JobDetail job1 = newJob(WeeklySuspension.class)
                .withIdentity("job1", "group1")
                .build();
            JobDetail job2 = newJob(PaymentNotification.class)
            	.withIdentity("job2","group1")
            	.build();
            
            JobDetail job3 = newJob(ChasePayment.class)
            		.withIdentity("job3","group1")
                	.build();
            
            
            // Trigger the job to run now, and then repeat every 40 seconds
            Trigger trigger1 = newTrigger()
                .withIdentity("trigger1", "group1")
                .withSchedule(weeklyOnDayAndHourAndMinute(DateBuilder.SUNDAY, 23, 59).inTimeZone(TimeZone.getTimeZone("GMT+8:00")))
                .forJob(job1.getKey())
                .build();
             
            Trigger trigger2 = newTrigger()
            	.withIdentity("trigger2","group1")
            	.withSchedule(weeklyOnDayAndHourAndMinute(DateBuilder.FRIDAY, 9,00).inTimeZone(TimeZone.getTimeZone("GMT+8:00")))
            	.forJob(job2.getKey())
            	.build();
//                .startNow()
//                .withSchedule(simpleSchedule()
//                        .withIntervalInSeconds(5)
//                        .repeatForever())            
//                .build();
            
            Trigger trigger3 = newTrigger()
                	.withIdentity("trigger3","group1")
                	.withSchedule(dailyAtHourAndMinute(7,00).inTimeZone(TimeZone.getTimeZone("GMT+8:00")))
                	.forJob(job3.getKey())
                	.build();
//            		.startNow()
//	                  .withSchedule(simpleSchedule()
//	                  .withIntervalInSeconds(300)
//	                  .repeatForever())            
//	                  .build();
            
            // Tell quartz to schedule the job using our trigger
            scheduler.scheduleJob(job1, trigger1);
            scheduler.scheduleJob(job2, trigger2);
            scheduler.scheduleJob(job3, trigger3);
            
            scheduler.start();
            System.out.println("Quartz Started");
//            try {
//				Thread.sleep(3000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
            
//            scheduler.shutdown();

        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }
	
	//for weekly suspension,payment notificaion on friday and daily chase payment
	
	// for order window notification
	public void setupNewJobAndTrigger(String orderWindowId, Date startTime) throws SchedulerException{
	    Scheduler sched = StdSchedulerFactory.getDefaultScheduler();
	    JobDetail job1 = newJob(SendNotification.class).withIdentity(orderWindowId,"group2").build();
	    job1.getJobDataMap().put(SendNotification.ORDERPERIOD_ID, orderWindowId);
	    SimpleTrigger trigger1 = (SimpleTrigger) newTrigger() 
	    	    .withIdentity(orderWindowId, "group2")
	    	    .startAt(startTime) // some Date 
	    	    .forJob(orderWindowId, "group2") // identify job with name, group strings
	    	    .build();
	    sched.scheduleJob(job1, trigger1);
	    sched.start();
	    System.out.println("Job Scheduled");
	}
	
	
	public void setupNewJobAndTriggerForJobs(String jobType, int day, int hour, int min) throws Exception{
		Scheduler sched = StdSchedulerFactory.getDefaultScheduler();
	    Trigger oldTrigger = null;
	    if(jobType.equals("weeklySuspension")){
	    	oldTrigger = sched.getTrigger(TriggerKey.triggerKey("trigger1", "group1"));
	    }else if(jobType.equals("paymentNotification")){
	    	oldTrigger = sched.getTrigger(TriggerKey.triggerKey("trigger2", "group1"));
	    }else if(jobType.equals("chasePayment")){
	    	oldTrigger = sched.getTrigger(TriggerKey.triggerKey("trigger3", "group1"));
	    }else{
	    	throw new Exception("Error with Job Name");
	    }
	    TriggerBuilder tb = oldTrigger.getTriggerBuilder();
    	Trigger newTrigger = tb.withSchedule(weeklyOnDayAndHourAndMinute(day, hour, min).inTimeZone(TimeZone.getTimeZone("GMT+8:00")))
    		    .build();
	    
	    
    	sched.rescheduleJob(oldTrigger.getKey(), newTrigger);
    	sched.start();
	}
	
	
	

}
