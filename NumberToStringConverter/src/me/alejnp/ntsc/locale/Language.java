package me.alejnp.ntsc.locale;

/**
 * Convenience class for storage of Language, with properties useful for localization.
 * @author Alej. Núñez Pérez
 *
 */
public final class Language {
	/**
	 * The ID of the language, represented by a two character String.<br />
	 * &emsp;examples: "en" for English, "es" for Spanish.
	 */
	public final String ID;
	
	/**
	 * The name of the language in plain English, critical for the <code>IConverter</code> implementations.<br />
	 * &emsp;examples: "english" for English, "spanish" for Spanish.
	 */
	public final String NAME;
	
	/**
	 * The path of the XML file to be parsed.<br />
	 * &emsp;examples: "numbers/numbers_en.xml" for the English mappings XML file.
	 */
	public final String PATH;
	
	/**
	 * Shorter Constructor, the <code>PATH</code> is defaulted to "numbers/numbers_{id}.xml".
	 * @param id - The ID of the language
	 * @param name - The full name of the language
	 */
	public Language(String id, String name) {
		this(id, name, "numbers/numbers_" + id + ".xml");
	}
	
	/**
	 * Shorter Constructor, the <code>PATH</code> is defaulted to "numbers/numbers_{id}.xml".
	 * @param id - The ID of the language
	 * @param name - The full name of the language
	 * @param path - The path of the XML file with the mappings
	 */
	public Language(String id, String name, String path) {
		ID = id;
		NAME = name;
		PATH = path;
	}

	/**
	 * Auto-generated toString method.
	 */
	@Override
	public String toString() {
		return "Language [ID=" + ID + ", NAME=" + NAME + ", PATH=" + PATH + "]";
	}

	/**
	 * Auto-generated hashCode method.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((PATH == null) ? 0 : PATH.hashCode());
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
		result = prime * result + ((NAME == null) ? 0 : NAME.hashCode());
		return result;
	}

	/**
	 * Auto-generated equals method.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Language other = (Language) obj;
		if (PATH == null) {
			if (other.PATH != null)
				return false;
		} else if (!PATH.equals(other.PATH))
			return false;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		if (NAME == null) {
			if (other.NAME != null)
				return false;
		} else if (!NAME.equals(other.NAME))
			return false;
		return true;
	}
}