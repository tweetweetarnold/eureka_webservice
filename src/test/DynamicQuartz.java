package test;

import org.quartz.ScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;

public class DynamicQuartz {
	SchedulerFactory schedulerFactory = 
	JobKey jobKey = reportIdToRun; //
	JobDetail jobDetail= newJob(MyJob.class).withIdentity(jobKey).build();
	Trigger trigger= newTrigger().withIdentity(triggerKey).withSchedule(cronSchedule(cronExpression)).startAt(startDate).endAt(endDate).build()
	scheduler.scheduleJob(jobDetail, trigger);
}
