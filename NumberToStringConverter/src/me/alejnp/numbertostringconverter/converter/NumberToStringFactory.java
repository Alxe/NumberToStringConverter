package me.alejnp.numbertostringconverter.converter;

import java.util.HashMap;
import java.util.Map;

public class NumberToStringFactory {
	public final static class Language {
		public final String ID, NAME;
		
		public Language(String id, String name) {
			ID = id;
			NAME = name;
		}
	}
	
	private static Map<Language, NumberToStringConverter> converters;
	
	public static NumberToStringConverter getConverter(String language) {	
		return get(language);
	}
	
	private static NumberToStringConverter get(String language) {
		if(converters == null) { initializeMap(); }
		
		
		return null;
	}

	private static void initializeMap() {
		converters = new HashMap<>();
		
		// Loading from XML file.
		// Language[] = ConfigLoader.getLanguages();
		
		
	}
}