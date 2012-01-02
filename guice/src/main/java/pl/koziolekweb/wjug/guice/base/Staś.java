package pl.koziolekweb.wjug.guice.base;

import javax.inject.Inject;
import javax.inject.Named;

public class Staś implements Osoba {

	private Osoba tata;

	private Osoba mama;

	@Inject
	public Staś( @Named("tata") Osoba tata,@Mama Osoba mama) {
		super();
		this.tata = tata;
		this.mama = mama;
	}

	@Override
	public String imię() {
		return "Staś";
	}

	@Override
	public Osoba mama() {
		return mama;
	}

	@Override
	public Osoba tata() {
		return tata;
	}

}
