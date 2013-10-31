package me.alejnp.numbertostringconverter.exception;

public class UnsupportedLanguageException extends Exception {
	private static final long serialVersionUID = 7899012404501772467L;
	
	public UnsupportedLanguageException() {
		super();
	}
	
	public UnsupportedLanguageException(String string) {
		super(string);
	}
}
