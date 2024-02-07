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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.xmlpull.v1.XmlPullParser;

import be.ceau.opml.entity.Head;

/**
 * {@link OpmlSectionHandler} that deals with the {@code head} tag
 */
final class OpmlHeadHandler implements OpmlSectionHandler<Head> {

	private final Deque<String> stack = new ArrayDeque<>();
	private final Set<String> appeared = new HashSet<>();

	private boolean started = false;

	private String title;
	private String dateCreated;
	private String dateModified;
	private String ownerName;
	private String ownerEmail;
	private String ownerId;
	private String docs;
	private final List<Integer> expansionState = new ArrayList<>();
	private Integer vertScrollState;
	private Integer windowTop;
	private Integer windowLeft;
	private Integer windowBottom;
	private Integer windowRight;

	@Override
	public void startTag(XmlPullParser xpp) throws OpmlParseException {
		// no nested elements in the standard elements in head
		if (!stack.isEmpty() && !stack.peek().contains(":")) {
			throw new OpmlParseException(String.format("head section contains nested element %s inside element %s", xpp.getName(), stack.peek()));
		}

		switch (xpp.getName()) {
		case "title":
		case "dateCreated":
		case "dateModified":
		case "ownerName":
		case "ownerEmail":
		case "ownerId":
		case "docs":
		case "expansionState":
		case "vertScrollState":
		case "windowBottom":
		case "windowLeft":
		case "windowRight":
		case "windowTop":
			// Each sub-element of <head> may appear once or not at all.
			// No sub-element of <head> may be repeated.
			// http://opml.org/spec2.opml#1629043127000
			// -- we currently take this to apply to standard elements only
			if (!appeared.add(xpp.getName())) {
				throw new OpmlParseException(String.format("encountered element <%s> more than once inside <head>", xpp.getName()));
			}
			// the standard <head> elements are ok
			stack.push(xpp.getName());
			break;
		default:
			// An OPML file may contain elements and attributes not described on this page,
			// only if those elements are defined in a namespace, as specified by the W3C.
			// http://opml.org/spec2.opml#1629042982000
			if (ValidityCheck.isTextBlank(xpp.getNamespace())) {
				throw new OpmlParseException(String.format("encountered non-namespaced element <%s> instead of <head>", xpp.getName()));
			}
			stack.push(xpp.getNamespace() + ":" + xpp.getName());
			break;
		}

		started = true;

	}

	@Override
	public void text(XmlPullParser xpp) throws OpmlParseException {
		if (stack.isEmpty()) {
			// we should be between <head> and first element
			ValidityCheck.requireNoText(xpp, "head", started);
			return;
		}
		final String text = xpp.getText();

		switch (stack.peek()) {
		case "title":
			title = text;
			break;
		case "dateCreated":
			dateCreated = text;
			break;
		case "dateModified":
			dateModified = text;
			break;
		case "ownerName":
			ownerName = text;
			break;
		case "ownerEmail":
			ownerEmail = text;
			break;
		case "ownerId":
			ownerId = text;
			break;
		case "docs":
			docs = text;
			break;
		case "expansionState":
			String[] split = text.split(",");
			for (String part : split) {
				part = part.trim();
				if (!part.isEmpty()) {
					try {
						expansionState.add(Integer.parseInt(part.trim()));
					} catch (NumberFormatException e) {
						throw new OpmlParseException("expansionState must be a comma-separated list of line numbers");
					}
				}
			}
			break;
		case "vertScrollState": {
			String trimmed = text.trim();
			if (!trimmed.isEmpty()) {
				try {
					vertScrollState = Integer.parseInt(trimmed.trim());
				} catch (NumberFormatException e) {
					throw new OpmlParseException("vertScrollState must be a number");
				}
			} else {
				vertScrollState = null;
			}
			break;
		}
		case "windowBottom": {
			String trimmed = text.trim();
			if (!trimmed.isEmpty()) {
				try {
					windowBottom = Integer.parseInt(text.trim());
				} catch (NumberFormatException e) {
					throw new OpmlParseException("windowBottom must be a number");
				}
			} else {
				windowBottom = null;
			}
			break;
		}
		case "windowLeft": {
			String trimmed = text.trim();
			if (!trimmed.isEmpty()) {
				try {
					windowLeft = Integer.parseInt(text.trim());
				} catch (NumberFormatException e) {
					throw new OpmlParseException("windowLeft must be a number");
				}
			} else {
				windowLeft = null;
			}
			break;
		}
		case "windowRight": {
			String trimmed = text.trim();
			if (!trimmed.isEmpty()) {
				try {
					windowRight = Integer.parseInt(text.trim());
				} catch (NumberFormatException e) {
					throw new OpmlParseException("windowRight must be a number");
				}
			} else {
				windowRight = null;
			}
			break;
		}
		case "windowTop": {
			String trimmed = text.trim();
			if (!trimmed.isEmpty()) {
				try {
					windowTop = Integer.parseInt(text.trim());
				} catch (NumberFormatException e) {
					throw new OpmlParseException("windowTop must be a number");
				}
			} else {
				windowTop = null;
			}
			break;
		}
		}
	}

	@Override
	public void endTag(XmlPullParser xpp) throws OpmlParseException {
		stack.poll();
		started = stack.isEmpty();
	}

	@Override
	public Head get() {
		return new Head(title, dateCreated, dateModified, ownerName, ownerEmail, ownerId, docs, expansionState, vertScrollState, windowTop, windowLeft, windowBottom, windowRight);
	}

}
