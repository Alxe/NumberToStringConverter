package me.alejnp.ntsc.converter;

import java.util.Map;

import me.alejnp.ntsc.exception.OutOfRangeConversionException;
import me.alejnp.ntsc.interfaces.IConverter;

/**
 * Convenience class for those properties shared by all Converters, namely, the mappings.
 * @author Alej. Núñez Pérez
 *
 */
public abstract class AbstractConverter implements IConverter {
	/**
	 * Blank instance of {@link AbstractConverter}, useful to return this instead of null.
	 */
	protected static final IConverter blankConverter = new AbstractConverter(null) {
		@Override
		public String convert(int number) throws OutOfRangeConversionException {
			// Do nothing, return empty string.
			
			return "";
		}
	};
	
	/**
	 * Mappings of <code>Number</code> to <code>String</code> needed by the Converter
	 */
	protected final Map<Integer, String> map;
	
	/**
	 * Constructor to be inherited by the child classes.
	 * @param map - A reference to the mappings of the Converter
	 */
	protected AbstractConverter(Map<Integer, String> map) {
		this.map = map;
	}
}
