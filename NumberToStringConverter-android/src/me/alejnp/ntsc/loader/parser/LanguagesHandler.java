package me.alejnp.ntsc.loader.parser;

import java.util.List;

import me.alejnp.ntsc.locale.Language;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class LanguagesHandler extends DefaultHandler {
	private final List<Language> array;
	
	public LanguagesHandler(List<Language> array) {
		this.array = array;
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if(uri.equals("entry")) {
			String id = attributes.getValue("id");
			String name = attributes.getValue("name");
			String directory = attributes.getValue("directory");
			
			array.add(new Language(id, name, directory));
		}
	}
}
