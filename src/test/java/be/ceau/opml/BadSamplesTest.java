/*
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

	@Test(expected = OpmlParseException.class)
	public void badTagInHead() throws IOException, OpmlParseException {
		assertExceptionWithMessage("badTagInHead.opml", "encountered non-namespaced element");
	}

	@Test(expected = OpmlParseException.class)
	public void doubleTagInHead() throws IOException, OpmlParseException {
		assertExceptionWithMessage("doubleTagInHead.opml", "more than once inside <head>");
	}

	@Test(expected = OpmlParseException.class)
	public void badsample01() throws IOException, OpmlParseException {
		assertExceptionWithMessage("badsample01.opml", "encountered non-namespaced element");
	}

	@Test(expected = OpmlParseException.class)
	public void badsample02() throws IOException, OpmlParseException {
		assertExceptionWithMessage("badsample02.opml", "XML invalid, unclosed tags");
	}

	@Test(expected = OpmlParseException.class)
	public void badsample03() throws IOException, OpmlParseException {
		assertExceptionWithMessage("badsample03.opml", "vertScrollState must be a number");
	}

	@Test(expected = OpmlParseException.class)
	public void badsample04() throws IOException, OpmlParseException {
		assertExceptionWithMessage("badsample04.opml", "head section contains nested element");
	}

	@Test(expected = OpmlParseException.class)
	public void badsample05() throws IOException, OpmlParseException {
		assertExceptionWithMessage("badsample05.opml", "text inside element <opml>");
	}

	@Test(expected = OpmlParseException.class)
	public void badsample06() throws IOException, OpmlParseException {
		assertExceptionWithMessage("badsample06.opml", "more than once");
	}

	@Test(expected = OpmlParseException.class)
	public void badsample07() throws IOException, OpmlParseException {
		assertExceptionWithMessage("badsample07.opml", "opml element does not have required attribute version");
	}

	@Test(expected = OpmlParseException.class)
	public void badsample08() throws IOException, OpmlParseException {
		assertExceptionWithMessage("badsample08.opml", "OPML documents can have only one head section");
	}

	@Test(expected = OpmlParseException.class)
	public void badsample9() throws IOException, OpmlParseException {
		assertExceptionWithMessage("badsample09.opml", "OPML documents can have only one body section");
	}

	@Test(expected = OpmlParseException.class)
	public void badsample10() throws IOException, OpmlParseException {
		assertExceptionWithMessage("badsample10.opml", "XML invalid, no <head> element");
	}

	@Test(expected = OpmlParseException.class)
	public void badsample11() throws IOException, OpmlParseException {
		assertExceptionWithMessage("badsample11.opml", "XML invalid, no <body> element");
	}

	@Test(expected = OpmlParseException.class)
	public void badsample12() throws IOException, OpmlParseException {
		assertExceptionWithMessage("badsample12.opml", "expansionState must be a comma-separated list of line numbers");
	}

	@Test(expected = OpmlParseException.class)
	public void badsample13() throws IOException, OpmlParseException {
		assertExceptionWithMessage("badsample13.opml", "windowBottom must be a number");
	}

	@Test(expected = OpmlParseException.class)
	public void badsample14() throws IOException, OpmlParseException {
		assertExceptionWithMessage("badsample14.opml", "windowLeft must be a number");
	}

	@Test(expected = OpmlParseException.class)
	public void badsample15() throws IOException, OpmlParseException {
		assertExceptionWithMessage("badsample15.opml", "windowRight must be a number");
	}

	@Test(expected = OpmlParseException.class)
	public void badsample16() throws IOException, OpmlParseException {
		assertExceptionWithMessage("badsample16.opml", "windowTop must be a number");
	}

	@Test(expected = OpmlParseException.class)
	public void badsample17() throws IOException, OpmlParseException {
		assertExceptionWithMessage("badsample17.opml", "text inside element");
	}

	private void assertExceptionWithMessage(String opmlFileName, String message) throws IOException, OpmlParseException {
		try (Reader reader = read(opmlFileName)) {
			new OpmlParser().parse(reader);
		} catch (OpmlParseException e) {
			Assert.assertTrue(e.getMessage().contains(message));
			throw e;
		}
	}

	private Reader read(String filename) throws FileNotFoundException {
		return new FileReader(getClass().getResource("/" + filename).getPath());
	}

}