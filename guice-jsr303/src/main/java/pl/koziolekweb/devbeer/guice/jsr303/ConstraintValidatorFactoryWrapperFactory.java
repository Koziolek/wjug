package pl.koziolekweb.devbeer.guice.jsr303;

import javax.validation.ConstraintValidatorFactory;

public interface ConstraintValidatorFactoryWrapperFactory {

	public ConstraintValidatorFactoryWrapper create(ConstraintValidatorFactory defaultInstance);
	
}
