package me.alejnp.ntsc.converter;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import me.alejnp.ntsc.exception.ParsingException;
import me.alejnp.ntsc.exception.UnsupportedLanguageException;
import me.alejnp.ntsc.interfaces.IConverter;
import me.alejnp.ntsc.interfaces.IDataLoader;
import me.alejnp.ntsc.locale.Language;

/**
 * Factory class that provides instances of Converters.
 * @author Alej. Núñez Pérez
 *
 */
public class ConverterFactory {
	/**
	 * Reference to the ConfigLoader needed to retrieve data from the system. This is required and up to the implemented platform to be provided.
	 */
	private static IDataLoader configLoader;
	
	/**
	 * Mappings of <code>Languages</code> to <code>IConverter</code> instances, making heavy instanciation less stressful to the system.
	 */
	private static Map<Language, IConverter> converters;
	
	/**
	 * Setter for the ConfigLoader field. See the {@link configLoader} field.
	 * @param configLoader - The new ConfigLoader.
	 */
	public static void setConfigLoader(IDataLoader configLoader) {
		ConverterFactory.configLoader = configLoader;
	}
	
	/**
	 * Convenience but less secure call to {@link #getConverter(Language)}.
	 * @param langId - <code>String</code> corresponding to the ID of the <code>Language</code>.
	 * @return
	 * @throws UnsupportedLanguageException - When the language is not supported, this 
	 */
	public static IConverter getConverter(String langId) throws UnsupportedLanguageException {	
		return getConverter(new Language(langId, null));
	}
	
	/**
	 * Method that returns a <code>IConverter</code> instance of a given <code>Language lang</code>.
	 * @param lang - 
	 * @return
	 * @throws UnsupportedLanguageException
	 */
	public static IConverter getConverter(Language lang) throws UnsupportedLanguageException {
		if(converters == null) { initMapping(); }
		
		// Check if provided Language has full name set. If not, tries to figure it, if does, skips this block.
		if(lang.NAME == null || lang.NAME.equals(lang.ID)) {
			for(Language l : converters.keySet()) {
				if(lang.ID == l.ID) { lang = l; break; }
			}
		}
		
		// If converter is not properly instanciated, instanciates it and saves it in the map.
		if(converters.get(lang) == null) { buildLanguage(lang); }
	
		// Returns the converter.
		return converters.get(lang);
	}

	/**
	 * Initializes the map and sets the keys to null, which will be properly linked later in {@link #buildLanguage(Language)}.
	 */
	private static void initMapping() {
		converters = new HashMap<>();
	 
		try {
			for(Language l : configLoader.getSupportedLanguages()) {
				converters.put(l, null);
			}
			
		} catch (ParsingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Instantiates the conversor that manages <code>lang</code> Language and adds it to the map.
	 * @param lang - The target Language to build.
	 * @throws UnsupportedLanguageException Thrown if the language is not supported
	 */
	private static void buildLanguage(Language lang) throws UnsupportedLanguageException {
		try {
			Map<Integer, String> map = configLoader.getLanguageMap(lang);
			
			// Search for the class that implemenents the Language via Reflection trickery.
			String packageName = "me.alejnp.numbertostringconverter.converter.",
					className = (Character.toUpperCase(lang.NAME.charAt(0)) + lang.NAME.substring(1)) + "Converter";
			
			IConverter conversor = buildConverter(packageName + className, map);
			
			converters.put(lang, conversor);
			
		} catch(ParsingException pe) {
			System.err.println("NumberToStringConverter: Error parsing file, either file was not found or the XMLReader failed");
			pe.printStackTrace();
		}
		
	}

	private static IConverter buildConverter(String name, Map<Integer, String> map) {
		try {
			IConverter converter;
			
			Constructor<?> constr = Class.forName(name).getConstructor(Map.class);
			converter = (IConverter) constr.newInstance(map);
			
			return converter;
		} catch(ReflectiveOperationException e) {
			e.printStackTrace();
			
			return null;
		}
	}
}