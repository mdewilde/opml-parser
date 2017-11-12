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
import java.util.ArrayList;
import java.util.List;

/**
 * Metadata of the OPML feed. A {@code <head>} contains zero or more optional elements.
 */
public class Head implements Serializable {

	private static final long serialVersionUID = 1510395890351L;

	private String title;

	private String dateCreated;

	private String dateModified;

	private String ownerName;

	private String ownerEmail;

	private String ownerId;

	private String docs;

	private final List<Integer> expansionState = new ArrayList<>();

	private Integer vertScrollState;

	private Integer windowTop;

	private Integer windowLeft;

	private Integer windowBottom;

	private Integer windowRight;

	/**
	 * The title of the document
	 * 
	 * @return {@link String} or {@code null} if not set
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 * @see #getTitle()
	 */
	public void setTitle(String title) {
		this.title = title;
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
	 * @param dateCreated
	 * @see #getDateCreated()
	 */
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
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
	 * @param dateModified
	 * @see #getDateModified()
	 */
	public void setDateModified(String dateModified) {
		this.dateModified = dateModified;
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
	 * @param ownerName
	 * @see #getOwnerName()
	 */
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
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
	 * @param ownerEmail
	 * @see #getOwnerEmail()
	 */
	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
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
	 * @param ownerId
	 * @see #getOwnerId()
	 */
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
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
	 * @param docs
	 * @see #getDocs()
	 */
	public void setDocs(String docs) {
		this.docs = docs;
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
	 * @param expansionState
	 * @see #getExpansionState()
	 */
	public void setExpansionState(List<Integer> expansionState) {
		this.expansionState.clear();
		if (expansionState != null) {
			this.expansionState.addAll(expansionState);
		}
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
	 * @param windowTop
	 * @see #getWindowTop()
	 */
	public void setVertScrollState(Integer vertScrollState) {
		this.vertScrollState = vertScrollState;
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
	 * @param windowTop
	 * @see #getWindowTop()
	 */
	public void setWindowTop(Integer windowTop) {
		this.windowTop = windowTop;
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
	 * @param windowLeft
	 * @see #getWindowLeft()
	 */
	public void setWindowLeft(Integer windowLeft) {
		this.windowLeft = windowLeft;
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
	 * @param windowBottom
	 * @see #getWindowBottom()
	 */
	public void setWindowBottom(Integer windowBottom) {
		this.windowBottom = windowBottom;
	}

	/**
	 * The pixel location of the right edge of the window
	 * 
	 * @return {@link Integer} or {@code null} if not set
	 */
	public Integer getWindowRight() {
		return windowRight;
	}

	/**
	 * @param windowRight
	 * @see #getWindowRight()
	 */
	public void setWindowRight(Integer windowRight) {
		this.windowRight = windowRight;
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

}
