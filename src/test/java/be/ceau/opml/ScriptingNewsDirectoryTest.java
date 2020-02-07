/*
	Copyright 2020 Marceau Dewilde <m@ceau.be>
	
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

import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;

import be.ceau.opml.entity.Opml;
import be.ceau.opml.entity.Outline;

public class ScriptingNewsDirectoryTest {

	@Test
	public void test() throws OpmlParseException {

		Opml opml = SampleReader.parse("/scriptingNewsDirectory.opml");

		Assert.assertEquals("2.0", opml.getVersion());

		Assert.assertEquals("scriptingNewsDirectory.opml", opml.getHead().getTitle());

		Assert.assertEquals("Thu, 13 Oct 2005 15:34:07 GMT", opml.getHead().getDateCreated());
		Assert.assertEquals("Tue, 25 Oct 2005 21:33:57 GMT", opml.getHead().getDateModified());
		Assert.assertEquals("Dave Winer", opml.getHead().getOwnerName());
		Assert.assertEquals("dwiner@yahoo.com", opml.getHead().getOwnerEmail());
		Assert.assertTrue(opml.getHead().getExpansionState().isEmpty());
		Assert.assertTrue(1 == opml.getHead().getVertScrollState());
		Assert.assertTrue(105 == opml.getHead().getWindowTop());
		Assert.assertTrue(466 == opml.getHead().getWindowLeft());
		Assert.assertTrue(386 == opml.getHead().getWindowBottom());
		Assert.assertTrue(964 == opml.getHead().getWindowRight());

		Assert.assertEquals(8, opml.getBody().getOutlines().size());

		Iterator<Outline> iterator = opml.getBody().getOutlines().iterator();
		
		Outline outline = iterator.next();
		Assert.assertEquals("Scripting News sites", outline.getAttribute("text"));
		Assert.assertEquals("Sun, 16 Oct 2005 05:56:10 GMT", outline.getAttribute("created"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://hosting.opml.org/dave/mySites.opml", outline.getAttribute("url"));

		outline = iterator.next();
		Assert.assertEquals("News.Com top 100 OPML", outline.getAttribute("text"));
		Assert.assertEquals("Tue, 25 Oct 2005 21:33:28 GMT", outline.getAttribute("created"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://news.com.com/html/ne/blogs/CNETNewsBlog100.opml", outline.getAttribute("url"));
		
		outline = iterator.next();
		Assert.assertEquals("BloggerCon III Blogroll", outline.getAttribute("text"));
		Assert.assertEquals("Mon, 24 Oct 2005 05:23:52 GMT", outline.getAttribute("created"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://static.bloggercon.org/iii/blogroll.opml", outline.getAttribute("url"));
		
		outline = iterator.next();
		Assert.assertEquals("TechCrunch reviews", outline.getAttribute("text"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://hosting.opml.org/techcrunch.opml.org/TechCrunch.opml", outline.getAttribute("url"));

		outline = iterator.next();
		Assert.assertEquals("Tod Maffin's directory of Public Radio podcasts", outline.getAttribute("text"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://todmaffin.com/radio.opml", outline.getAttribute("url"));
		
		outline = iterator.next();
		Assert.assertEquals("Adam Curry's iPodder.org directory", outline.getAttribute("text"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://homepage.mac.com/dailysourcecode/DSC/ipodderDirectory.opml", outline.getAttribute("url"));
		
		outline = iterator.next();
		Assert.assertEquals("Memeorandum", outline.getAttribute("text"));
		Assert.assertEquals("Thu, 13 Oct 2005 15:19:05 GMT", outline.getAttribute("created"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://tech.memeorandum.com/index.opml", outline.getAttribute("url"));
		
		outline = iterator.next();
		Assert.assertEquals("DaveNet archive", outline.getAttribute("text"));
		Assert.assertEquals("Wed, 12 Oct 2005 01:39:56 GMT", outline.getAttribute("created"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://davenet.opml.org/index.opml", outline.getAttribute("url"));

	}

}