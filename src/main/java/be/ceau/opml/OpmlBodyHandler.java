package be.ceau.opml;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;

import be.ceau.opml.entity.Body;
import be.ceau.opml.entity.Outline;

final class OpmlBodyHandler implements OpmlSectionHandler<Body> {

	private final Deque<OutlineBuilder> stack = new ArrayDeque<>();

	private final List<OutlineBuilder> outlineBuilders = new ArrayList<>();

	@Override
	public void startTag(XmlPullParser xpp) throws OpmlParseException {
		ValidityCheck.require(xpp, XmlPullParser.START_TAG, "outline");

		OutlineBuilder outlineBuilder = parseOutlineBuilder(xpp);
		if (stack.isEmpty()) {
			// this outline is a child of <body>
			outlineBuilders.add(outlineBuilder);
		} else {
			// this outline is nested in a different <outline>
			stack.peek().subElements.add(outlineBuilder);
		}

		stack.push(outlineBuilder);
	}

	@Override
	public void text(XmlPullParser xpp) throws OpmlParseException {
		ValidityCheck.requireNoText(xpp, stack.isEmpty() ? "body" : "outline");
	}

	@Override
	public void endTag(XmlPullParser xpp) throws OpmlParseException {
		if (!stack.isEmpty()) {
			stack.pop();
			ValidityCheck.require(xpp, XmlPullParser.END_TAG, "outline");
		} else {
			ValidityCheck.require(xpp, XmlPullParser.END_TAG, "body");
		}
	}

	@Override
	public Body get() {
		final List<Outline> outlines = new ArrayList<>();
		for (OutlineBuilder subElement : outlineBuilders) {
			outlines.add(build(subElement));
		}
		return new Body(outlines);
	}

	private Outline build(OutlineBuilder builder) {
		final List<Outline> subElements = new ArrayList<>();
		for (OutlineBuilder subElement : builder.subElements) {
			subElements.add(build(subElement));
		}
		return new Outline(builder.attributes, subElements);
	}
	
	private OutlineBuilder parseOutlineBuilder(XmlPullParser xpp) throws OpmlParseException {
		final OutlineBuilder outlineBuilder = new OutlineBuilder();
		for (int i = 0; i < xpp.getAttributeCount(); i++) {
			String name = xpp.getAttributeName(i);
			if (outlineBuilder.attributes.containsKey(name)) {
				throw new OpmlParseException(String.format("element %s contains attribute %s more than once", xpp.getName(), name));
			}
			outlineBuilder.attributes.put(xpp.getAttributeName(i), xpp.getAttributeValue(i));
		}
		return outlineBuilder;
	}

	private static class OutlineBuilder {
		private final Map<String, String> attributes = new HashMap<>();
		private final List<OutlineBuilder> subElements = new ArrayList<>();
	}

}
