package me.alejnp.ntsc.loader.parser;

import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Handler for <code>Number:String</code> mappings in XML files
 * @author Alej. Núñez Pérez
 *
 */
public class NumbersHandler extends DefaultHandler {
	private final Map<Integer, String> map;
	
	public NumbersHandler(Map<Integer, String> map) {
		this.map = map;
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if(uri.equals("entry")) {
			int key = Integer.parseInt(attributes.getValue("key"));
			String value = attributes.getValue("value");
			
			map.put(key, value);
		}
	}
}
