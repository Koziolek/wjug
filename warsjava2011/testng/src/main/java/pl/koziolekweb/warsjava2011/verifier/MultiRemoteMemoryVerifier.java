package pl.koziolekweb.warsjava2011.verifier;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

import pl.koziolekweb.warsjava2011.dom.Memory;
import pl.koziolekweb.warsjava2011.tools.JMXMemoryClient;
import pl.koziolekweb.warsjava2011.tools.Logger;
import pl.koziolekweb.warsjava2011.tools.PercentageCalculator;
import pl.koziolekweb.warsjava2011.verifier.ds.HostListDataSource;

public class MultiRemoteMemoryVerifier {

	@Test(dataProviderClass = HostListDataSource.class, dataProvider = "memory")
	public void verifiMemoryState(String host, int port, Short limit)
			throws Exception {
		JMXMemoryClient client = makeJMX(host, port);
		Memory memoryInfo = client.getMemoryInfo();
		client.close();
		Double usedMemoryPercentage = PercentageCalculator.percentageValue(
				memoryInfo.used, memoryInfo.max);
		assertTrue(usedMemoryPercentage < new Double(limit),
				Logger.logMemoryInfo(memoryInfo, limit));
	}

	private static JMXMemoryClient makeJMX(String host, int port)
			throws Exception {
		JMXMemoryClient client = new JMXMemoryClient(host, port);
		client.init();
		return client;
	}
}
