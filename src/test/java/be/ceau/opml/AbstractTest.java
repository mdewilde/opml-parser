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
