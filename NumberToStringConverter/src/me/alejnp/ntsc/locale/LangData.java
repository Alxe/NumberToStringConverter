package me.alejnp.ntsc.locale;

/**
 * Convenience class for storage of Language Data, with properties useful for localization.
 * @author Alej. Núñez Pérez
 *
 */
public final class LangData {
	/**
	 * The ID of the language, represented by a two character String.<br />
	 * &emsp;examples: "en" for English, "es" for Spanish.
	 */
	public final String ID;
	
	/**
	 * The name of the class that impelements {@link IConverter}, appended to it's package.<br />
	 * &emsp;examples: "com.example.EnglishConverter" for English, "com.example.SpanishConverter" for Spanish.
	 */
	public final String CLASS_NAME;
	
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
	public LangData(String id, String name) {
		this(id, name, "numbers/numbers_" + id + ".xml");
	}
	
	/**
	 * @param id - The ID of the language
	 * @param className - The full name of the language
	 * @param path - The path of the XML file with the mappings
	 */
	public LangData(String id, String className, String path) {
		ID = id;
		CLASS_NAME = className;
		PATH = path;
	}

	/**
	 * Auto-generated toString method.
	 */
	@Override
	public String toString() {
		return "LangData [ID=" + ID + ", CLASS_NAME=" + CLASS_NAME + ", PATH=" + PATH + "]";
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
		result = prime * result + ((CLASS_NAME == null) ? 0 : CLASS_NAME.hashCode());
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
		LangData other = (LangData) obj;
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
		if (CLASS_NAME == null) {
			if (other.CLASS_NAME != null)
				return false;
		} else if (!CLASS_NAME.equals(other.CLASS_NAME))
			return false;
		return true;
	}
}