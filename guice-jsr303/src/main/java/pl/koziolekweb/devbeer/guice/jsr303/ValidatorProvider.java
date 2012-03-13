package pl.koziolekweb.devbeer.guice.jsr303;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.validation.Configuration;
import javax.validation.ConstraintValidatorFactory;
import javax.validation.MessageInterpolator;
import javax.validation.TraversableResolver;
import javax.validation.Validation;
import javax.validation.Validator;

public class ValidatorProvider implements Provider<Validator> {

	@Inject
	private ConstraintValidatorFactoryWrapperFactory constraintValidatorFactoryWrapperFactory;

	/*
	 * @Inject private Provider<MessageInterpolator> interpolatorProvider;
	 * 
	 * @Inject private Provider<TraversableResolver>
	 * traversableResolverProvider;
	 */
	public Validator get() {
		Configuration<?> configure = Validation.byDefaultProvider().configure();
		configure.constraintValidatorFactory(constraintValidatorFactoryWrapperFactory
				.create(configure.getDefaultConstraintValidatorFactory()));
		/*
		 * configure.messageInterpolator(interpolatorProvider.get());
		 * configure.traversableResolver(traversableResolverProvider.get());
		 */return configure.buildValidatorFactory().getValidator();
	}

}
