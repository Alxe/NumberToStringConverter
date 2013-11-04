package me.alejnp.ntsc.converter;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	private static List<ConverterFactory> factories;
	
	public static ConverterFactory getFactory(IDataLoader dataLoader) {
		// If the IDataLoader is null, return null.
		if(dataLoader == null) return null;
		
		// If the List is not instanciated, instanciate it.
		if(factories == null) factories = new ArrayList<ConverterFactory>();
		
		// Traverse the list, search for a compatible factory and return it.
		for (ConverterFactory cf : factories) {
			if(cf.dataLoader.equals(dataLoader)) return cf;
		}
		
		// If no compatible factory was found, instanciate it and add it to the list, then return it.
		ConverterFactory cf = new ConverterFactory(dataLoader);
		
		factories.add(cf);
		return cf;
		
	}
	
	/**
	 * Reference to the {@link IDataLoader} needed to retrieve data from the system. This is required and up to the implemented platform to be provided.
	 */
	private final IDataLoader dataLoader;
	
	/**
	 * Mappings of <code>Languages</code> to <code>IConverter</code> instances, making heavy instanciation less stressful to the system.
	 */
	private final Map<Language, IConverter> converters;
	
	private ConverterFactory(IDataLoader dataLoader) {
		this.dataLoader = dataLoader;
		
		converters = createConverterPool();
	}
	
	/**
	 * Convenience but less secure call to {@link #getConverter(Language)}.
	 * @param langId - <code>String</code> corresponding to the ID of the <code>Language</code>.
	 * @return
	 * @throws UnsupportedLanguageException - When the language is not supported, this 
	 */
	public IConverter getConverter(String langId) throws UnsupportedLanguageException {
		Language lang = null;
		
		for(Language l : converters.keySet()) if(l.ID.equals(langId)) { lang = l; break; }
			
		if(lang == null) throw new UnsupportedLanguageException("There is no language for ID " + langId);
		
		return getConverter(new Language(langId, null));
	}
	
	/**
	 * Method that returns a <code>IConverter</code> instance of a given <code>Language lang</code>.
	 * @param lang - 
	 * @return
	 * @throws UnsupportedLanguageException
	 */
	public IConverter getConverter(Language lang) throws UnsupportedLanguageException {	
		if(lang == null) throw new UnsupportedLanguageException("There is no language for ID " + lang);
		
		// If converter is not properly instanciated, instanciates it and saves it in the map.
		if(converters.get(lang) == null) { buildConverterForLanguage(lang); }
	
		// Returns the converter.
		return converters.get(lang);
	}

	/**
	 * Initializes a map, sets it's keys to Languages supported and it's values to null, which will be properly linked later in {@link #buildLanguage(Language)}.
	 */
	private Map<Language, IConverter> createConverterPool() {
		Map<Language, IConverter> converters = new HashMap<Language, IConverter>();
	 
		try {
			for(Language l : dataLoader.getSupportedLanguages()) {
				converters.put(l, null);
			}
			
		} catch (ParsingException e) {
			System.err.println("Couldn't load languages properly.");
			e.printStackTrace();
		}
		
		return converters;
	}
	
	/**
	 * Instantiates the conversor that manages <code>lang</code> Language and adds it to the map.
	 * @param lang - The target Language to build.
	 * @throws UnsupportedLanguageException Thrown if the language is not supported
	 */
	private IConverter buildConverterForLanguage(Language lang) throws UnsupportedLanguageException {
		try {
			Map<Integer, String> map = dataLoader.getLanguageMap(lang);
			
			IConverter conversor = instanciateConverter(lang.CLASS_NAME, map);
			
			converters.put(lang, conversor);
			return conversor;
			
		} catch(ParsingException pe) {
			System.err.println("NumberToStringConverter: Error parsing file, either file was not found or the XMLReader failed");
			pe.printStackTrace();
		}
		
		return null;
	}

	/**
	 * Instanciates a new Converter from class <code>className</code>, with <code>values</code> associated to it.
	 * @param className - The class name of the {@link IConverter} to instanciate.
	 * @param values - The values that will be passed to the {@link IConverter} object.
	 * @return
	 */
	private IConverter instanciateConverter(String className, Map<Integer, String> values) {
		try {
			IConverter converter;
			
			// Obtain the class of the desired object.
			Class<?> clazz = Class.forName(className);
			
			// Get the type of the values map we will send into the constructor.
			Class<?> type = values.getClass();
			
			// Grab the constructor of the class we want to instanciate.
			Constructor<?> constr = clazz.getDeclaredConstructor(type);
			
			// Finish by instanciating the converter.
			converter = (IConverter) constr.newInstance(values);
			
			return converter;
		} catch(ReflectiveOperationException e) {
			System.err.println("Failed at instanciating the converter, blankConverter will be returned.");
			e.printStackTrace();
		}
		
		return null;
	}
}