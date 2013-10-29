package me.alejnp.converters;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class NumberToStringFactory {
	private static Map<Locale, NumberToStringConverter> converters;
	
	public static NumberToStringConverter getConverter(Locale l) {
		NumberToStringConverter ntsc = get(Locale.US);
		
		
		
		return ntsc;
	}
	
	private static NumberToStringConverter get(Locale l) {
		NumberToStringConverter ntsc;
		
		if(converters == null) initializeMap();	
		
		if((ntsc = converters.get(l)) == null) {
			ntsc = loadConverter(l);
		}
		
		return null;
	}

	private static NumberToStringConverter loadConverter(Locale l) {
		NumberToStringConverter ntsc = null;
		
		return ntsc;
	}

	private static void initializeMap() {
		converters = new HashMap<Locale, NumberToStringConverter>();
	}
}