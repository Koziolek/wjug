package pl.koziolekweb.wjug.guice.base;

import javax.inject.Named;

import com.google.inject.assistedinject.Assisted;

public interface WładysławFactory {

	@Named("dataUrodzenia")
	public Władysław create(@Assisted("dataUrodzenia") Long dataUrodzenia,
			@Assisted("liczbaRąk") Long liczbaRąk);

}
