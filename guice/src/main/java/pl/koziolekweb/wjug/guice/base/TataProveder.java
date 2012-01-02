package pl.koziolekweb.wjug.guice.base;

import com.google.inject.Provider;

public class TataProveder implements Provider<Osoba> {

	@Override
	public Osoba get() {
		return new Osoba() {

			@Override
			public String imię() {
				return "tata";
			}

			@Override
			public Osoba mama() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Osoba tata() {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}

}
