package pl.koziolekweb.warsjava2011.tools;

import pl.koziolekweb.warsjava2011.dom.Memory;

public class Logger {

	private static final int MEGABYTE = 1048576;

	public static String logMemoryInfo(Memory memoryInfo, Short limit){
		StringBuilder builder = new StringBuilder(
				"Przekroczono bezpieczny pułap pamięci!\n\rUżywane: ");
		builder.append(memoryInfo.used / MEGABYTE);
		builder.append("MB\n\r");
		builder.append("Maksymalna dostępna pamięć: ");
		builder.append(memoryInfo.max / MEGABYTE);
		builder.append("MB\n\r");
		builder.append("co stanowi: ");
		builder.append(String
				.format("%4.2f", (new Double(memoryInfo.used) / new Double(
						memoryInfo.max)) * 100.));
		builder.append("% całkowitej pamięci.\n\r");
		builder.append("Limit wynosi: ");
		builder.append(limit);
		builder.append("%.\n\r");
		return (builder.toString());
	}
	
}
