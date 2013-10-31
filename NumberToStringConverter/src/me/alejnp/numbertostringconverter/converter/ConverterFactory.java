package me.alejnp.numbertostringconverter.converter;

import java.util.HashMap;
import java.util.Map;

import me.alejnp.numbertostringconverter.exception.ParsingException;
import me.alejnp.numbertostringconverter.exception.UnsupportedLanguageException;
import me.alejnp.numbertostringconverter.interfaces.IConfigLoader;
import me.alejnp.numbertostringconverter.interfaces.IConverter;

public class ConverterFactory {
	public final static class Language {
		public final String ID;
		
		public Language(String id) {
			ID = id;
		}
		
		@Override
		public String toString() {
			return "Language [ID=" + ID + "]";
		}
	}
	
	private static IConfigLoader configLoader;
	
	private static Map<Language, IConverter> converters;
	
	public static void setConfigLoader(IConfigLoader configLoader) {
		ConverterFactory.configLoader = configLoader;
	}
	
	public static IConverter getConverter(String lang) throws UnsupportedLanguageException {	
		return getConverter(new Language(lang));
	}
	
	public static IConverter getConverter(Language lang) throws UnsupportedLanguageException {
		if(converters == null) { initializeMap(); }
		if(converters.get(lang) == null) { buildLanguage(lang); }
		
		return converters.get(lang);
	}

	private static void buildLanguage(Language lang) throws UnsupportedLanguageException {
		try {
			Map<?,?> map = configLoader.getLanguageMap("numbers_" + lang.ID + ".xml");
			
			converters.put(lang, map);
		} catch(ParsingException pe) {
			System.err.println("NumberToStringConverter: Error parsing file, either file was not found or the XMLReader failed");
		}
		
	}

	private static void initializeMap() {
		converters = new HashMap<>();
		
		// Loading from XML file.
		 Language[] langs = configLoader.getSupportedLanguages();
		
		for(Language l : langs) {
			converters.put(l, null);
		}
	}
}