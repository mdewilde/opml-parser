package be.ceau.opml;

import java.util.ArrayDeque;
import java.util.Deque;

import org.xmlpull.v1.XmlPullParser;

import be.ceau.opml.entity.Opml;
import be.ceau.opml.entity.Outline;

final class OpmlBodyHandler implements OpmlSectionHandler {

	private final Deque<Outline> outlines = new ArrayDeque<>();

	private final Opml opml;

	OpmlBodyHandler(Opml opml) {
		this.opml = opml;
	}

	@Override
	public void startTag(XmlPullParser xpp) throws OpmlParseException {
		ValidityCheck.require(xpp, XmlPullParser.START_TAG, "outline");

		Outline outline = parseOutline(xpp);
		if (outlines.isEmpty()) {
			// this outline is a child of <body>
			opml.getBody().addOutline(outline);
		} else {
			// this outline is nested in a different <outline>
			outlines.peek().addSubElement(outline);
		}

		outlines.push(outline);

	}

	@Override
	public void text(XmlPullParser xpp) throws OpmlParseException {
		ValidityCheck.requireNoText(xpp, outlines.isEmpty() ? "body" : "outline");
	}

	@Override
	public void endTag(XmlPullParser xpp) throws OpmlParseException {
		if (!outlines.isEmpty()) {
			outlines.pop();
		} else if (!xpp.getName().equals("body")) {
			throw new OpmlParseException(String.format("found </%s> but expected </outline> or </body>", xpp.getName()));
		}
	}

	private Outline parseOutline(XmlPullParser xpp) {
		Outline outline = new Outline();
		for (int i = 0; i < xpp.getAttributeCount(); i++) {
			outline.putAttribute(xpp.getAttributeName(i), xpp.getAttributeValue(i));
		}
		return outline;
	}

}
