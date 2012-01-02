package pl.koziolekweb.wjug.guice.base;

import static org.testng.Assert.assertEquals;

import javax.inject.Inject;

import org.testng.annotations.Guice;
import org.testng.annotations.Test;

@Guice(modules = { TestowaRodzinaWładysława.class })
public class WładysławTest {

/*	@Inject
	private Osoba władysław;
*/
	@Inject
	private WładysławFactory factory;
	
	@Test
	public void testWładysław() {
		Władysław władysław = factory.create(System.nanoTime(), 4L);
		assertEquals(władysław.imię(), "Władysław");
		assertEquals(władysław.tata().imię(), "tata");
		System.out.println(((Władysław) władysław).dataUrodzenia());
		System.out.println(((Władysław) władysław).liczbaRąk());
	}

}
