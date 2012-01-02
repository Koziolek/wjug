package pl.koziolekweb.wjug.guice.base;

import javax.inject.Inject;

import static org.testng.Assert.*;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

@Guice(modules = { TestowaRodzinaStasia.class })
public class StaśTest {
	@Inject
	private Staś staś;

	@Test
	public void testStaś() {
		assertEquals(staś.imię(), "Staś");
		assertEquals(staś.tata().imię(), "tata");
	}

}
