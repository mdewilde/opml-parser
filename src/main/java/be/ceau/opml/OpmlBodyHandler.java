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

/**
 * {@link OpmlSectionHandler} that deals with the {@code body} tag
 */
final class OpmlBodyHandler implements OpmlSectionHandler<Body> {

	private final Deque<OutlineBuilder> outlineBuilderStack = new ArrayDeque<>();
	private final Deque<String> elementStack = new ArrayDeque<>();

	private final List<OutlineBuilder> outlineBuilders = new ArrayList<>();

	@Override
	public void startTag(XmlPullParser xpp) throws OpmlParseException {
		ValidityCheck.requirePosition(xpp, XmlPullParser.START_TAG);

		switch (xpp.getName()) {
		case "body":
			// no special action required
			break;
		case "outline":
			OutlineBuilder outlineBuilder = parseOutlineBuilder(xpp);
			if (outlineBuilderStack.isEmpty()) {
				// this outline is a child of <body>
				outlineBuilders.add(outlineBuilder);
			} else {
				// this outline is nested in a different <outline>
				outlineBuilderStack.peek().subElements.add(outlineBuilder);
			}
			outlineBuilderStack.push(outlineBuilder);
			break;
		default:
			// An OPML file may contain elements and attributes not described on this page,
			// only if those elements are defined in a namespace, as specified by the W3C.
			// http://opml.org/spec2.opml#1629042982000
			if (ValidityCheck.isTextBlank(xpp.getNamespace())) {
				throw new OpmlParseException(String.format("encountered non-namespaced element <%s> instead of <outline>", xpp.getName()));
			}
			break;
		}

		elementStack.push(xpp.getName());

	}

	@Override
	public void text(XmlPullParser xpp) throws OpmlParseException {
		switch (elementStack.getFirst()) {
		case "outline":
		case "body":
			ValidityCheck.requireNoText(xpp, elementStack.getFirst(), true);
			break;
		default:
			// valid custom elements are allowed to have text
			// if it is on our elementStack here, it is valid
			break;
		}
	}

	@Override
	public void endTag(XmlPullParser xpp) throws OpmlParseException {
		ValidityCheck.requirePosition(xpp, XmlPullParser.END_TAG);

		String closed = elementStack.pop();

		if (!xpp.getName().equals(closed)) {
			throw new OpmlParseException(String.format("required element <%s> but found <%s>", closed, xpp.getName()));
		}

		switch (xpp.getName()) {
		case "outline":
			if (outlineBuilderStack.isEmpty()) {
				throw new OpmlParseException("invalid nesting of outline elements");
			}
			outlineBuilderStack.pop();
			break;
		case "body":
			if (!outlineBuilderStack.isEmpty()) {
				throw new OpmlParseException("unclosed outline element(s)");
			}
			break;
		default:
			if (ValidityCheck.isTextBlank(xpp.getNamespace())) {
				throw new OpmlParseException(String.format("encountered non-namespaced element <%s> instead of <outline> or <body>", xpp.getName()));
			}
			break;
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
