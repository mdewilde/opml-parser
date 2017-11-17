package be.ceau.opml;

import org.junit.Assert;
import org.junit.Test;

import be.ceau.opml.entity.Opml;

public class OpmlWriterTest {

	private final OpmlParser parser = new OpmlParser();
	private final OpmlWriter writer = new OpmlWriter();

	@Test
	public void parseAndWrite() throws OpmlParseException, OpmlWriteException {
		test("/category.opml");
		test("/digitalPodcast.opml");
		test("/emptyHeadNumber.opml");
		test("/mySubscriptions.opml");
		test("/placesLived.opml");
		test("/scriptingNewsDirectory.opml");
		test("/states.opml");
		test("/userlandsamples.opml");
	}

	private void test(String file) throws OpmlParseException, OpmlWriteException {
		String sample = SampleReader.read(file);
		Opml opml = parser.parse(sample);
		String resample = writer.write(opml);
		Opml reopml = parser.parse(resample);
		Assert.assertEquals(opml, reopml);
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalidArgument() throws OpmlWriteException {
		writer.write(null);
	}

}
