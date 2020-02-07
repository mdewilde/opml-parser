/*
	Copyright 2020 Marceau Dewilde <m@ceau.be>
	
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

/**
 * Handling strategy for specific sections of an OPML document. Implementations should be assumed not-threadsafe unless
 * explicitly documented otherwise.
 */
interface OpmlSectionHandler<E> {

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

	E get();
	
}
