package pl.koziolekweb.devbeer.guice.jsr303;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorFactory;

import com.google.inject.Injector;
import com.google.inject.assistedinject.Assisted;

public class ConstraintValidatorFactoryWrapper implements
		ConstraintValidatorFactory {

	@SuppressWarnings("unused")
	private ConstraintValidatorFactory defaultInstance;
	
	@Inject
	private Injector injector;

	@Inject
	public ConstraintValidatorFactoryWrapper(
			@Assisted ConstraintValidatorFactory defaultInstance) {
		this.defaultInstance = defaultInstance;
	}

	@Override
	public <T extends ConstraintValidator<?, ?>> T getInstance(Class<T> key) {
		return injector.getInstance(key);
	}

}
