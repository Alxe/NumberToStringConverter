package me.alejnp.numbertostringconverter.loader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import me.alejnp.numbertostringconverter.exception.ParsingException;
import me.alejnp.numbertostringconverter.interfaces.IConfigLoader;
import me.alejnp.numbertostringconverter.loader.parser.LanguagesHandler;
import me.alejnp.numbertostringconverter.loader.parser.NumbersHandler;
import me.alejnp.numbertostringconverter.locale.Language;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.annotation.SuppressLint;
import android.content.Context;

/**
 * Android implementation of <code>{@link IConfigLoader}</code>.
 * @author Alej. Núñez Pérez
 *
 */
public final class ConfigLoader implements IConfigLoader {
	/**
	 * Singleton object of the IClassLoader implementation.
	 */
	private static ConfigLoader _instance;
	
	/**
	 * Returns the instance of the ClassLoader, instanciating it if it's needed.
	 * @return The singleton of ClassLoader.
	 */
	public static ConfigLoader getInstance() {
		return (_instance != null) ? _instance : (_instance = new ConfigLoader());
	}
	
	/**
	 * Private constructor to match the singleton pattern.
	 */
	private ConfigLoader() {
		// Do nothing
	}
	
	/**
	 * Context of the Android Activity, used to retrieve assets.
	 */
	private Context context;
	
	/**
	 * Setter for the context.
	 * @param context - The new context.
	 */
	public void setContext(Context context) {
		this.context = context;
	}
	
	public List<Language> getSupportedLanguages() throws ParsingException {
		try {
			List<Language> list = new ArrayList<Language>();
			
			XMLReader xr = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
			xr.setContentHandler(new LanguagesHandler(list));
			xr.parse(new InputSource(context.getAssets().open("languages.xml")));
			
			return list;
			
		} catch (SAXException e) {
			throw new ParsingException(e.toString());
		} catch (ParserConfigurationException e) {
			throw new ParsingException(e.toString());
		} catch (IOException e) {
			throw new ParsingException(e.toString());
		}
	}
	
	@SuppressLint("UseSparseArrays")
	public Map<Integer, String> getLanguageMap(Language lang) throws ParsingException {
		try {
			Map<Integer, String> map = new HashMap<Integer, String>();
			
			XMLReader xr = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
			xr.setContentHandler(new NumbersHandler(map));
			xr.parse(new InputSource(context.getAssets().open(lang.PATH)));
			
			return map;
		} catch (SAXException e) {
			throw new ParsingException(e.toString());
		} catch (ParserConfigurationException e) {
			throw new ParsingException(e.toString());
		} catch (IOException e) {
			throw new ParsingException(e.toString());
		}
	}
}
