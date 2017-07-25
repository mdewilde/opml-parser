package be.ceau.opml;

/**
 * Metadata of the OPML feed.
 * 
 * An OPML processor may ignore all the head sub-elements. If the outline is
 * opened inside another outline then the processor must ignore the window
 * elements.
 */
public class Head {

	private String title;

	/**
	 * RFC 822 date
	 */
	private String dateCreated;

	/**
	 * RFC 822 date
	 */
	private String dateModified;
	private String ownerName;
	private String ownerEmail;

	/**
	 * A comma-separated list of line numbers that should be expanded on display
	 */
	private String expansionState;
	private String vertScrollState;

	/**
	 * position and size of the display window
	 */
	private String windowTop;
	/**
	 * position and size of the display window
	 */
	private String windowLeft;
	/**
	 * position and size of the display window
	 */
	private String windowBottom;
	/**
	 * position and size of the display window
	 */
	private String windowRight;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getDateModified() {
		return dateModified;
	}

	public void setDateModified(String dateModified) {
		this.dateModified = dateModified;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerEmail() {
		return ownerEmail;
	}

	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}

	public String getExpansionState() {
		return expansionState;
	}

	public void setExpansionState(String expansionState) {
		this.expansionState = expansionState;
	}

	public String getVertScrollState() {
		return vertScrollState;
	}

	public void setVertScrollState(String vertScrollState) {
		this.vertScrollState = vertScrollState;
	}

	public String getWindowTop() {
		return windowTop;
	}

	public void setWindowTop(String windowTop) {
		this.windowTop = windowTop;
	}

	public String getWindowLeft() {
		return windowLeft;
	}

	public void setWindowLeft(String windowLeft) {
		this.windowLeft = windowLeft;
	}

	public String getWindowBottom() {
		return windowBottom;
	}

	public void setWindowBottom(String windowBottom) {
		this.windowBottom = windowBottom;
	}

	public String getWindowRight() {
		return windowRight;
	}

	public void setWindowRight(String windowRight) {
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
