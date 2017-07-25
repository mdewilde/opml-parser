package be.ceau.opml;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a line in the outline. May contain any number of arbitrary
 * attributes. Common attributes include text and type. The outline element may
 * contain any number of outline sub-elements.
 */
public class Outline {

	public final Map<String, String> attributes = new HashMap<>();
	public List<Outline> subElements;
	
	public void putAttribute(String key, String value) {
		this.attributes.put(key, value);
	}
	
	public Map<String, String> getAttributes() {
		return attributes;
	}
	
	public void addSubElement(Outline outline) {
		if (subElements == null) {
			subElements = new ArrayList<>();
		}
		subElements.add(outline);
	}
	
	public List<Outline> getSubElements() {
		if (subElements == null) {
			return Collections.emptyList();
		}
		return subElements;
	}

	@Override
	public String toString() {
		return new StringBuilder()
				.append("Outline [attributes=")
				.append(attributes)
				.append(", subElements=")
				.append(subElements)
				.append("]")
				.toString();
	}
	
}
