/*
	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at
	
		https://www.apache.org/licenses/LICENSE-2.0
	
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
*/
package be.ceau.opml.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import be.ceau.opml.ValidityCheck;

/**
 * <p>
 * An {@code <outline>} is an XML element containing at least one required attribute, text, and zero or more additional
 * attributes. An {@code <outline>} may contain zero or more {@code <outline>} sub-elements. No attribute may be
 * repeated within the same {@code <outline>} element.
 * </p>
 * <p>
 * Instances are unmodifiable and threadsafe.
 * </p>
 */
public class Outline implements Serializable {

	private static final long serialVersionUID = 1510395943061L;

	private final Map<String, String> attributes;
	private final List<Outline> subElements;

	/**
	 * @param attributes
	 *            a {@link Map}, can not be {@code null}, can not contain {@code null} or blank {@link String} as key
	 * @param subElements 
	 *            a {@link List}, can not be {@code null}
	 * @throws IllegalArgumentException
	 *            if either argument {@code null} or if {@code attributes} contains a blank key
	 */
	public Outline(Map<String, String> attributes, List<Outline> subElements) {
		if (attributes == null) {
			throw new IllegalArgumentException("attributes can not be null");
		}
		if (subElements == null) {
			throw new IllegalArgumentException("subElements can not be null");
		}
		Map<String, String> map = new HashMap<>();
		for (Entry<String, String> entry : attributes.entrySet()) {
			if (ValidityCheck.isTextBlank(entry.getKey())) {
				throw new IllegalArgumentException("attributes map contains a null or blank key");
			}
			map.put(entry.getKey(), entry.getValue());
		}
		this.attributes = Collections.unmodifiableMap(map);
		this.subElements = Collections.unmodifiableList(new ArrayList<>(subElements));
	}

	/**
	 * @return unmodifiable {@link Map} with all attributes of this {@link Outline}
	 */
	public Map<String, String> getAttributes() {
		return attributes;
	}

	/**
	 * Convenience method. Equivalent to:
	 * 
	 * <pre>
	 * {@code getAttributes().get(key);}
	 * </pre>
	 * 
	 * @param key
	 *            a {@link String}, can not be null
	 * @return the value of that attribute, or {@code null} if not available
	 */
	public String getAttribute(String key) {
		if (key == null) {
			throw new IllegalArgumentException("key can not be null");
		}
		return attributes.get(key);
	}

	/**
	 * Convenience method. Equivalent to:
	 * 
	 * <pre>
	 * {@code getAttributes().get("text");}
	 * </pre>
	 * 
	 * As per the specification of OPML 2.0, the text attribute is required. However, it is not in the version 1.0 spec.
	 * 
	 * @return the value of attribute text, or {@code null} if not available
	 */
	public String getText() {
		return attributes.get("text");
	}

	public List<Outline> getSubElements() {
		return subElements;
	}

	/**
	 * @param index
	 *            index of the element to return
	 * @return {@link Outline} at the given index, or {@code null} if there is no element at that index
	 */
	public Outline getSubElement(int index) {
		if (index < subElements.size()) {
			return subElements.get(index);
		}
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Objects.hashCode(attributes);
		result = prime * result + Objects.hashCode(subElements);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Outline other = (Outline) obj;
		return Objects.equals(attributes, other.attributes) && Objects.equals(subElements, other.subElements);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Outline [attributes=").append(attributes).append(", subElements=");
		for (Outline outline : subElements) {
			sb.append(System.lineSeparator()).append("\t").append(outline);
		}
		return sb.append("]").toString();
	}

}
