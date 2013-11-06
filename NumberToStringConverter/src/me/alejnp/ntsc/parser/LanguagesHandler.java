package me.alejnp.ntsc.parser;

import java.util.List;

import me.alejnp.ntsc.locale.LangData;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class LanguagesHandler extends DefaultHandler {
	/**
	 * A reference indicating where to store the parsed data.
	 */
	private final List<LangData> list;
	
	/**
	 * @param list - The reference of the list that will store the results.
	 */
	public LanguagesHandler(List<LangData> list) {
		this.list = list;
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// Retrieves "id", "converterClass" and "dataPath" per "entry" of the languages.xml file.
		if(localName.equals("entry")) {
			String id = attributes.getValue("id");
			String path = attributes.getValue("dataPath");
			String className = attributes.getValue("converterClass");
			
			list.add(new LangData(id, className, path));
		}
	}
}
