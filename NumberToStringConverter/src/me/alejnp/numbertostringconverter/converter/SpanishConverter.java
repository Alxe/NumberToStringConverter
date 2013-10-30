package me.alejnp.numbertostringconverter.converter;

import java.util.Map;

public final class SpanishConverter extends AbstractConverter {
	private final String UNSUPORTED_NUMBER = "el método no soporta más de 999 millones";
	private final String MINUS = "menos";
	private final String AND = "y";

	protected SpanishConverter(Map<Integer, String> map) {
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
		final String sMillions = "millones";
		final int million = 1000000;
		
		/*
		 * millionthOfNumber - La parte correspondiente a los millones del número.
		 * modOfNumber - La parte correspondiente a el resto del calculo anterior.
		 * 
		 * El valor devuelto será una cadena tipo "{1} millón/millones {2} mil ..."
		 */
		
		final int millionthOfNumber = (number / million),
				modOfNumber = (number % million);
		
		final boolean moreThanAMillion = (number > million);
				
		return ((moreThanAMillion) ? (convert(millionthOfNumber) + " " + sMillions) : map.get(million)) + 
				((modOfNumber != 0) ? (" " + convert(modOfNumber)) : "");
	}
	
	private String treatThousands(int number) {
		final String sThousand = "mil";
		final int thousand = 1000;
		
		/*
		 * thousandthOfNumber - La parte correspondiente a los miles del número.
		 * modOfNumber - La parte correspondiente a el resto del calculo anterior.
		 * 
		 * El valor devuelto será una cadena tipo "{1} mil {2} ..."
		 */
		
		final int thousandthOfNumber = (number / thousand),
				modOfNumber = (number % thousand);
		
		final boolean moreThanAThousand = (number > thousand);
		
		return ((moreThanAThousand) ? (convert(thousandthOfNumber) + " ") : "") + sThousand + 
				((modOfNumber != 0) ? (" " + convert(modOfNumber)) : ""); 
	}
	
	private String treatHundreds(int number) {
		final String sHundred = "cien";
		final int hundred = 100;
		
		final int hundredthOfNumber = (number / hundred),
				modOfNumber = (number % hundred);
		
		final boolean moreThanOneHundred = (number > hundred);
		
		return (!moreThanOneHundred) ? sHundred : (
			map.get(hundredthOfNumber * hundred) + " " + convert(modOfNumber) );
	}
	
	private String treatSimple(int number) {
		final String sTen = "dieci",
				sTwenty = "veinti";
		final int ten = 10;
		
		final int tenthOfNumber = (number / ten),
				modOfNumber = (number % ten);
		
		if(modOfNumber == 0) {
			return map.get(number);
			
		} else {
			if(number > 30) {
				return (map.get(tenthOfNumber * ten) + " " + AND + " " + map.get(modOfNumber));
				
			} else if(number > 20) {
				return (sTwenty + map.get(modOfNumber));
				
			} else if(number > 15) {
				return (sTen + map.get(modOfNumber));
				
			} else {
				return map.get(number);
				
			}
		}
	}
}
