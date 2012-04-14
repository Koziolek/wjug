package pl.koziolekweb.warsjava2011.reporters;

import java.util.Date;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class MemoryVerificationListener extends TestListenerAdapter {

	@Override
	public void onTestFailure(ITestResult result) {
		Object[] parameters = result.getParameters();
		if (parameters.length == 0) {
			System.err.printf("Wielkosc tablicy %d. Błąd: %s \n\r",
					parameters.length, result.getThrowable().getMessage());
			result.getThrowable().printStackTrace();
			return;
		}
		Object host = parameters[0];
		Object limit = parameters[2];
		Date now = new Date();
		System.err
				.printf("[%tT %td %tb %tY] Na serwerze %s, przekroczono limit pamięci. Limit ten to %d procent. Komunikat o błędzie: %s\n\r",
						now,  now,  now, now, host, limit, result.getThrowable().getMessage());

	}
}
