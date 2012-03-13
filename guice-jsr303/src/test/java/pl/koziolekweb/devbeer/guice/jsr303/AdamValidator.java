package pl.koziolekweb.devbeer.guice.jsr303;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class AdamValidator implements ConstraintValidator<Adam, String> {

	@Mock
	private Object mock;

	@Override
	public void initialize(Adam constraintAnnotation) {
		MockitoAnnotations.initMocks(this);
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return "Adam".equals(value);
	}
	
}