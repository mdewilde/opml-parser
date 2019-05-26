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

import be.ceau.opml.entity.Opml;

public class CategoryTest {

	@Test
	public void test() throws OpmlParseException {
		
		Opml opml = SampleReader.parse("/category.opml");
		
		Assert.assertEquals("2.0", opml.getVersion());
		Assert.assertEquals("Illustrating the category attribute", opml.getHead().getTitle());
		Assert.assertEquals("Mon, 31 Oct 2005 19:23:00 GMT", opml.getHead().getDateCreated());
		
		Assert.assertEquals(1, opml.getBody().getOutlines().size());

		Assert.assertEquals("The Mets are the best team in baseball.", opml.getBody().getOutline(0).getAttribute("text"));
		Assert.assertEquals("/Philosophy/Baseball/Mets,/Tourism/New York", opml.getBody().getOutline(0).getAttribute("category"));
		Assert.assertEquals("Mon, 31 Oct 2005 18:21:33 GMT", opml.getBody().getOutline(0).getAttribute("created"));
		
	}

}