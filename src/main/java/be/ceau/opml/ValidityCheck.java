package be.ceau.opml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

class ValidityCheck {

	static void require(XmlPullParser xpp, int position, String name) throws OpmlParseException {
		requirePosition(xpp, position);
		requireName(xpp, name);
	}

	static void requirePosition(XmlPullParser xpp, int position) throws OpmlParseException {
		try {
			if (xpp.getEventType() != position) {
				throw new OpmlParseException(String.format("required position %s but found position %s", translate(position), translate(xpp.getEventType())));
			}
		} catch (XmlPullParserException e) {
			throw new OpmlParseException(e);
		}
	}
	
	static void requireName(XmlPullParser xpp, String name) throws OpmlParseException {
		if (!xpp.getName().equals(name)) {
			throw new OpmlParseException(String.format("required name %s but found name %s", name, xpp.getName()));
		}
	}

	static void requireNoText(XmlPullParser xpp, String name) throws OpmlParseException {
		String text = xpp.getText();
		if (text != null) {
			text = text.trim();
			if (!text.isEmpty()) {
				throw new OpmlParseException(String.format("element %s should not contain text but contains %s", name, text));
			}
		}
	}

	private static String translate(int position) {
		switch (position) {
			case XmlPullParser.START_DOCUMENT : 
				return "START_DOCUMENT";
			case XmlPullParser.START_TAG : 
				return "START_TAG";
			case XmlPullParser.TEXT : 
				return "TEXT";
			case XmlPullParser.END_TAG : 
				return "END_TAG";
			case XmlPullParser.END_DOCUMENT : 
				return "END_DOCUMENT";
		}
		return String.valueOf(position);
	}

}
