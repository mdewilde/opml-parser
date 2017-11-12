/*
	Copyright 2017 Marceau Dewilde <m@ceau.be>
	
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
package be.ceau.opml.entity;

import java.io.Serializable;

/**
 * <p>
 * Instances represent a full OPML document.
 * </p>
 * <p>
 * {@code <opml>} is an XML element, with a single required attribute, version; a {@link <head>} element and a
 * {@link <body>} element, both of which are required.
 * </p>
 * <p>
 * Instances of this class are unmodifiable and threadsafe.
 * </p>
 * 
 * @see <a href="http://dev.opml.org/spec2.html">OPML version 2 specification</a>
 */
public class Opml implements Serializable {

	private static final long serialVersionUID = 1510395918756L;

	/**
	 * OPML version
	 */
	private final String version;

	private final Head head;

	private final Body body;

	public Opml(String version, Head head, Body body) {
		if (version == null) {
			throw new IllegalArgumentException("version can not be null");
		}
		if (head == null) {
			throw new IllegalArgumentException("head can not be null");
		}
		if (body == null) {
			throw new IllegalArgumentException("body can not be null");
		}
		this.version = version;
		this.head = head;
		this.body = body;
	}

	public String getVersion() {
		return version;
	}

	public Head getHead() {
		return head;
	}

	public Body getBody() {
		return body;
	}

	@Override
	public String toString() {
		return new StringBuilder()
				.append("Opml [")
				.append(System.lineSeparator())
				.append("\tversion=")
				.append(version)
				.append(System.lineSeparator())
				.append("\thead=")
				.append(head)
				.append(System.lineSeparator())
				.append("\tbody=")
				.append(body)
				.append(System.lineSeparator())
				.append("]")
				.toString();
	}

}
