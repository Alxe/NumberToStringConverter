package me.alejnp.numbertostringconverter.exception;

/**
 * Custom exception used by conversors whenever the number exceeds the supported range.
 * @author Alej. Núñez Pérez.
 *
 */
public class OutOfRangeConversionException extends IllegalArgumentException {
	/**
	 * Auto-generated serialVersionUID field
	 */
	private static final long serialVersionUID = 3461522251711229931L;

	/**
	 * Inherited constructor.
	 */
	public OutOfRangeConversionException() {
		super();
	}
	
	/**
	 * Inhereted constructor.
	 * @param string - The message of the Exception.
	 */
	public OutOfRangeConversionException(String string) {
		super(string);
	}
}
