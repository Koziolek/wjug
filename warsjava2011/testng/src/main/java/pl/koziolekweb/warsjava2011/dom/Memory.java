package pl.koziolekweb.warsjava2011.dom;

public class Memory {

	public static final Memory NULL = new Memory(-1L, -1L, -1L);
	
	public final Long init;
	
	public final Long max;

	public final Long used;

	
	public Memory(Long max, Long init, Long used) {
		super();
		this.max = max;
		this.init = init;
		this.used = used;
	}
}
