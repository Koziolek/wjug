package pl.koziolekweb.warsjava2011;

import java.io.File;
import java.io.PrintStream;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class App {

	public static void main(String[] args) throws Exception {

		System.setOut(new PrintStream(new File("/dev/null")));
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = schedulerFactory.getScheduler();
		scheduler.start();
		JobDetail jobDetail = new JobDetail("JMXTomcatMemoryJob",
				"JMXTomcatMemoryJob", PingJMXJob.class);
		Trigger trigger = new CronTrigger("JMXTomcatMemoryTrigger",
				"JMXTomcatMemoryJob", "* * * * * ?");
		scheduler.scheduleJob(jobDetail, trigger);

	}

}
