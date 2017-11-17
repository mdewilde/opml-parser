package be.ceau.opml;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import be.ceau.opml.entity.Opml;

public class SampleReader {

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
