package me.alejnp.ntsc.loader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import me.alejnp.ntsc.exception.ParsingException;
import me.alejnp.ntsc.interfaces.IDataLoader;
import me.alejnp.ntsc.loader.parser.LanguagesHandler;
import me.alejnp.ntsc.loader.parser.NumbersHandler;
import me.alejnp.ntsc.locale.Language;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.annotation.SuppressLint;
import android.content.Context;

/**
 * Android implementation of <code>{@link IDataLoader}</code>.
 * @author Alej. Núñez Pérez
 *
 */
public final class DataLoader implements IDataLoader {
	/**
	 * Context of the Android Activity, used to retrieve assets.
	 */
	private final Context context;
	
	/**
	 * Private constructor to match the singleton pattern.
	 */
	public DataLoader(Context context) {
		this.context = context;
	}
	
	public List<Language> getSupportedLanguages() throws ParsingException {
		try {
			List<Language> list = new ArrayList<Language>();
			
			// Instances a XMLReader, sets it's content handler and passes the retrieved data to a list.
			XMLReader xr = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
			xr.setContentHandler(new LanguagesHandler(list));
			xr.parse(new InputSource(context.getAssets().open("languages.xml")));
			
			return list;
			
		} catch (SAXException saxe) {
			throw new ParsingException(saxe.toString());
		} catch (ParserConfigurationException pce) {
			throw new ParsingException(pce.toString());
		} catch (IOException ioe) {
			throw new ParsingException(ioe.toString());
		}
	}
	
	@SuppressLint("UseSparseArrays")
	public Map<Integer, String> getLanguageMap(Language lang) throws ParsingException {
		try {
			Map<Integer, String> map = new HashMap<Integer, String>();
			
			// Instances a XMLReader, sets it's content handler and passes the retrieved data to a map.
			XMLReader xr = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
			xr.setContentHandler(new NumbersHandler(map));
			xr.parse(new InputSource(context.getAssets().open(lang.PATH)));
			
			return map;
		} catch (SAXException saxe) {
			throw new ParsingException(saxe.toString());
		} catch (ParserConfigurationException pce) {
			throw new ParsingException(pce.toString());
		} catch (IOException ioe) {
			throw new ParsingException(ioe.toString());
		}
	}
}
