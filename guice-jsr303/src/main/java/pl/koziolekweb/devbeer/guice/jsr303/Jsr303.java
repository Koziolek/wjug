package pl.koziolekweb.devbeer.guice.jsr303;

import javax.validation.Configuration;
import javax.validation.Validation;
import javax.validation.Validator;

import com.google.inject.AbstractModule;

public class Jsr303 extends AbstractModule {

	protected void configure() {
		bind(Validator.class).toProvider((Class)null);
		
		Configuration configure = Validation.byDefaultProvider().configure();
		configure.constraintValidatorFactory(null).messageInterpolator(null).traversableResolver(null);
	}

}
