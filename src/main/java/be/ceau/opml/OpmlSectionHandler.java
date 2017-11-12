package be.ceau.opml;

import org.xmlpull.v1.XmlPullParser;

/**
 * Handling strategy for specific sections of an OPML document. Implementations should be assumed not-threadsafe unless
 * explicitly documented otherwise.
 */
interface OpmlSectionHandler {

	/**
	 * Handle {@link XmlPullParser#START_TAG} event. The {@link XmlPullParser} should not be moved by this method.
	 * 
	 * @param xpp
	 *            an {@link XmlPullParser} instance, set at {@link XmlPullParser#START_TAG}
	 * @throws OpmlParseException
	 */
	void startTag(XmlPullParser xpp) throws OpmlParseException;

	/**
	 * Handle {@link XmlPullParser#TEXT} event. The {@link XmlPullParser} should not be moved by this method.
	 * 
	 * @param xpp
	 *            an {@link XmlPullParser} instance, set at {@link XmlPullParser#TEXT}
	 * @throws OpmlParseException
	 */
	void text(XmlPullParser xpp) throws OpmlParseException;

	/**
	 * Handle {@link XmlPullParser#END_TAG} event. The {@link XmlPullParser} should not be moved by this method.
	 * 
	 * @param xpp
	 *            an {@link XmlPullParser} instance, set at {@link XmlPullParser#END_TAG}
	 * @throws OpmlParseException
	 */
	void endTag(XmlPullParser xpp) throws OpmlParseException;

}
