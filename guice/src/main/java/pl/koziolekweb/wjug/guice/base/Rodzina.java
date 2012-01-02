package pl.koziolekweb.wjug.guice.base;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class Rodzina extends AbstractModule {

	@Override
	protected void configure() {
		bind(Osoba.class).to(Jaś.class);
		bind(Osoba.class).annotatedWith(Mama.class).toInstance(new Osoba() {

			@Override
			public String imię() {
				return "mama";
			}

			@Override
			public Osoba mama() {
				return null;
			}

			@Override
			public Osoba tata() {
				return null;
			}
		});
		bind(Osoba.class).annotatedWith(Names.named("tata")).toProvider(
				TataProveder.class);
	}

}
