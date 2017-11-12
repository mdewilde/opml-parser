package be.ceau.opml;

import org.xmlpull.v1.XmlPullParser;

import be.ceau.opml.entity.Opml;

final class OpmlInitHandler implements OpmlSectionHandler {

	private final Opml opml;

	OpmlInitHandler(Opml opml) {
		this.opml = opml;
	}

	@Override
	public void startTag(XmlPullParser xpp) throws OpmlParseException {
		ValidityCheck.require(xpp, XmlPullParser.START_TAG, "opml");
		opml.setVersion(xpp.getAttributeValue(null, "version"));
		if (opml.getVersion() == null) {
			throw new OpmlParseException("opml element does not have required attribute version");
		}
	}

	@Override
	public void text(XmlPullParser xpp) throws OpmlParseException {
		ValidityCheck.requireNoText(xpp, "opml");
	}

	@Override
	public void endTag(XmlPullParser xpp) throws OpmlParseException {
		// do nothing
	}

}
