package pl.koziolekweb.wjug.guice.base;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Application {
	public static void main(String[] args) {
		Injector createInjector = Guice.createInjector(new Rodzina());
		Osoba przedstawicielRodziny = createInjector.getInstance(Osoba.class);
		System.out.println(przedstawicielRodziny.imię());
		System.out.println(przedstawicielRodziny.tata().imię());
		System.out.println(przedstawicielRodziny.mama().imię());
	}
}
