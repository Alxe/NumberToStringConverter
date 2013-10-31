package me.alejnp.numbertostringconverter.converter;

import java.util.Map;

import me.alejnp.numbertostringconverter.interfaces.IConverter;

public abstract class AbstractConverter implements IConverter {
	protected final Map<Integer, String> map;
	
	protected AbstractConverter(Map<Integer, String> map) {
		this.map = map;
	}
}
