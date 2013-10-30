package me.alejnp.numbertostringconverter.converter;

public interface NumberToStringConverter {
	/**
	 * Convierte un n�mero a String, dependiendo de la localizaci�n de la clase que implementa.
	 * @param number - El n�mero a convertir a texto.
	 * @return La representaci�n textual del n�mero.
	 */
	public String convert(int number);
}
