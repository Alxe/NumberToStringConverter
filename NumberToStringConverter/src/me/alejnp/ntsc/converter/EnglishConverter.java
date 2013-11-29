package me.alejnp.ntsc.converter;

import java.util.Map;

public class EnglishConverter extends AbstractConverter {
	private final String UNSUPORTED_NUMBER = "over 999 millions not supported";
	private final String MINUS = "minus";
	private final String AND = "-";

	/**
	 * @see AbstractConverter#AbstractConverter(Map)
	 */
	protected EnglishConverter(Map<Integer, String> map) {
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
			str = UNSUPORTED_NUMBER;
			
		} else if(number > 999999) {
			// El número será inferior a mil millones, pero igual o mayor a un millón.
			str = treatMillions(number);
			
		} else if(number > 999) {
			// El número será inferior a un millón pero igual o mayor a mil.
			str = treatThousands(number);
			
		} else if(number > 99) {
			// El número será inferior a mil pero igual o mayor a cien.
			str = treatHundreds(number);
			
		} else {
			// El número será inferior a cien, un lugar donde las palabras únicas son abundantes.
			str = treatSimple(number);
		}
		
		return (((negative) ? MINUS : "") + " " + str).trim();
	}
	
	private String treatMillions(int number) {
		final String sMillion = "million";
		final int million = 1000000;
		
		/*
		 * millionthOfNumber - La parte correspondiente a los millones del número.
		 * modOfNumber - La parte correspondiente a el resto del calculo anterior.
		 * 
		 * El valor devuelto será una cadena tipo "{1} million(s) {2} mil ..."
		 */
		
		final int millionthOfNumber = (number / million),
				modOfNumber = (number % million);
				
		return (convert(millionthOfNumber) + " " + sMillion) + " " + convert(modOfNumber);
	}
	
	private String treatThousands(int number) {
		final String sThousand = "thousand";
		final int thousand = 1000;
		
		/*
		 * thousandthOfNumber - La parte correspondiente a los miles del número.
		 * modOfNumber - La parte correspondiente a el resto del calculo anterior.
		 * 
		 * El valor devuelto será una cadena tipo "{1} thousand(s) {2} ..."
		 */
		
		final int thousandthOfNumber = (number / thousand),
				modOfNumber = (number % thousand);
		
		return (convert(thousandthOfNumber) + " " + sThousand) + " " + convert(modOfNumber); 
	}
	
	private String treatHundreds(int number) {
		final String sHundred = "hundred";
		final int hundred = 100;
		
		final int hundredthOfNumber = (number / hundred),
				modOfNumber = (number % hundred);
		
		return (convert(hundredthOfNumber) + " " + sHundred) + " " + convert(modOfNumber);
	}
	
	private String treatSimple(int number) {
		final int ten = 10;
		
		final int modOfNumber = (number % ten);
		
		if(modOfNumber != 0 && number > 20) {
			return map.get(number - modOfNumber) + AND + map.get(modOfNumber);
			
		} 

		return (number != 0) ? map.get(number) :
			"";
	}
}
