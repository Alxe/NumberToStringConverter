package me.alejnp.numbertostringconverter.exception;

/**
 * Custom exception used in parsing non-critical errors.
 * @author Alej. Núñez Pérez.
 *
 */
public class ParsingException extends Exception {
	/**
	 * Auto-generated serialVersionUID field.
	 */
	private static final long serialVersionUID = 190629866424886282L;

	/**
	 * Inherited constructor.
	 */
	public ParsingException() {
		super();
	}
	
	/**
	 * Inhereted constructor.
	 * @param string - The message of the Exception.
	 */
	public ParsingException(String string) {
		super(string);
	}
}
