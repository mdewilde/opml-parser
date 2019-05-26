/*
	Copyright 2019 Marceau Dewilde <m@ceau.be>
	
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
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Metadata of the OPML feed. A {@code <head>} contains zero or more optional elements.
 */
public class Head implements Serializable {

	private static final long serialVersionUID = 1510395890351L;

	private final String title;

	private final String dateCreated;

	private final String dateModified;

	private final String ownerName;

	private final String ownerEmail;

	private final String ownerId;

	private final String docs;

	private final List<Integer> expansionState;

	private final Integer vertScrollState;

	private final Integer windowTop;

	private final Integer windowLeft;

	private final Integer windowBottom;

	private final Integer windowRight;

	public Head(String title, String dateCreated, String dateModified, String ownerName, String ownerEmail,
			String ownerId, String docs, List<Integer> expansionState, Integer vertScrollState, Integer windowTop,
			Integer windowLeft, Integer windowBottom, Integer windowRight) {
		this.title = title;
		this.dateCreated = dateCreated;
		this.dateModified = dateModified;
		this.ownerName = ownerName;
		this.ownerEmail = ownerEmail;
		this.ownerId = ownerId;
		this.docs = docs;
		if (expansionState == null) {
			this.expansionState = Collections.emptyList();
		} else {
			this.expansionState = Collections.unmodifiableList(expansionState);
		}
		this.vertScrollState = vertScrollState;
		this.windowTop = windowTop;
		this.windowLeft = windowLeft;
		this.windowBottom = windowBottom;
		this.windowRight = windowRight;
	}

	/**
	 * The title of the document
	 * 
	 * @return {@link String} or {@code null} if not set
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * A date-time, indicating when the document was created (RFC 822 date)
	 * 
	 * @return {@link String} or {@code null} if not set
	 */
	public String getDateCreated() {
		return dateCreated;
	}

	/**
	 * A date-time, indicating when the document was last modified (RFC 822 date)
	 * 
	 * @return {@link String} or {@code null} if not set
	 */
	public String getDateModified() {
		return dateModified;
	}

	/**
	 * The owner of the document
	 * 
	 * @return {@link String} or {@code null} if not set
	 */
	public String getOwnerName() {
		return ownerName;
	}

	/**
	 * The email address of the owner of the document
	 * 
	 * @return {@link String} or {@code null} if not set
	 */
	public String getOwnerEmail() {
		return ownerEmail;
	}

	/**
	 * The http address of a web page that contains information that allows a human reader to communicate with the
	 * author of the document via email or other means. It also may be used to identify the author. No two authors have
	 * the same ownerId.
	 * 
	 * @return {@link String} or {@code null} if not set
	 */
	public String getOwnerId() {
		return ownerId;
	}

	/**
	 * The http address of documentation for the format used in the OPML file.It's probably a pointer to this page for
	 * people who might stumble across the file on a web server 25 years from now and wonder what it is.
	 * 
	 * @return {@link String} or {@code null} if not set
	 */
	public String getDocs() {
		return docs;
	}

	/**
	 * A comma-separated list of line numbers that are expanded. The line numbers in the list tell you which headlines
	 * to expand. The order is important. For each element in the list, X, starting at the first summit, navigate
	 * flatdown X times and expand. Repeat for each element in the list.
	 * 
	 * @return mutable {@link List}, never {@code null}
	 */
	public List<Integer> getExpansionState() {
		return expansionState;
	}

	/**
	 * @return serialized form of expansionState element
	 * @see #getExpansionState()
	 */
	public String getExpansionStateString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < expansionState.size(); i++) {
			if (i > 0) {
				sb.append(", ");
			}
			sb.append(expansionState.get(i));
		}
		return sb.toString();
	}

	/**
	 * Line of the outline is displayed on the top line of the window. This number is calculated with the expansion
	 * state already applied.
	 * 
	 * @return {@link Integer} or {@code null} if not set
	 */
	public Integer getVertScrollState() {
		return vertScrollState;
	}

	/**
	 * The pixel location of the top edge of the window
	 * 
	 * @return {@link Integer} or {@code null} if not set
	 */
	public Integer getWindowTop() {
		return windowTop;
	}

	/**
	 * The pixel location of the left edge of the window
	 * 
	 * @return {@link Integer} or {@code null} if not set
	 */
	public Integer getWindowLeft() {
		return windowLeft;
	}

	/**
	 * The pixel location of the bottom edge of the window
	 * 
	 * @return {@link Integer} or {@code null} if not set
	 */
	public Integer getWindowBottom() {
		return windowBottom;
	}

	/**
	 * The pixel location of the right edge of the window
	 * 
	 * @return {@link Integer} or {@code null} if not set
	 */
	public Integer getWindowRight() {
		return windowRight;
	}

	@Override
	public String toString() {
		return new StringBuilder()
				.append("Head [")
				.append(System.lineSeparator())
				.append("\ttitle=")
				.append(title)
				.append(System.lineSeparator())
				.append("\tdateCreated=")
				.append(dateCreated)
				.append(System.lineSeparator())
				.append("\tdateModified=")
				.append(dateModified)
				.append(System.lineSeparator())
				.append("\townerName=")
				.append(ownerName)
				.append(System.lineSeparator())
				.append("\townerEmail=")
				.append(ownerEmail)
				.append(System.lineSeparator())
				.append("\townerId=")
				.append(ownerId)
				.append(System.lineSeparator())
				.append("\tdocs=")
				.append(docs)
				.append(System.lineSeparator())
				.append("\texpansionState=")
				.append(expansionState)
				.append(System.lineSeparator())
				.append("\tvertScrollState=")
				.append(vertScrollState)
				.append(System.lineSeparator())
				.append("\twindowTop=")
				.append(windowTop)
				.append(System.lineSeparator())
				.append("\twindowLeft=")
				.append(windowLeft)
				.append(System.lineSeparator())
				.append("\twindowBottom=")
				.append(windowBottom)
				.append(System.lineSeparator())
				.append("\twindowRight=")
				.append(windowRight)
				.append("]")
				.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Objects.hashCode(dateCreated);
		result = prime * result + Objects.hashCode(dateModified);
		result = prime * result + Objects.hashCode(docs);
		result = prime * result + Objects.hashCode(expansionState);
		result = prime * result + Objects.hashCode(ownerEmail);
		result = prime * result + Objects.hashCode(ownerId);
		result = prime * result + Objects.hashCode(ownerName);
		result = prime * result + Objects.hashCode(title);
		result = prime * result + Objects.hashCode(vertScrollState);
		result = prime * result + Objects.hashCode(windowBottom);
		result = prime * result + Objects.hashCode(windowLeft);
		result = prime * result + Objects.hashCode(windowRight);
		result = prime * result + Objects.hashCode(windowTop);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Head other = (Head) obj;
		if (!Objects.equals(dateCreated, other.dateCreated)) {
			return false;
		}
		if (!Objects.equals(dateModified, other.dateModified)) {
			return false;
		}
		if (!Objects.equals(docs, other.docs)) {
			return false;
		}
		if (!Objects.equals(expansionState, other.expansionState)) {
			return false;
		}
		if (!Objects.equals(ownerEmail, other.ownerEmail)) {
			return false;
		}
		if (!Objects.equals(ownerId, other.ownerId)) {
			return false;
		}
		if (!Objects.equals(ownerName, other.ownerName)) {
			return false;
		}
		if (!Objects.equals(title, other.title)) {
			return false;
		}
		if (!Objects.equals(vertScrollState, other.vertScrollState)) {
			return false;
		}
		if (!Objects.equals(windowBottom, other.windowBottom)) {
			return false;
		}
		if (!Objects.equals(windowLeft, other.windowLeft)) {
			return false;
		}
		if (!Objects.equals(windowRight, other.windowRight)) {
			return false;
		}
		if (!Objects.equals(windowTop, other.windowTop)) {
			return false;
		}
		return true;
	}

}
