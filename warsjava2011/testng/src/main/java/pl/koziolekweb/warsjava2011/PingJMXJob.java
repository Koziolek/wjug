package pl.koziolekweb.warsjava2011;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.testng.TestNG;

import pl.koziolekweb.warsjava2011.reporters.MemoryVerificationListener;
import pl.koziolekweb.warsjava2011.verifier.MultiRemoteMemoryVerifier;

public class PingJMXJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		TestNG testNG = new TestNG();
		testNG.addListener(new MemoryVerificationListener());
		testNG.setTestClasses(new Class[] { MultiRemoteMemoryVerifier.class });
		testNG.run();
	}

}
