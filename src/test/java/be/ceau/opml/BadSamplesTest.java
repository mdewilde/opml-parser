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
package be.ceau.opml;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BadSamplesTest {

	@Before
	public void setup() {
		System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "trace");
	}

	@Test(expected = IllegalArgumentException.class)
	public void parseInvalid() throws OpmlParseException {
		new OpmlParser().parse("");
	}

	@Test
	public void badsample01() throws IOException {
		try (Reader reader = read("badsample01.opml")) {
			new OpmlParser().parse(reader);
		} catch (OpmlParseException e) {
			Assert.assertTrue(e.getMessage().startsWith("required element <opml> but found"));
		}
	}

	@Test
	public void badsample02() throws IOException {
		try (Reader reader = read("badsample02.opml")) {
			new OpmlParser().parse(reader);
		} catch (OpmlParseException e) {
			Assert.assertTrue(e.getMessage().startsWith("XML invalid, unclosed tags"));
		}
	}

	@Test
	public void badsample03() throws IOException {
		try (Reader reader = read("badsample03.opml")) {
			new OpmlParser().parse(reader);
		} catch (OpmlParseException e) {
			Assert.assertTrue(e.getMessage().startsWith("vertScrollState must be a number"));
		}
	}

	@Test
	public void badsample04() throws IOException {
		try (Reader reader = read("badsample04.opml")) {
			new OpmlParser().parse(reader);
		} catch (OpmlParseException e) {
			Assert.assertTrue(e.getMessage().startsWith("head section contains nested element"));
		}
	}

	@Test
	public void badsample05() throws IOException {
		try (Reader reader = read("badsample05.opml")) {
			new OpmlParser().parse(reader);
		} catch (OpmlParseException e) {
			Assert.assertTrue(e.getMessage().startsWith("text inside element <opml>"));
		}
	}

	@Test
	public void badsample06() throws IOException {
		try (Reader reader = read("badsample06.opml")) {
			new OpmlParser().parse(reader);
		} catch (OpmlParseException e) {
			Assert.assertTrue(e.getMessage().contains("more than once"));
		}
	}

	@Test
	public void badsample07() throws IOException {
		try (Reader reader = read("badsample07.opml")) {
			new OpmlParser().parse(reader);
		} catch (OpmlParseException e) {
			Assert.assertTrue(e.getMessage().contains("opml element does not have required attribute version"));
		}
	}

	@Test
	public void badsample08() throws IOException {
		try (Reader reader = read("badsample08.opml")) {
			new OpmlParser().parse(reader);
		} catch (OpmlParseException e) {
			Assert.assertTrue(e.getMessage().contains("OPML documents can have only one head section"));
		}
	}

	@Test
	public void badsample9() throws IOException {
		try (Reader reader = read("badsample09.opml")) {
			new OpmlParser().parse(reader);
		} catch (OpmlParseException e) {
			Assert.assertTrue(e.getMessage().contains("OPML documents can have only one body section"));
		}
	}

	@Test
	public void badsample10() throws IOException {
		try (Reader reader = read("badsample10.opml")) {
			new OpmlParser().parse(reader);
		} catch (OpmlParseException e) {
			Assert.assertTrue(e.getMessage().contains("XML invalid, no <head> element"));
		}
	}

	@Test
	public void badsample11() throws IOException {
		try (Reader reader = read("badsample11.opml")) {
			new OpmlParser().parse(reader);
		} catch (OpmlParseException e) {
			Assert.assertTrue(e.getMessage().contains("XML invalid, no <body> element"));
		}
	}

	@Test
	public void badsample12() throws IOException {
		try (Reader reader = read("badsample12.opml")) {
			new OpmlParser().parse(reader);
		} catch (OpmlParseException e) {
			Assert.assertTrue(e.getMessage().contains("expansionState must be a comma-separated list of line numbers"));
		}
	}

	@Test
	public void badsample13() throws IOException {
		try (Reader reader = read("badsample13.opml")) {
			new OpmlParser().parse(reader);
		} catch (OpmlParseException e) {
			Assert.assertTrue(e.getMessage().contains("windowBottom must be a number"));
		}
	}

	@Test
	public void badsample14() throws IOException {
		try (Reader reader = read("badsample14.opml")) {
			new OpmlParser().parse(reader);
		} catch (OpmlParseException e) {
			Assert.assertTrue(e.getMessage().contains("windowLeft must be a number"));
		}
	}

	@Test
	public void badsample15() throws IOException {
		try (Reader reader = read("badsample15.opml")) {
			new OpmlParser().parse(reader);
		} catch (OpmlParseException e) {
			Assert.assertTrue(e.getMessage().contains("windowRight must be a number"));
		}
	}

	@Test
	public void badsample16() throws IOException {
		try (Reader reader = read("badsample16.opml")) {
			new OpmlParser().parse(reader);
		} catch (OpmlParseException e) {
			Assert.assertTrue(e.getMessage().contains("windowTop must be a number"));
		}
	}

	private Reader read(String filename) throws FileNotFoundException {
		return new FileReader(getClass().getResource("/" + filename).getPath());
	}
	
}