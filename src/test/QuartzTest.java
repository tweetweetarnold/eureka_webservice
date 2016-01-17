package test;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;

import java.util.TimeZone;

import static org.quartz.CronScheduleBuilder.*;

import org.quartz.DateBuilder;


import background.PaymentNotification;
import background.WeeklySuspension;
import background.ChasePayment;

import org.quartz.JobDetail;




public class QuartzTest {

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
}