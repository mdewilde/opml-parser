/*
	Copyright 2019 Marceau Dewilde <m@ceau.be>
	
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
import java.util.List;
import java.util.Objects;

/**
 * Contains the content of the outline. Must have at least one outline element.
 */
public class Body implements Serializable {

	private static final long serialVersionUID = 1510395846940L;

	// A <body> contains one or more <outline> elements
	private final List<Outline> outlines;

	public Body(List<Outline> outlines) {
		if (outlines == null || outlines.isEmpty()) {
			throw new IllegalArgumentException("a Body must contain at least one Outline");
		}
		this.outlines = Collections.unmodifiableList(new ArrayList<>(outlines));
	}

	/**
	 * @return unmodifiable {@link List} holding every {@link Outline} in this {@link Body}
	 */
	public List<Outline> getOutlines() {
		return outlines;
	}

	/**
	 * @param index
	 *            index of the element to return
	 * @return {@link Outline} at the given index, or {@code null} if there is no element at that index
	 */
	public Outline getOutline(int index) {
		if (index < outlines.size()) {
			return outlines.get(index);
		}
		return null;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Body [outlines=");
		for (Outline outline : outlines) {
			sb.append(System.lineSeparator()).append(outline);
		}
		return sb.append("]").toString();
	}

	@Override
	public int hashCode() {
		return 31 + Objects.hashCode(outlines);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Body other = (Body) obj;
		return Objects.equals(outlines, other.outlines);
	}

}
