package be.ceau.opml;

import org.xmlpull.v1.XmlPullParser;

final class OpmlInitHandler implements OpmlSectionHandler<String> {

	private String version;

	@Override
	public void startTag(XmlPullParser xpp) throws OpmlParseException {
		ValidityCheck.require(xpp, XmlPullParser.START_TAG, "opml");
		version = xpp.getAttributeValue(null, "version");
		if (version == null) {
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

	@Override
	public String get() {
		return version;
	}

}
