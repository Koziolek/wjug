package pl.koziolekweb.devbeer.guice.jsr303;

import java.util.Locale;

import javax.inject.Inject;
import javax.validation.MessageInterpolator;

import com.google.inject.assistedinject.Assisted;

/**
 * Extend this class if you want to add some custom interpolations.
 * 
 * @author koziolek
 * 
 */
public class MessageInterpolatorWrapper implements MessageInterpolator {

	private MessageInterpolator defaultInstance;

	@Inject
	public MessageInterpolatorWrapper(
			@Assisted MessageInterpolator defaultInstance) {
		this.defaultInstance = defaultInstance;
	}

	@Override
	public String interpolate(String messageTemplate, Context context) {
		return defaultInstance.interpolate(messageTemplate, context);
	}

	@Override
	public String interpolate(String messageTemplate, Context context,
			Locale locale) {
		return defaultInstance.interpolate(messageTemplate, context, locale);
	}

	protected MessageInterpolator getDefaultInstance() {
		return defaultInstance;
	}

}
