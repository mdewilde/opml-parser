package be.ceau.opml;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.junit.Test;
import org.xmlpull.v1.XmlPullParserException;

public class ParserTest {

	@Test
	public void parse() {
		long start = System.currentTimeMillis();
		
		Parser parser = new Parser();
		try {

			Reader resourceReader = new FileReader(getClass().getResource("/sample.opml").getPath());

			Opml opml = parser.parse(resourceReader);

			System.out.println(String.valueOf(opml));
			System.out.println("total time " + (System.currentTimeMillis() - start));

		} catch (XmlPullParserException | IOException e) {
			e.printStackTrace();
		}
	
	}

}