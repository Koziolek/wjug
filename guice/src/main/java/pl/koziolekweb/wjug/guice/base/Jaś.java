package pl.koziolekweb.wjug.guice.base;

import javax.inject.Inject;
import javax.inject.Named;

public class Jaś implements Osoba {

	@Inject @Mama
	private Osoba mama;
	@Inject
	@Named("tata")
	private Osoba tata;

	private String imię;

	public Jaś() {
		imię = "Jaś";
	}

	public String imię() {
		return imię;
	}

	public Osoba mama() {
		return mama;
	}

	public Osoba tata() {
		return tata;
	}

}
