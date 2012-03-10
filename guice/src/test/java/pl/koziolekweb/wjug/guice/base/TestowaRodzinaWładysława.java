package pl.koziolekweb.wjug.guice.base;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.name.Names;

public class TestowaRodzinaWładysława extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(Osoba.class,
				Władysław.class).build(WładysławFactory.class));
		bind(String.class).annotatedWith(Names.named("Władysław")).toInstance(
				"Władysław");bind(Osoba.class).to(Władysław.class);
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
