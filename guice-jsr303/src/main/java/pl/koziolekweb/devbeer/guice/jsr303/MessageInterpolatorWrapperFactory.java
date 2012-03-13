package pl.koziolekweb.devbeer.guice.jsr303;

import javax.validation.MessageInterpolator;


public interface MessageInterpolatorWrapperFactory {

	public MessageInterpolatorWrapper create(MessageInterpolator defaultInstance);
	
}
