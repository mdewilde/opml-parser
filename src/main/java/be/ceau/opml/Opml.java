package be.ceau.opml;

/**
 * Represents the full OPML document
 */
public class Opml {

	/**
	 * OPML version
	 */
	private String version;

	private Head head = new Head();
	
	/**
	 * Contains the content of the outline. Must have at least one outline element.
	 */
	private Body body = new Body();

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
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
