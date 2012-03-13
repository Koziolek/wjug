package pl.koziolekweb.devbeer.guice.jsr303;

import javax.validation.ConstraintValidatorFactory;
import javax.validation.Validator;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class Jsr303 extends AbstractModule {

	protected void configure() {
		bind(Validator.class).toProvider(ValidatorProvider.class);
		install(new FactoryModuleBuilder().implement(
				ConstraintValidatorFactory.class,
				ConstraintValidatorFactoryWrapper.class).build(
				ConstraintValidatorFactoryWrapperFactory.class));

		// bind(ConstraintValidatorFactory.class).toProvider(ConstraintValidatorFactoryProvider.class);
	}

}
