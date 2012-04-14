package pl.koziolekweb.warsjava2011.verifier;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import pl.koziolekweb.warsjava2011.dom.Memory;
import pl.koziolekweb.warsjava2011.tools.JMXMemoryClient;
import pl.koziolekweb.warsjava2011.tools.Logger;

public class RemoteMemoryVerifier {

	@Test
	public void verifi() throws Exception {
		JMXMemoryClient client = new JMXMemoryClient("localhost", 9090);
		client.init();
		Memory memoryInfo = client.getMemoryInfo();
		client.close();
		assertTrue(memoryInfo.used <= memoryInfo.max * 0.01,
				Logger.logMemoryInfo(memoryInfo, (short)1));
	}

}
