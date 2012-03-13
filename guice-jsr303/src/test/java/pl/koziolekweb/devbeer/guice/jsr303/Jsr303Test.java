package pl.koziolekweb.devbeer.guice.jsr303;

import javax.inject.Inject;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;

import static org.testng.Assert.*;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

@Guice(modules = Jsr303.class)
public class Jsr303Test {

	@Inject
	private Validator validator;

	@Test
	public void shouldConfigureBuildValidator() {
		assertNotNull(validator);
		assertEquals(validator.validate(new Person("")).size(), 0);
		assertEquals(validator.validate(new Person(null)).size(), 1);
	}

	class Person {
		@NotNull
		private final String name;

		public Person(String name) {
			super();
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}
	}
}
