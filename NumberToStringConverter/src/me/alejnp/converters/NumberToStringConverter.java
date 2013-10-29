package me.alejnp.converters;

public interface NumberToStringConverter {
	/**
	 * Convierte un número a String, dependiendo de la localización de la clase que implementa.
	 * @param number - El número a convertir a texto.
	 * @return La representación textual del número.
	 */
	public String convert(int number);
}
