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
	public void badsample1() throws OpmlParseException, IOException {
		try (Reader resourceReader = new FileReader(getClass().getResource("/badsample1.opml").getPath())) {
			new OpmlParser().parse(resourceReader);
		} catch (OpmlParseException e) {
			Assert.assertTrue("message is " + e.getMessage(), e.getMessage().startsWith("required name opml"));
			throw e;
		}
	}

	@Test(expected = OpmlParseException.class)
	public void badsample2() throws OpmlParseException, IOException {
		try (Reader resourceReader = new FileReader(getClass().getResource("/badsample2.opml").getPath())) {
			new OpmlParser().parse(resourceReader);
		} catch (OpmlParseException e) {
			Assert.assertTrue("message is " + e.getMessage(), e.getMessage().startsWith("XML invalid, unclosed tags"));
			throw e;
		}
	}

	@Test(expected = OpmlParseException.class)
	public void badsample3() throws OpmlParseException, IOException {
		try (Reader resourceReader = new FileReader(getClass().getResource("/badsample3.opml").getPath())) {
			new OpmlParser().parse(resourceReader);
		} catch (OpmlParseException e) {
			Assert.assertTrue("message is " + e.getMessage(), e.getMessage().startsWith("vertScrollState must be a number"));
			throw e;
		}
	}

	@Test(expected = OpmlParseException.class)
	public void badsample4() throws OpmlParseException, IOException {
		try (Reader resourceReader = new FileReader(getClass().getResource("/badsample4.opml").getPath())) {
			new OpmlParser().parse(resourceReader);
		} catch (OpmlParseException e) {
			Assert.assertTrue("message is " + e.getMessage(), e.getMessage().startsWith("head section contains nested element"));
			throw e;
		}
	}

	@Test(expected = OpmlParseException.class)
	public void badsample5() throws OpmlParseException, IOException {
		try (Reader resourceReader = new FileReader(getClass().getResource("/badsample5.opml").getPath())) {
			new OpmlParser().parse(resourceReader);
		} catch (OpmlParseException e) {
			Assert.assertTrue("message is " + e.getMessage(), e.getMessage().startsWith("element opml should not contain"));
			throw e;
		}
	}

	@Test(expected = OpmlParseException.class)
	public void badsample6() throws OpmlParseException, IOException {
		try (Reader resourceReader = new FileReader(getClass().getResource("/badsample6.opml").getPath())) {
			new OpmlParser().parse(resourceReader);
		} catch (OpmlParseException e) {
			Assert.assertTrue("message is " + e.getMessage(), e.getMessage().contains("more than once"));
			throw e;
		}
	}

	@Test(expected = OpmlParseException.class)
	public void badsample7() throws OpmlParseException, IOException {
		try (Reader resourceReader = new FileReader(getClass().getResource("/badsample7.opml").getPath())) {
			new OpmlParser().parse(resourceReader);
		} catch (OpmlParseException e) {
			Assert.assertTrue("message is " + e.getMessage(), e.getMessage().contains("opml element does not have required attribute version"));
			throw e;
		}
	}

}