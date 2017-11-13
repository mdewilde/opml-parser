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

import java.util.Arrays;

import org.junit.Assert;

import be.ceau.opml.entity.Opml;
import be.ceau.opml.entity.Outline;

public class PlacesLivedTest extends AbstractTest {

	@Override
	public String getFile() {
		return "/placesLived.opml";
	}

	@Override
	public void test(Opml opml) throws OpmlParseException {

		Assert.assertEquals("2.0", opml.getVersion());
		Assert.assertEquals("placesLived.opml", opml.getHead().getTitle());
		Assert.assertEquals("Mon, 27 Feb 2006 12:09:48 GMT", opml.getHead().getDateCreated());
		Assert.assertEquals("Mon, 27 Feb 2006 12:11:44 GMT", opml.getHead().getDateModified());
		Assert.assertEquals("Dave Winer", opml.getHead().getOwnerName());
		Assert.assertEquals("http://www.opml.org/profiles/sendMail?usernum=1", opml.getHead().getOwnerId());
		Assert.assertEquals(Arrays.asList(1, 2, 5, 10, 13, 15), opml.getHead().getExpansionState());
		Assert.assertNull(opml.getHead().getVertScrollState());
		Assert.assertTrue(242 == opml.getHead().getWindowTop());
		Assert.assertTrue(329 == opml.getHead().getWindowLeft());
		Assert.assertTrue(665 == opml.getHead().getWindowBottom());
		Assert.assertTrue(547 == opml.getHead().getWindowRight());
		
		Assert.assertEquals(1, opml.getBody().getOutlines().size());

		Outline outline = opml.getBody().getOutlines().get(0);
		Assert.assertEquals("Places I've lived", outline.getAttribute("text"));
		Assert.assertEquals(6, outline.getSubElements().size());
		
		Outline suboutline = null;
		Outline subsuboutline = null;
		
		suboutline = outline.getSubElements().get(0);
		Assert.assertEquals("Boston", suboutline.getAttribute("text"));
		Assert.assertEquals(2, suboutline.getSubElements().size());
		
			subsuboutline = suboutline.getSubElements().get(0);
			Assert.assertEquals("Cambridge", subsuboutline.getAttribute("text"));
			Assert.assertEquals(0, subsuboutline.getSubElements().size());
			
			subsuboutline = suboutline.getSubElements().get(1);
			Assert.assertEquals("West Newton", subsuboutline.getAttribute("text"));
			Assert.assertEquals(0, subsuboutline.getSubElements().size());

		suboutline = outline.getSubElements().get(1);
		Assert.assertEquals("Bay Area", suboutline.getAttribute("text"));
		Assert.assertEquals(4, suboutline.getSubElements().size());
		
			subsuboutline = suboutline.getSubElements().get(0);
			Assert.assertEquals("Mountain View", subsuboutline.getAttribute("text"));
			Assert.assertEquals(0, subsuboutline.getSubElements().size());
			
			subsuboutline = suboutline.getSubElements().get(1);
			Assert.assertEquals("Los Gatos", subsuboutline.getAttribute("text"));
			Assert.assertEquals(0, subsuboutline.getSubElements().size());
	
			subsuboutline = suboutline.getSubElements().get(2);
			Assert.assertEquals("Palo Alto", subsuboutline.getAttribute("text"));
			Assert.assertEquals(0, subsuboutline.getSubElements().size());
			
			subsuboutline = suboutline.getSubElements().get(3);
			Assert.assertEquals("Woodside", subsuboutline.getAttribute("text"));
			Assert.assertEquals(0, subsuboutline.getSubElements().size());

		suboutline = outline.getSubElements().get(2);
		Assert.assertEquals("New Orleans", suboutline.getAttribute("text"));
		Assert.assertEquals(2, suboutline.getSubElements().size());
		
			subsuboutline = suboutline.getSubElements().get(0);
			Assert.assertEquals("Uptown", subsuboutline.getAttribute("text"));
			Assert.assertEquals(0, subsuboutline.getSubElements().size());
			
			subsuboutline = suboutline.getSubElements().get(1);
			Assert.assertEquals("Metairie", subsuboutline.getAttribute("text"));
			Assert.assertEquals(0, subsuboutline.getSubElements().size());

		suboutline = outline.getSubElements().get(3);
		Assert.assertEquals("Wisconsin", suboutline.getAttribute("text"));
		Assert.assertEquals(1, suboutline.getSubElements().size());
		
			subsuboutline = suboutline.getSubElements().get(0);
			Assert.assertEquals("Madison", subsuboutline.getAttribute("text"));
			Assert.assertEquals(0, subsuboutline.getSubElements().size());

		suboutline = outline.getSubElements().get(4);
		Assert.assertEquals("Florida", suboutline.getAttribute("text"));
		Assert.assertEquals("include", suboutline.getAttribute("type"));
		Assert.assertEquals("http://hosting.opml.org/dave/florida.opml", suboutline.getAttribute("url"));
		Assert.assertEquals(0, suboutline.getSubElements().size());

		suboutline = outline.getSubElements().get(5);
		Assert.assertEquals("New York", suboutline.getAttribute("text"));
		Assert.assertEquals(3, suboutline.getSubElements().size());

			subsuboutline = suboutline.getSubElements().get(0);
			Assert.assertEquals("Jackson Heights", subsuboutline.getAttribute("text"));
			Assert.assertEquals(0, subsuboutline.getSubElements().size());
			
			subsuboutline = suboutline.getSubElements().get(1);
			Assert.assertEquals("Flushing", subsuboutline.getAttribute("text"));
			Assert.assertEquals(0, subsuboutline.getSubElements().size());
	
			subsuboutline = suboutline.getSubElements().get(2);
			Assert.assertEquals("The Bronx", subsuboutline.getAttribute("text"));
			Assert.assertEquals(0, subsuboutline.getSubElements().size());
		

	}

}