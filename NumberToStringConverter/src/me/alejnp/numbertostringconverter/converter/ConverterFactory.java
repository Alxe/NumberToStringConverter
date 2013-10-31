package me.alejnp.numbertostringconverter.converter;

import java.util.HashMap;
import java.util.List;
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
	
	/**
	 * Convenience but less secure call to {@link #getConverter(Language)}.
	 * @param lang - <code>String</code> corresponding to the ID of the <code>Language</code>.
	 * @return
	 * @throws UnsupportedLanguageException - When the language is not supported, this 
	 */
	public static IConverter getConverter(String lang) throws UnsupportedLanguageException {	
		return getConverter(new Language(lang, lang));
	}
	
	/**
	 * Method that returns a <code>IConverter</code> instance of a given <code>Language lang</code>.
	 * @param lang - 
	 * @return
	 * @throws UnsupportedLanguageException
	 */
	public static IConverter getConverter(Language lang) throws UnsupportedLanguageException {
		if(converters == null) { initializeMap(); }
		if(converters.get(lang) == null) { buildLanguage(lang); }
		
		return converters.get(lang);
	}

	private static void buildLanguage(Language lang) throws UnsupportedLanguageException {
		try {
			Map<Integer,String> map = configLoader.getLanguageMap(lang);
			
			// TODO
//			converters.put(lang, map);
		} catch(ParsingException pe) {
			System.err.println("NumberToStringConverter: Error parsing file, either file was not found or the XMLReader failed");
			pe.printStackTrace();
		}
		
	}

	private static void initializeMap() {
		converters = new HashMap<>();
	 
		try {
			for(Language l : configLoader.getSupportedLanguages()) {
				converters.put(l, null);
			}
			
		} catch (ParsingException e) {
			e.printStackTrace();
		}
	}
}