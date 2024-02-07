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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import be.ceau.opml.entity.Opml;

public class SampleReader {

	private SampleReader() {
		// static only
	}

	public static String read(String filename) {
		String path = SampleReader.class.getResource(filename).getPath();
		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			String line = null;
			StringBuilder stringBuilder = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(System.lineSeparator());
			}
			return stringBuilder.toString();
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	public static Opml parse(String filename) throws OpmlParseException {
		String sample = read(filename);
		return new OpmlParser().parse(sample);
	}

}
