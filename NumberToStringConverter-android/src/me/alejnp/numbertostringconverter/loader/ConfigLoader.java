package me.alejnp.numbertostringconverter.loader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import me.alejnp.numbertostringconverter.R;
import me.alejnp.numbertostringconverter.converter.ConverterFactory.Language;
import me.alejnp.numbertostringconverter.exception.ParsingException;
import me.alejnp.numbertostringconverter.interfaces.IConfigLoader;
import me.alejnp.numbertostringconverter.loader.parser.NumbersHandler;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.annotation.SuppressLint;
import android.content.Context;

public final class ConfigLoader implements IConfigLoader {
	private static ConfigLoader _instance;
	
	public static ConfigLoader getInstance() {
		return (_instance != null) ? _instance : (_instance = new ConfigLoader());
	}
	
	private ConfigLoader() {
		// Do nothing
	}
	
	private Context context;
	
	public void setContext(Context context) {
		this.context = context;
	}
	
	public Language[] getSupportedLanguages() {
		String[] array = context.getResources().getStringArray(R.array.languages);
		Language[] langs = new Language[array.length];
		
		for(int i = 0; i < array.length; i++) {
			langs[i] = new Language(array[i]);
		}
		
		return langs;
	}
	
	@SuppressLint("UseSparseArrays")
	public Map<Integer, String> getLanguageMap(String path) throws ParsingException {
		try {
			Map<Integer, String> map = new HashMap<Integer, String>();
			
			XMLReader xr = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
			xr.setContentHandler(new NumbersHandler(map));
			xr.parse(new InputSource(context.getAssets().open(path)));
			
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
