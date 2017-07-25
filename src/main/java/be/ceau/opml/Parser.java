package be.ceau.opml;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayDeque;
import java.util.Deque;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class Parser {

	public Opml parse(String string) throws XmlPullParserException, IOException {
		return parse(new StringReader(string));
	}

	public Opml parse(InputStream input) throws XmlPullParserException, IOException {
		return parse(new InputStreamReader(input));
	}
	
	public Opml parse(Reader reader) throws XmlPullParserException, IOException {

		Opml opml = new Opml();

		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		factory.setNamespaceAware(true);
		XmlPullParser xpp = factory.newPullParser();
		xpp.setInput(reader);
		int event = xpp.getEventType();

		Deque<Outline> outlines = new ArrayDeque<>();
		
		while (event != XmlPullParser.END_DOCUMENT) {
			switch (event) {
			case XmlPullParser.START_TAG:
				switch (xpp.getName()) {

				case "opml":
					opml.setVersion(xpp.getAttributeValue(null, "version"));
					break;
				case "head":
					break;
				case "title":
					opml.getHead().setTitle(xpp.nextText());
					break;
				case "dateCreated":
					opml.getHead().setDateCreated(xpp.nextText());
					break;
				case "dateModified":
					opml.getHead().setDateModified(xpp.nextText());
					break;
				case "expansionState":
					opml.getHead().setExpansionState(xpp.nextText());
					break;
				case "ownerEmail":
					opml.getHead().setOwnerEmail(xpp.nextText());
					break;
				case "ownerName":
					opml.getHead().setOwnerEmail(xpp.nextText());
					break;
				case "vertScrollState":
					opml.getHead().setVertScrollState(xpp.nextText());
					break;
				case "windowBottom":
					opml.getHead().setWindowBottom(xpp.nextText());
					break;
				case "windowLeft":
					opml.getHead().setWindowLeft(xpp.nextText());
					break;
				case "windowRight":
					opml.getHead().setWindowRight(xpp.nextText());
					break;
				case "windowTop":
					opml.getHead().setWindowTop(xpp.nextText());
					break;
				case "outline":
					Outline outline = new Outline();
					for (int i = 0; i < xpp.getAttributeCount(); i++) {
						outline.putAttribute(xpp.getAttributeName(i), xpp.getAttributeValue(i));
					}
					if (outlines.isEmpty()) {
						opml.getBody().addOutline(outline);
					} else {
						outlines.peek().addSubElement(outline);
					}
					outlines.push(outline);
					break;
				}
				break;
			case XmlPullParser.END_TAG:
				switch (xpp.getName()) {
					case "outline":
					outlines.pop();
				}
			}
			event = xpp.next();
		}
		return opml;
	}
}
