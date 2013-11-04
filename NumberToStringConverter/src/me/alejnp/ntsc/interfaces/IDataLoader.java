package me.alejnp.ntsc.interfaces;

import java.util.List;
import java.util.Map;

import me.alejnp.ntsc.exception.ParsingException;
import me.alejnp.ntsc.locale.LangData;

/**
 * Interface to be implemented by the class that will natively manage and load data needed by the core package.
 * @author Alej. Núñez Pérez
 *
 */
public interface IDataLoader {
	/**
	 * Checks for the supported languages, defined in an xml file of name languages.xml on the assets folder.
	 * @return A list containing the supported languages.
	 * @throws ParsingException If the parsing ended abruptely, or the file to be parsed was not found.
	 */
	public List<LangData> getSupportedLanguages() throws ParsingException;
	
	/**
	 * Checks for the K:V mapping of the numbers, defined in an xml file of name numbers_<code>{id}</code>.xml on the numbers subfolder of assets.
	 * @param lang - The language of the strings to be returned.
	 * @return A map containing the numbers associated to their textual representation.
	 * @throws ParsingException If the parsing ended abruptely, or the file to be parsed was not found, meaning the language IS NOT supported.
	 */
	public Map<Integer, String> getLanguageMap(LangData lang) throws ParsingException;
}
