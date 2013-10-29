package me.alejnp.converters;

import java.util.Map;

public abstract class AbstractConverter implements NumberToStringConverter {
	protected final Map<Integer, String> map;
	
	protected AbstractConverter(Map<Integer, String> map) {
		this.map = map;
	}
}
