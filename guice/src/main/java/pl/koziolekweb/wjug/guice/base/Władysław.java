package pl.koziolekweb.wjug.guice.base;

import javax.inject.Inject;
import javax.inject.Named;

import com.google.inject.assistedinject.Assisted;

public class Władysław implements Osoba {

	private Osoba tata;

	private Osoba mama;

	@Inject
	@Named("Władysław")
	private String imię;

	private Long dataUrodzenia;

	private Long liczbaRąk;

	@Inject
	public Władysław(@Named("tata") Osoba tata, @Mama Osoba mama,
			@Assisted("dataUrodzenia") Long dataUrodzenia,
			@Assisted("liczbaRąk") Long liczbaRąk) {
		super();
		this.tata = tata;
		this.mama = mama;
		this.dataUrodzenia = dataUrodzenia;
		this.liczbaRąk = liczbaRąk;
	}

	public Long liczbaRąk() {
		return liczbaRąk;
	}

	@Override
	public String imię() {
		return imię;
	}

	@Override
	public Osoba mama() {
		return mama;
	}

	@Override
	public Osoba tata() {
		return tata;
	}

	public Long dataUrodzenia() {
		return dataUrodzenia;
	}
}
