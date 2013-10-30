package me.alejnp.converters;

import java.util.HashMap;
import java.util.Map;

public class SpanishConverter extends AbstractConverter {
	public static void main(String... args) {
		HashMap<Integer, String> map = new HashMap<>();
		
		map.put(0, "cero");
		map.put(1, "uno");
		map.put(2, "dos");
		map.put(3, "tres");
		map.put(4, "cuatro");
		map.put(5, "cinco");
		map.put(6, "seis");
		map.put(7, "siete");
		map.put(8, "ocho");
		map.put(9, "nueve");
		map.put(10, "diez");
		map.put(11, "once");
		map.put(12, "doce");
		map.put(13, "trece");
		map.put(14, "catorce");
		map.put(15, "quince");
		map.put(20, "veinte");
		map.put(30, "treinta");
		map.put(40, "cuarenta");
		map.put(50, "cincuenta");
		map.put(60, "sesenta");
		map.put(70, "setenta");
		map.put(80, "ochenta");
		map.put(90, "noventa");
		map.put(100, "ciento");
		map.put(200, "doscientos");
		map.put(300, "trescientos");
		map.put(400, "cuatrocientos");
		map.put(500, "quinientos");
		map.put(600, "seiscientos");
		map.put(700, "setecientos");
		map.put(800, "ochocientos");
		map.put(900, "novecientos");
		
		
		NumberToStringConverter ntsc = new SpanishConverter(map);
		
		System.out.println(ntsc.convert(0002503));
	}
	
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
		
		final boolean moreThanAMillion = (millionthOfNumber > 1) && (modOfNumber != 0);
				
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
		
		final boolean moreThanAThousand = (thousandthOfNumber > 1) && (modOfNumber != 0);
		
		return ((moreThanAThousand) ? (convert(thousandthOfNumber) + " ") : "") + sThousand + 
				((modOfNumber != 0) ? (" " + convert(modOfNumber)) : ""); 
	}
	
	private String treatHundreds(int number) {
		final String sHundred = "cien";
		final int hundred = 100;
		
		final int hundredthOfNumber = (number / hundred),
				modOfNumber = (number % hundred);
		
		final boolean moreThanOneHundred = (hundredthOfNumber > 1) && (modOfNumber != 0);
		
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
