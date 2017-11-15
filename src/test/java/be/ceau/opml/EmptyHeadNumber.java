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

import org.junit.Assert;

import be.ceau.opml.entity.Opml;

public class EmptyHeadNumber extends AbstractTest {

	@Override
	public String getFile() {
		return "/emptyHeadNumber.opml";
	}

	@Override
	public void test(Opml opml) throws OpmlParseException {
		
		Assert.assertNull(opml.getHead().getWindowBottom());
		Assert.assertNull(opml.getHead().getWindowTop());
		Assert.assertNull(opml.getHead().getWindowLeft());
		Assert.assertNull(opml.getHead().getWindowRight());

	}


}