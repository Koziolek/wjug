package pl.koziolekweb.wjug.guice.base;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

public class JaśTest {

	@Test
	public void imięJasiaPowinnoZwrócićJaś() {
		assertEquals("Jaś", new Jaś().imię());
	}

}
