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
import java.util.Deque;

import org.xmlpull.v1.XmlPullParser;

/**
 * {@link OpmlSectionHandler} that deals with the {@code opml} tag
 */
final class OpmlInitHandler implements OpmlSectionHandler<String> {

	private final Deque<String> stack = new ArrayDeque<>();

	private boolean started = false;
	private String version;

	@Override
	public void startTag(XmlPullParser xpp) throws OpmlParseException {
		ValidityCheck.requirePosition(xpp, XmlPullParser.START_TAG);
		switch (xpp.getName()) {
		case "opml":
			if (started) {
				throw new OpmlParseException("an opml document can not have multiple <opml> elements");
			}
			version = xpp.getAttributeValue(null, "version");
			if (version == null) {
				throw new OpmlParseException("opml element does not have required attribute version");
			}
			started = true;
			stack.push(xpp.getName());
			break;
		default:
			// An OPML file may contain elements and attributes not described on this page,
			// only if those elements are defined in a namespace, as specified by the W3C.
			// http://opml.org/spec2.opml#1629042982000
			if (ValidityCheck.isTextBlank(xpp.getNamespace())) {
				throw new OpmlParseException(String.format("encountered non-namespaced element <%s> instead of <opml>", xpp.getName()));
			}
			stack.push(xpp.getNamespace() + ":" + xpp.getName());
			break;
		}
	}

	@Override
	public void text(XmlPullParser xpp) throws OpmlParseException {
		if (stack.isEmpty()) {
			ValidityCheck.requireNoText(xpp, "opml", started);
		} else {
			switch (stack.peek()) {
			case "opml":
				ValidityCheck.requireNoText(xpp, "opml", started);
				break;
			default:
				// no strict rules regarding text in properly namespaced custom elements
				break;
			}
		}
	}

	@Override
	public void endTag(XmlPullParser xpp) throws OpmlParseException {
		switch (stack.peek()) {
		case "opml":
			started = false;
			break;
		default:
			break;
		}
	}

	@Override
	public String get() {
		return version;
	}

}
