package me.alejnp.numbertostringconverter.interfaces;

import me.alejnp.numbertostringconverter.exception.OutOfRangeConversionException;

/**
 * Interface to be implemented by a class designed to play nice with the Converter framework.
 * @author Alej. Núñez Pérez
 *
 */
public interface IConverter {
	/**
	 * Transforms a number to it's textual representation.
	 * @param number - Number to be converted into text.
	 * @return Textual representation of the number as <code>String</code>.
	 * @throws OutOfRangeConversionException Thrown when the <code>number</code> exceeds the allowed value.
	 */
	public String convert(int number) throws OutOfRangeConversionException;
}
