package pl.koziolekweb.devbeer.guice.jsr303;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.validation.Configuration;
import javax.validation.MessageInterpolator;
import javax.validation.Validation;
import javax.validation.Validator;

public class ValidatorProvider implements Provider<Validator> {

	@Inject
	private ConstraintValidatorFactoryWrapperFactory constraintValidatorFactoryWrapperFactory;

	@com.google.inject.Inject(optional = true)
	private MessageInterpolator messageInterpolator;

	/*
	 * @Inject private Provider<TraversableResolver>
	 * traversableResolverProvider;
	 */
	public Validator get() {
		Configuration<?> configure = Validation.byDefaultProvider().configure();
		configure
				.constraintValidatorFactory(constraintValidatorFactoryWrapperFactory
						.create(configure
								.getDefaultConstraintValidatorFactory()));
		if (messageInterpolator != null)
			configure.messageInterpolator(messageInterpolator);
		/*
		 * configure.traversableResolver(traversableResolverProvider.get());
		 */return configure.buildValidatorFactory().getValidator();
	}

}
