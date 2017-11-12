package be.ceau.opml;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import be.ceau.opml.entity.Head;

final class OpmlHeadHandler implements OpmlSectionHandler<Head> {

	private final Deque<String> stack = new ArrayDeque<>();

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
		// no nested elements in head
		if (!stack.isEmpty()) {
			throw new OpmlParseException(String.format("head section contains nested element %s inside element %s",
					xpp.getName(), stack.peek()));
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
				vertScrollState = null;
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
				vertScrollState = null;
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
				vertScrollState = null;
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
				vertScrollState = null;
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

	@Override
	public Head get() {
		return new Head(title, dateCreated, dateModified, ownerName, ownerEmail, ownerId, docs, expansionState,
				vertScrollState, windowTop, windowLeft, windowBottom, windowRight);
	}

}
