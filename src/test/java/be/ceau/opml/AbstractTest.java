package be.ceau.opml;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.junit.Before;
import org.junit.Test;

import be.ceau.opml.entity.Opml;

public abstract class AbstractTest {

	@Before
	public void setup() {
		System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "trace");
	}

	@Test
	public void wrappedTest() throws OpmlParseException {
		try (Reader reader = new FileReader(getClass().getResource(getFile()).getPath())) {
			OpmlParser parser = new OpmlParser();
			Opml opml = parser.parse(reader);
			test(opml);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public abstract String getFile();

	public abstract void test(Opml opml) throws OpmlParseException;

}
