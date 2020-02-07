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

import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map.Entry;

import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import be.ceau.opml.entity.Body;
import be.ceau.opml.entity.Head;
import be.ceau.opml.entity.Opml;
import be.ceau.opml.entity.Outline;

/**
 * Writer for OPML documents. Instances are threadsafe and reusable.
 */
public class OpmlWriter {

	private static final String SINGLE_INDENT = "  ";
	private static final String DOUBLE_INDENT = "    ";

	/**
	 * Write the given {@link Opml} instance into valid XML {@link String}
	 * 
	 * @param opml
	 *            an {@link Opml} instance
	 * @return {@link String}, not {@code null}
	 * @throws IllegalArgumentException
	 *             if argument is {@code null}
	 * @throws OpmlWriteException
	 *             if an exception occurs while writing
	 */
	public String write(Opml opml) throws OpmlWriteException {
		if (opml == null) {
			throw new IllegalArgumentException("argument can not be null");
		}
		try {
	        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
	        XmlSerializer serializer = factory.newSerializer();
	        StringWriter stringWriter = new StringWriter();
	        serializer.setOutput(stringWriter);
	        serializer.startDocument(StandardCharsets.UTF_8.name(), null);
	        serializer.text(System.lineSeparator());
	        writeOpml(serializer, opml);
	        serializer.endDocument();
	        return stringWriter.toString();
		} catch (IllegalArgumentException | IllegalStateException | IOException | XmlPullParserException e) {
			throw new OpmlWriteException(e);
		}

	}

	private void writeOpml(XmlSerializer serializer, Opml opml) throws IllegalArgumentException, IllegalStateException, IOException {
		serializer.startTag(null, "opml");
		serializer.attribute(null, "version", opml.getVersion());
		serializer.text(System.lineSeparator());
		writeHead(serializer, opml.getHead());
		writeBody(serializer, opml.getBody());
		serializer.endTag(null, "opml");
		serializer.text(System.lineSeparator());
	}
	
	private void writeHead(XmlSerializer serializer, Head head) throws IllegalArgumentException, IllegalStateException, IOException {
		serializer.text(SINGLE_INDENT);
		serializer.startTag(null, "head");
		serializer.text(System.lineSeparator());
		
		if (!ValidityCheck.isTextBlank(head.getTitle())) {
			serializer.text(DOUBLE_INDENT);
			serializer.startTag(null, "title");
			serializer.text(head.getTitle());
			serializer.endTag(null, "title");
			serializer.text(System.lineSeparator());
		}
		if (!ValidityCheck.isTextBlank(head.getDateCreated())) {
			serializer.text(DOUBLE_INDENT);
			serializer.startTag(null, "dateCreated");
			serializer.text(head.getDateCreated());
			serializer.endTag(null, "dateCreated");
			serializer.text(System.lineSeparator());
		}
		if (!ValidityCheck.isTextBlank(head.getDateModified())) {
			serializer.text(DOUBLE_INDENT);
			serializer.startTag(null, "dateModified");
			serializer.text(head.getDateModified());
			serializer.endTag(null, "dateModified");
			serializer.text(System.lineSeparator());
		}
		if (!ValidityCheck.isTextBlank(head.getOwnerName())) {
			serializer.text(DOUBLE_INDENT);
			serializer.startTag(null, "ownerName");
			serializer.text(head.getOwnerName());
			serializer.endTag(null, "ownerName");
			serializer.text(System.lineSeparator());
		}
		if (!ValidityCheck.isTextBlank(head.getOwnerEmail())) {
			serializer.text(DOUBLE_INDENT);
			serializer.startTag(null, "ownerEmail");
			serializer.text(head.getOwnerEmail());
			serializer.endTag(null, "ownerEmail");
			serializer.text(System.lineSeparator());
		}
		if (!ValidityCheck.isTextBlank(head.getOwnerId())) {
			serializer.text(DOUBLE_INDENT);
			serializer.startTag(null, "ownerId");
			serializer.text(head.getOwnerId());
			serializer.endTag(null, "ownerId");
			serializer.text(System.lineSeparator());
		}
		if (!ValidityCheck.isTextBlank(head.getDocs())) {
			serializer.text(DOUBLE_INDENT);
			serializer.startTag(null, "docs");
			serializer.text(head.getDocs());
			serializer.endTag(null, "docs");
			serializer.text(System.lineSeparator());
		}
		if (!head.getExpansionState().isEmpty()) {
			serializer.text(DOUBLE_INDENT);
			serializer.startTag(null, "expansionState");
			serializer.text(head.getExpansionStateString());
			serializer.endTag(null, "expansionState");
			serializer.text(System.lineSeparator());
		}
		if (head.getVertScrollState() != null) {
			serializer.text(DOUBLE_INDENT);
			serializer.startTag(null, "vertScrollState");
			serializer.text(head.getVertScrollState().toString());
			serializer.endTag(null, "vertScrollState");
			serializer.text(System.lineSeparator());
		}
		if (head.getWindowTop() != null) {
			serializer.text(DOUBLE_INDENT);
			serializer.startTag(null, "windowTop");
			serializer.text(head.getWindowTop().toString());
			serializer.endTag(null, "windowTop");
			serializer.text(System.lineSeparator());
		}
		if (head.getWindowLeft() != null) {
			serializer.text(DOUBLE_INDENT);
			serializer.startTag(null, "windowLeft");
			serializer.text(head.getWindowLeft().toString());
			serializer.endTag(null, "windowLeft");
			serializer.text(System.lineSeparator());
		}
		if (head.getWindowBottom() != null) {
			serializer.text(DOUBLE_INDENT);
			serializer.startTag(null, "windowBottom");
			serializer.text(head.getWindowBottom().toString());
			serializer.endTag(null, "windowBottom");
			serializer.text(System.lineSeparator());
		}
		if (head.getWindowRight() != null) {
			serializer.text(DOUBLE_INDENT);
			serializer.startTag(null, "windowRight");
			serializer.text(head.getWindowRight().toString());
			serializer.endTag(null, "windowRight");
			serializer.text(System.lineSeparator());
		}
		serializer.text(SINGLE_INDENT);
		serializer.endTag(null, "head");
		serializer.text(System.lineSeparator());
	}

	private void writeBody(XmlSerializer serializer, Body body) throws IllegalArgumentException, IllegalStateException, IOException {
		serializer.text(SINGLE_INDENT);
		serializer.startTag(null, "body");
		serializer.text(System.lineSeparator());
		for (Outline outline : body.getOutlines()) {
			writeOutline(serializer, outline);
		}
		serializer.text(SINGLE_INDENT);
		serializer.endTag(null, "body");
		serializer.text(System.lineSeparator());
	}

	private void writeOutline(XmlSerializer serializer, Outline outline) throws IllegalArgumentException, IllegalStateException, IOException {
		serializer.text(DOUBLE_INDENT);
		serializer.startTag(null, "outline");
		for (Entry<String, String> entry : outline.getAttributes().entrySet()) {
			serializer.attribute(null, entry.getKey(), entry.getValue());
		}
		for (Outline subElement : outline.getSubElements()) {
			writeOutline(serializer, subElement);
		}
		serializer.endTag(null, "outline");
		serializer.text(System.lineSeparator());
	}

}
