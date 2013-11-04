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
import me.alejnp.ntsc.locale.LangData;

/**
 * Factory class that provides instances of Converters.
 * @author Alej. Núñez Pérez
 *
 */
public class ConverterFactory {
	/**
	 * Blank instance of {@link AbstractConverter}, useful to return this instead of null.
	 */
	protected static final IConverter blankConverter = new AbstractConverter(null) {
		@Override
		public String convert(int number) {
			// Do nothing, return empty string.
			
			return "";
		}
	};
	
	private static List<ConverterFactory> factories;
	
	public static ConverterFactory getFactory(IDataLoader dataLoader) {
		// If the DataLoader is null, return null.
		if(dataLoader == null) return null;
		
		// If the factories List is not instanciated, instanciate it.
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
	private final Map<LangData, IConverter> converters;
	
	private ConverterFactory(IDataLoader dataLoader) {
		this.dataLoader = dataLoader;
		
		converters = createConverterPool();
	}
	
	/**
	 * Convenience but less secure call to {@link #getConverter(LangData)}.
	 * @param langId - <code>String</code> corresponding to the ID of the <code>Language</code>.
	 * @return
	 * @throws UnsupportedLanguageException - When the language is not supported, this 
	 */
	public IConverter getConverter(String langId) throws UnsupportedLanguageException {
		LangData lang = null;
		
		for(LangData l : converters.keySet()) if(l.ID.equals(langId)) { lang = l; break; }
			
		if(lang == null) throw new UnsupportedLanguageException("There is no language for ID " + langId);
		
		return getConverter(lang);
	}
	
	/**
	 * Method that returns a <code>IConverter</code> instance of a given <code>Language lang</code>.
	 * @param lang - 
	 * @return
	 * @throws UnsupportedLanguageException
	 */
	public IConverter getConverter(LangData lang) throws UnsupportedLanguageException {	
		if(lang == null) throw new UnsupportedLanguageException("There is no language for ID " + lang);
		
		// If converter is not properly instanciated, instanciates it and saves it in the map.
		if(converters.get(lang) == null) { buildConverterForLanguage(lang); }
	
		// Returns the converter.
		return converters.get(lang);
	}

	/**
	 * Initializes a map, sets it's keys to Languages supported and it's values to null, which will be properly linked later in {@link #buildLanguage(LangData)}.
	 * @return The new <code>which</code> Languages (key) and Converter (value) instances.
	 */
	private Map<LangData, IConverter> createConverterPool() {
		Map<LangData, IConverter> converters = new HashMap<LangData, IConverter>();
	 
		try {
			for(LangData l : dataLoader.getSupportedLanguages()) {
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
	private IConverter buildConverterForLanguage(LangData lang) throws UnsupportedLanguageException {
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
	 * @return The newly created Converter object.
	 */
	private IConverter instanciateConverter(String className, Map<Integer, String> values) {
		try {
			IConverter converter;
			
			// Obtain the class of the desired object.
			Class<?> cls = Class.forName(className);
			
			// Grab the constructor of the class we want to instanciate.
			Constructor<?> constr = cls.getDeclaredConstructor(Map.class);
			
			// Finish by instanciating the converter.
			converter = (IConverter) constr.newInstance(values);
			
			return converter;
		} catch(ReflectiveOperationException e) {
			System.err.println("Failed at instanciating the converter, blankConverter will be returned.");
			e.printStackTrace();
		}
		
		return blankConverter;
	}
}