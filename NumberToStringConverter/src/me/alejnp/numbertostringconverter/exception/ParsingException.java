package me.alejnp.numbertostringconverter.exception;

public class ParsingException extends Exception {
	private static final long serialVersionUID = 190629866424886282L;

	public ParsingException() {
		super();
	}
	
	public ParsingException(String string) {
		super(string);
	}
}
