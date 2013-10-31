package me.alejnp.numbertostringconverter.exception;

/**
 * Custom exception used when the Conversor of the Language specified is not available
 * @author Alej. Núñez Pérez.
 *
 */
public class UnsupportedLanguageException extends Exception {
	/**
	 * Auto-generated serialVersionUID field.
	 */
	private static final long serialVersionUID = 7899012404501772467L;
	
	/**
	 * Inherited constructor.
	 */
	public UnsupportedLanguageException() {
		super();
	}
	
	/**
	 * Inhereted constructor.
	 * @param string - The message of the Exception.
	 */
	public UnsupportedLanguageException(String string) {
		super(string);
	}
}
