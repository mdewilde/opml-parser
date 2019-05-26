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

public class StatesTest {

	@Test
	public void test() throws OpmlParseException {
		
		Opml opml = SampleReader.parse("/states.opml");

		Assert.assertEquals(1, opml.getBody().getOutlines().size());
		Assert.assertEquals(8, opml.getBody().getOutline(0).getSubElements().size());
		Assert.assertEquals(6, opml.getBody().getOutline(0).getSubElement(0).getSubElements().size());
		Assert.assertEquals(4, opml.getBody().getOutline(0).getSubElement(0).getSubElement(3).getSubElements().size());
		Assert.assertEquals(5, opml.getBody().getOutline(0).getSubElement(1).getSubElements().size());
		Assert.assertEquals(5, opml.getBody().getOutline(0).getSubElement(2).getSubElements().size());
		Assert.assertEquals(10, opml.getBody().getOutline(0).getSubElement(3).getSubElements().size());
		Assert.assertEquals(5, opml.getBody().getOutline(0).getSubElement(4).getSubElements().size());
		Assert.assertEquals(6, opml.getBody().getOutline(0).getSubElement(5).getSubElements().size());
		Assert.assertEquals(10, opml.getBody().getOutline(0).getSubElement(6).getSubElements().size());
		Assert.assertEquals(3, opml.getBody().getOutline(0).getSubElement(7).getSubElements().size());

	}


}