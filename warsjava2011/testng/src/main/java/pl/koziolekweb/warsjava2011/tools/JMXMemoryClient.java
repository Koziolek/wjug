package pl.koziolekweb.warsjava2011.tools;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.openmbean.CompositeData;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import pl.koziolekweb.warsjava2011.dom.Memory;

public class JMXMemoryClient {

	private final String host;
	private final Integer port;
	private JMXConnector jmxConnection;
	private MBeanServerConnection mBeanServerConnection;
	private ObjectName memoryMBeanName;
	private boolean init = false;

	public JMXMemoryClient(String host, Integer port) {
		this.host = host;
		this.port = port;
	}

	public synchronized void init() throws Exception {
		JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://"
				+ host + ":" + port + "/jmxrmi");
		jmxConnection = JMXConnectorFactory.connect(url, null);
		mBeanServerConnection = jmxConnection.getMBeanServerConnection();
		memoryMBeanName = new ObjectName("java.lang:type=Memory");
		this.init = true;
	}

	public synchronized void close() throws Exception {
		jmxConnection.close();
		this.init = false;
	}

	public Memory getMemoryInfo() throws Exception {
		if (!init)
			throw new IllegalStateException("Nie wywołano init");
		CompositeData attribute = getHeapMemoryUsage(mBeanServerConnection,
				memoryMBeanName);
		Long used = (Long) attribute.get("used");
		Long max = (Long) attribute.get("max");
		Long init = (Long) attribute.get("init");
		return new Memory(max, init, used);
	}

	private static CompositeData getHeapMemoryUsage(
			MBeanServerConnection mBeanServerConnection,
			ObjectName memoryMBeanName) throws Exception {
		CompositeData attribute = (CompositeData) mBeanServerConnection
				.getAttribute(memoryMBeanName, "HeapMemoryUsage");
		return attribute;
	}

}
