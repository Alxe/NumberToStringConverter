package me.alejnp.numbertostringconverter.converter;

import java.util.HashMap;
import java.util.Map;

import me.alejnp.numbertostringconverter.exception.ParsingException;
import me.alejnp.numbertostringconverter.exception.UnsupportedLanguageException;
import me.alejnp.numbertostringconverter.interfaces.IConfigLoader;
import me.alejnp.numbertostringconverter.interfaces.IConverter;
import me.alejnp.numbertostringconverter.locale.Language;

/**
 * Factory class that provides instances of Converters.
 * @author Alej. Núñez Pérez
 *
 */
public class ConverterFactory {
	/**
	 * Reference to the ConfigLoader needed to retrieve data from the system. This is required and up to the implemented platform to be provided.
	 */
	private static IConfigLoader configLoader;
	
	/**
	 * Mappings of <code>Languages</code> to <code>IConverter</code> instances, making heavy instanciation less stressful to the system.
	 */
	private static Map<Language, IConverter> converters;
	
	/**
	 * Setter for the ConfigLoader field. See the {@link configLoader} field.
	 * @param configLoader - The new ConfigLoader.
	 */
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
			
			// TODO
//			converters.put(lang, map);
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