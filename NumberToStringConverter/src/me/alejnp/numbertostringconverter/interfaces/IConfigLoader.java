package me.alejnp.numbertostringconverter.interfaces;

import java.util.Map;

import me.alejnp.numbertostringconverter.converter.ConverterFactory.Language;
import me.alejnp.numbertostringconverter.exception.ParsingException;

public interface IConfigLoader {
	public Language[] getSupportedLanguages();
	
	public Map<Integer, String> getLanguageMap(String path) throws ParsingException;
}
