package me.alejnp.converters;

import java.util.Map;

public class SpanishConverter extends AbstractConverter {
	private final String UNSUPORTED_NUMBER = "el método no soporta más de 999 millones";
	private final String MINUS = "menos";
	private final String AND = "y";

	public SpanishConverter(Map<Integer, String> map) {
		super(map);
	}

	@Override
	public String convert(int number) {
		String str;
		boolean negative = false;
		
		if(number < 0) {
			// Mencionamos que el número era negativo.
			negative = true;
			
		}
		
		number = Math.abs(number);
		
		if(number > 999999999) {
			// En el caso de que el valor superase los 999 millones, tiramos una excepción mencionando que no está soportado.
			throw new IllegalArgumentException(UNSUPORTED_NUMBER);
			
		} else if(number > 999999) {
			// El número será inferior a mil millones, pero igual o mayor a un millón.
			str = convertMillions(number);
			
		} else if(number > 999) {
			// El número será inferior a un millón pero igual o mayor a mil.
			str = convertThousands(number);
			
		} else if(number > 99) {
			// El número será inferior a mil pero igual o mayor a cien.
			str = convertHundreds(number);
			
		} else {
			// El número será inferior a cien, un lugar donde las palabras únicas son abundantes.
			str = convertSimple(number);
		}
		
		return ((negative) ? MINUS : "") + " " + str;
	}
	
	private String convertMillions(int number) {
		if(number < 1000000 || number > 999999999) throw new IllegalArgumentException("number < 1000000 || number > 999999999");
		
		if((number % 1000000) == 0) {
			// El número es multiplo exacto de un millón.
			return convertHundreds(number / 1000)
			
		} else {
			
		}
	}
	
	private String convertThousands(int number) {
		return "";
	}
	
	private String convertHundreds(int number) {
		if(number < 100 || number > 999) throw new IllegalArgumentException("number < 100 || number > 999");

		if((number % 100) == 0) {
			return map.get(number);
		} else {
			return map.get(number - (number % 100)) + convertSimple(number % 100);
		}
	}
	
	private String convertSimple(int number) {
		return "";
	}
}
