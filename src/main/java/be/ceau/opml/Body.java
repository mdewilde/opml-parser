package be.ceau.opml;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains the content of the outline. Must have at least one outline element.
 */
public class Body {

	// it is expected that a body will have outline elements
	private final List<Outline> outlines = new ArrayList<>();

	public void addOutline(Outline outline) {
		if (outline != null) {
			this.outlines.add(outline);
		}
	}
	
	public List<Outline> getOutlines() {
		return outlines;
	}

	@Override
	public String toString() {
		return new StringBuilder()
				.append("Body [outlines=")
				.append(outlines)
				.append("]")
				.toString();
	}
	
}
