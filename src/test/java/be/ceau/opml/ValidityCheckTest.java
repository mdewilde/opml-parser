/*
	Copyright 2019 Marceau Dewilde <m@ceau.be>
	
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

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;


public class ValidityCheckTest {

	@Test
	public void translateTest() {
		Assert.assertEquals("START_DOCUMENT", ValidityCheck.translate(XmlPullParser.START_DOCUMENT)); 
		Assert.assertEquals("START_TAG", ValidityCheck.translate(XmlPullParser.START_TAG)); 
		Assert.assertEquals("TEXT", ValidityCheck.translate(XmlPullParser.TEXT)); 
		Assert.assertEquals("END_TAG", ValidityCheck.translate(XmlPullParser.END_TAG)); 
		Assert.assertEquals("END_DOCUMENT", ValidityCheck.translate(XmlPullParser.END_DOCUMENT)); 
		Assert.assertEquals("123", ValidityCheck.translate(123)); 
	}

	@Test
	public void requirePosition() {
		XmlPullParser parser = Mockito.mock(XmlPullParser.class);
		try {
			Mockito.when(parser.getEventType()).thenReturn(XmlPullParser.END_TAG);
			ValidityCheck.requirePosition(parser, XmlPullParser.START_TAG);
		} catch (OpmlParseException e) {
			Assert.assertEquals("required position START_TAG but found position END_TAG", e.getMessage());
		} catch (XmlPullParserException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test(expected = OpmlParseException.class)
	public void wrapXmlPullParserException() throws OpmlParseException, XmlPullParserException {
		XmlPullParser parser = Mockito.mock(XmlPullParser.class);
		Mockito.when(parser.getEventType()).thenThrow(XmlPullParserException.class);
		ValidityCheck.requirePosition(parser, XmlPullParser.START_DOCUMENT);
	}

	@Test
	public void isTextBlank() {
		Assert.assertTrue(ValidityCheck.isTextBlank((String) null));
		Assert.assertTrue(ValidityCheck.isTextBlank("   	"));
		Assert.assertTrue(ValidityCheck.isTextBlank(""));
	}

	
}
