package pl.koziolekweb.warsjava2011.verifier.ds;

import org.testng.annotations.DataProvider;

public class HostListDataSource {

	@DataProvider(name="memory")
	public static Object[][] getHostLIstForMemoryTest() throws Exception {
		
		Object[][] hostsAndLimits = new Object[][] { new Object[] {
				"localhost", 9090, (short) 1 }
		/*,new Object[] {
				"192.168.1.103", 9090, (short) 1 }*/};

		return hostsAndLimits;
	}

	

}
