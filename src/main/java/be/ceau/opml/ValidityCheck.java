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

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class ValidityCheck {

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
			throw new OpmlParseException(String.format("required element <%s> but found <%s>", name, xpp.getName()));
		}
	}

	static void requireNoText(XmlPullParser xpp, String elementName, boolean insideElement) throws OpmlParseException {
		if (!isTextBlank(xpp)) {
			if (insideElement) {
				throw new OpmlParseException(String.format("text inside element <%s>: \"%s\"", elementName, xpp.getText()));
			}
			throw new OpmlParseException(String.format("required element <%s> but found text: \"%s\"", elementName, xpp.getText()));
		}
	}

	static boolean isTextBlank(XmlPullParser xpp) {
		return isTextBlank(xpp.getText());
	}

	public static boolean isTextBlank(String str) {
		// adapted from Apache commons-lang StringUtils#isBlank(String)
		// licensed under Apache Software License 2.0
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	static String translate(int position) {
		switch (position) {
		case XmlPullParser.START_DOCUMENT:
			return "START_DOCUMENT";
		case XmlPullParser.START_TAG:
			return "START_TAG";
		case XmlPullParser.TEXT:
			return "TEXT";
		case XmlPullParser.END_TAG:
			return "END_TAG";
		case XmlPullParser.END_DOCUMENT:
			return "END_DOCUMENT";
		}
		return String.valueOf(position);
	}

}
