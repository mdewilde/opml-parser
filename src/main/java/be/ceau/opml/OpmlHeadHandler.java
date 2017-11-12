package be.ceau.opml;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import be.ceau.opml.entity.Head;
import be.ceau.opml.entity.Opml;

final class OpmlHeadHandler implements OpmlSectionHandler {

	private final Opml opml;
	private final Deque<String> stack = new ArrayDeque<>();
	
	OpmlHeadHandler(Opml opml) {
		this.opml = opml;
	}

	@Override
	public void startTag(XmlPullParser xpp) throws OpmlParseException {
		// no nested elements in head
		if (!stack.isEmpty()) {
			throw new OpmlParseException(String.format("head section contains nested element %s inside element %s", xpp.getName(), stack.peek()));
		}
		stack.push(xpp.getName());
	}

	@Override
	public void text(XmlPullParser xpp) throws OpmlParseException {
		if (stack.isEmpty()) {
			// we should be between <head> and first element
			ValidityCheck.requireNoText(xpp, "opml");
			return;
		}
		final String text = xpp.getText();
		final Head head = opml.getHead();
		switch (stack.peek()) {
		case "title":
			head.setTitle(text);
			break;
		case "dateCreated":
			head.setDateCreated(text);
			break;
		case "dateModified":
			head.setDateModified(text);
			break;
		case "ownerName":
			head.setOwnerName(text);
			break;
		case "ownerEmail":
			head.setOwnerEmail(text);
			break;
		case "ownerId":
			head.setOwnerId(text);
			break;
		case "docs":
			head.setDocs(text);
			break;
		case "expansionState":
			List<Integer> expansionState = new ArrayList<>();
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
			head.setExpansionState(expansionState);
			break;
		case "vertScrollState": {
			String trimmed = text.trim();
			if (!trimmed.isEmpty()) {
				try {
					head.setVertScrollState(Integer.parseInt(trimmed.trim()));
				} catch (NumberFormatException e) {
					throw new OpmlParseException("vertScrollState must be a number");
				}
			} else {
				head.setVertScrollState(null);
			}
			break;
		}
		case "windowBottom": {
			String trimmed = text.trim();
			if (!trimmed.isEmpty()) {
				try {
					head.setWindowBottom(Integer.parseInt(text.trim()));
				} catch (NumberFormatException e) {
					throw new OpmlParseException("windowBottom must be a number");
				}
			} else {
				head.setVertScrollState(null);
			}
			break;
		}
		case "windowLeft": {
			String trimmed = text.trim();
			if (!trimmed.isEmpty()) {
				try {
					head.setWindowLeft(Integer.parseInt(text.trim()));
				} catch (NumberFormatException e) {
					throw new OpmlParseException("windowLeft must be a number");
				}
			} else {
				head.setVertScrollState(null);
			}
			break;
		}
		case "windowRight": {
			String trimmed = text.trim();
			if (!trimmed.isEmpty()) {
				try {
					head.setWindowRight(Integer.parseInt(text.trim()));
				} catch (NumberFormatException e) {
					throw new OpmlParseException("windowRight must be a number");
				}
			} else {
				head.setVertScrollState(null);
			}
			break;
		}
		case "windowTop": {
			String trimmed = text.trim();
			if (!trimmed.isEmpty()) {
				try {
					head.setWindowTop(Integer.parseInt(text.trim()));
				} catch (NumberFormatException e) {
					throw new OpmlParseException("windowTop must be a number");
				}
			} else {
				head.setVertScrollState(null);
			}
			break;
		}
		}
	}

	@Override
	public void endTag(XmlPullParser xpp) throws OpmlParseException {
		String opened = stack.poll();
		if (!xpp.getName().equals(opened) && !xpp.getName().equals("head")) {
			throw new OpmlParseException(String.format("expected close of %s but found %s", opened, xpp.getName()));
		}
	}

}
