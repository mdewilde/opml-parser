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

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayDeque;
import java.util.Deque;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import be.ceau.opml.entity.Opml;

/**
 * Parser for OPML documents. Instances are threadsafe and reusable.
 */
public class OpmlParser {

	/**
	 * Parse the given {@link String} into an {@link Opml} instance
	 * 
	 * @param string a {@link String} representation of a full and valid OPML
	 *               document
	 * @return {@link Opml} instance, not {@code null}
	 * @throws IllegalArgumentException if argument is {@code null} or invalid
	 * @throws OpmlParseException       if an exception occurs during parsing
	 */
	public Opml parse(String string) throws OpmlParseException {
		if (string == null || string.trim().isEmpty()) {
			throw new IllegalArgumentException("argument can not be null or empty");
		}
		try (Reader reader = new StringReader(string)) {
			return parse(reader);
		} catch (IOException e) {
			throw new OpmlParseException(e);
		}
	}

	/**
	 * Parse the given {@link InputStream} into an {@link Opml} instance. Note that
	 * the caller is responsible for closing the given {@link InputStream}.
	 * 
	 * @param input an {@link InputStream} over a valid OPML document
	 * @return {@link Opml} instance, not {@code null}
	 * @throws IllegalArgumentException if argument is {@code null}
	 * @throws OpmlParseException       if argument invalid or if an exception
	 *                                  occurs during parsing
	 */
	public Opml parse(InputStream input) throws OpmlParseException {
		if (input == null) {
			throw new IllegalArgumentException("argument can not be null");
		}
		try (Reader reader = new InputStreamReader(input, StandardCharsets.UTF_8)) {
			return parse(reader);
		} catch (IOException e) {
			throw new OpmlParseException(e);
		}
	}

	/**
	 * Parse the given {@link Reader} into an {@link Opml} instance. Note that the
	 * caller is responsible for closing the given {@link Reader}.
	 * 
	 * @param reader a {@link Reader} over a valid OPML document
	 * @return {@link Opml} instance, not {@code null}
	 * @throws IllegalArgumentException if argument is {@code null}
	 * @throws OpmlParseException       if argument invalid or if an exception
	 *                                  occurs during parsing
	 */
	public Opml parse(Reader reader) throws OpmlParseException {
		if (reader == null) {
			throw new IllegalArgumentException("argument can not be null");
		}
		try {
			return extract(reader);
		} catch (XmlPullParserException | IOException e) {
			throw new OpmlParseException(e);
		}
	}

	private Opml extract(Reader reader) throws XmlPullParserException, IOException, OpmlParseException {

		final XmlPullParser xpp = newXmlPullParser(reader);

		OpmlInitHandler initHandler = new OpmlInitHandler();
		OpmlHeadHandler headHandler = new OpmlHeadHandler();
		OpmlBodyHandler bodyHandler = new OpmlBodyHandler();

		OpmlSectionHandler<?> handler = initHandler;

		final Deque<String> stack = new ArrayDeque<>();
		boolean startedOpml = false;
		boolean startedHead = false;
		boolean startedBody = false;

		while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {

			switch (xpp.next()) {
			case XmlPullParser.START_TAG: {
				final String name = xpp.getName();
				stack.push(name);
				switch (name) {
				case "opml": {
					if (startedOpml) {
						throw new OpmlParseException("OPML documents can have only one opml section");
					}
					handler.startTag(xpp);
					startedOpml = true;
					break;
				}
				case "head": {
					if (startedHead) {
						throw new OpmlParseException("OPML documents can have only one head section");
					}
					handler = headHandler;
					startedHead = true;
					break;
				}
				case "body": {
					if (startedBody) {
						throw new OpmlParseException("OPML documents can have only one body section");
					}
					handler = bodyHandler;
					handler.startTag(xpp);
					startedBody = true;
					break;
				}
				default: {
					handler.startTag(xpp);
					break;
				}
				}
				ValidityCheck.requirePosition(xpp, XmlPullParser.START_TAG);
				break;
			}
			case XmlPullParser.TEXT: {
				handler.text(xpp);
				ValidityCheck.requirePosition(xpp, XmlPullParser.TEXT);
				break;
			}
			case XmlPullParser.END_TAG: {
				String ended = xpp.getName();
				stack.pop();
				switch (ended) {
				case "head":
					handler.endTag(xpp);
					handler = initHandler;
					break;
				case "body":
					handler.endTag(xpp);
					handler = initHandler;
					break;
				default: {
					handler.endTag(xpp);
					break;
				}
				}
				ValidityCheck.requirePosition(xpp, XmlPullParser.END_TAG);
				break;
			}
			}

		}

		if (!stack.isEmpty()) {
			throw new OpmlParseException(String.format("XML invalid, unclosed tags %s", stack));
		}
		if (!startedOpml) {
			throw new OpmlParseException("XML invalid, no <opml> element");
		}
		if (!startedHead) {
			throw new OpmlParseException("XML invalid, no <head> element");
		}
		if (!startedBody) {
			throw new OpmlParseException("XML invalid, no <body> element");
		}

		return new Opml(initHandler.get(), headHandler.get(), bodyHandler.get());

	}

	private XmlPullParser newXmlPullParser(Reader reader) throws XmlPullParserException {
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		factory.setNamespaceAware(true);
		XmlPullParser xpp = factory.newPullParser();
		xpp.setInput(reader);
		return xpp;
	}

}
