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
import be.ceau.opml.entity.Outline;

public class MySubscriptionsTest extends AbstractTest {

	@Override
	public String getFile() {
		return "/mySubscriptions.opml";
	}

	@Override
	public void test(Opml opml) throws OpmlParseException {

		Assert.assertEquals("2.0", opml.getVersion());
		Assert.assertEquals("mySubscriptions.opml", opml.getHead().getTitle());
		Assert.assertEquals("Sat, 18 Jun 2005 12:11:52 GMT", opml.getHead().getDateCreated());
		Assert.assertEquals("Tue, 02 Aug 2005 21:42:48 GMT", opml.getHead().getDateModified());
		Assert.assertEquals("Dave Winer", opml.getHead().getOwnerName());
		Assert.assertEquals("dave@scripting.com", opml.getHead().getOwnerEmail());
		Assert.assertTrue(opml.getHead().getExpansionState().isEmpty());
		Assert.assertTrue(1 == opml.getHead().getVertScrollState());
		Assert.assertTrue(61 == opml.getHead().getWindowTop());
		Assert.assertTrue(304 == opml.getHead().getWindowLeft());
		Assert.assertTrue(562 == opml.getHead().getWindowBottom());
		Assert.assertTrue(842 == opml.getHead().getWindowRight());
		
		Assert.assertEquals(13, opml.getBody().getOutlines().size());

		Outline outline = null;

		outline = opml.getBody().getOutlines().get(0);
		Assert.assertEquals("CNET News.com", outline.getAttribute("text"));
		Assert.assertEquals("Tech news and business reports by CNET News.com. Focused on information technology, core topics include computers, hardware, software, networking, and Internet media.", outline.getAttribute("description"));
		Assert.assertEquals("http://news.com.com/", outline.getAttribute("htmlUrl"));
		Assert.assertEquals("unknown", outline.getAttribute("language"));
		Assert.assertEquals("CNET News.com", outline.getAttribute("title"));
		Assert.assertEquals("rss", outline.getAttribute("type"));
		Assert.assertEquals("RSS2", outline.getAttribute("version"));
		Assert.assertEquals("http://news.com.com/2547-1_3-0-5.xml", outline.getAttribute("xmlUrl"));

		outline = opml.getBody().getOutlines().get(1);
		Assert.assertEquals("washingtonpost.com - Politics", outline.getAttribute("text"));
		Assert.assertEquals("Politics", outline.getAttribute("description"));
		Assert.assertEquals("http://www.washingtonpost.com/wp-dyn/politics?nav=rss_politics", outline.getAttribute("htmlUrl"));
		Assert.assertEquals("unknown", outline.getAttribute("language"));
		Assert.assertEquals("washingtonpost.com - Politics", outline.getAttribute("title"));
		Assert.assertEquals("rss", outline.getAttribute("type"));
		Assert.assertEquals("RSS2", outline.getAttribute("version"));
		Assert.assertEquals("http://www.washingtonpost.com/wp-srv/politics/rssheadlines.xml", outline.getAttribute("xmlUrl"));

		outline = opml.getBody().getOutlines().get(2);
		Assert.assertEquals("Scobleizer: Microsoft Geek Blogger", outline.getAttribute("text"));
		Assert.assertEquals("Robert Scoble's look at geek and Microsoft life.", outline.getAttribute("description"));
		Assert.assertEquals("http://radio.weblogs.com/0001011/", outline.getAttribute("htmlUrl"));
		Assert.assertEquals("unknown", outline.getAttribute("language"));
		Assert.assertEquals("Scobleizer: Microsoft Geek Blogger", outline.getAttribute("title"));
		Assert.assertEquals("rss", outline.getAttribute("type"));
		Assert.assertEquals("RSS2", outline.getAttribute("version"));
		Assert.assertEquals("http://radio.weblogs.com/0001011/rss.xml", outline.getAttribute("xmlUrl"));

		outline = opml.getBody().getOutlines().get(3);
		Assert.assertEquals("Yahoo! News: Technology", outline.getAttribute("text"));
		Assert.assertEquals("Technology", outline.getAttribute("description"));
		Assert.assertEquals("http://news.yahoo.com/news?tmpl=index&cid=738", outline.getAttribute("htmlUrl"));
		Assert.assertEquals("unknown", outline.getAttribute("language"));
		Assert.assertEquals("Yahoo! News: Technology", outline.getAttribute("title"));
		Assert.assertEquals("rss", outline.getAttribute("type"));
		Assert.assertEquals("RSS2", outline.getAttribute("version"));
		Assert.assertEquals("http://rss.news.yahoo.com/rss/tech", outline.getAttribute("xmlUrl"));

		outline = opml.getBody().getOutlines().get(4);
		Assert.assertEquals("Workbench", outline.getAttribute("text"));
		Assert.assertEquals("Programming and publishing news and comment", outline.getAttribute("description"));
		Assert.assertEquals("http://www.cadenhead.org/workbench/", outline.getAttribute("htmlUrl"));
		Assert.assertEquals("unknown", outline.getAttribute("language"));
		Assert.assertEquals("Workbench", outline.getAttribute("title"));
		Assert.assertEquals("rss", outline.getAttribute("type"));
		Assert.assertEquals("RSS2", outline.getAttribute("version"));
		Assert.assertEquals("http://www.cadenhead.org/workbench/rss.xml", outline.getAttribute("xmlUrl"));

		outline = opml.getBody().getOutlines().get(5);
		Assert.assertEquals("Christian Science Monitor | Top Stories", outline.getAttribute("text"));
		Assert.assertEquals("Read the front page stories of csmonitor.com.", outline.getAttribute("description"));
		Assert.assertEquals("http://csmonitor.com", outline.getAttribute("htmlUrl"));
		Assert.assertEquals("unknown", outline.getAttribute("language"));
		Assert.assertEquals("Christian Science Monitor | Top Stories", outline.getAttribute("title"));
		Assert.assertEquals("rss", outline.getAttribute("type"));
		Assert.assertEquals("RSS", outline.getAttribute("version"));
		Assert.assertEquals("http://www.csmonitor.com/rss/top.rss", outline.getAttribute("xmlUrl"));

		outline = opml.getBody().getOutlines().get(6);
		Assert.assertEquals("Dictionary.com Word of the Day", outline.getAttribute("text"));
		Assert.assertEquals("A new word is presented every day with its definition and example sentences from actual published works.", outline.getAttribute("description"));
		Assert.assertEquals("http://dictionary.reference.com/wordoftheday/", outline.getAttribute("htmlUrl"));
		Assert.assertEquals("unknown", outline.getAttribute("language"));
		Assert.assertEquals("Dictionary.com Word of the Day", outline.getAttribute("title"));
		Assert.assertEquals("rss", outline.getAttribute("type"));
		Assert.assertEquals("RSS", outline.getAttribute("version"));
		Assert.assertEquals("http://www.dictionary.com/wordoftheday/wotd.rss", outline.getAttribute("xmlUrl"));

		outline = opml.getBody().getOutlines().get(7);
		Assert.assertEquals("The Motley Fool", outline.getAttribute("text"));
		Assert.assertEquals("To Educate, Amuse, and Enrich", outline.getAttribute("description"));
		Assert.assertEquals("http://www.fool.com", outline.getAttribute("htmlUrl"));
		Assert.assertEquals("unknown", outline.getAttribute("language"));
		Assert.assertEquals("The Motley Fool", outline.getAttribute("title"));
		Assert.assertEquals("rss", outline.getAttribute("type"));
		Assert.assertEquals("RSS", outline.getAttribute("version"));
		Assert.assertEquals("http://www.fool.com/xml/foolnews_rss091.xml", outline.getAttribute("xmlUrl"));

		outline = opml.getBody().getOutlines().get(8);
		Assert.assertEquals("InfoWorld: Top News", outline.getAttribute("text"));
		Assert.assertEquals("The latest on Top News from InfoWorld", outline.getAttribute("description"));
		Assert.assertEquals("http://www.infoworld.com/news/index.html", outline.getAttribute("htmlUrl"));
		Assert.assertEquals("unknown", outline.getAttribute("language"));
		Assert.assertEquals("InfoWorld: Top News", outline.getAttribute("title"));
		Assert.assertEquals("rss", outline.getAttribute("type"));
		Assert.assertEquals("RSS2", outline.getAttribute("version"));
		Assert.assertEquals("http://www.infoworld.com/rss/news.xml", outline.getAttribute("xmlUrl"));

		outline = opml.getBody().getOutlines().get(9);
		Assert.assertEquals("NYT > Business", outline.getAttribute("text"));
		Assert.assertEquals("Find breaking news & business news on Wall Street, media & advertising, international business, banking, interest rates, the stock market, currencies & funds.", outline.getAttribute("description"));
		Assert.assertEquals("http://www.nytimes.com/pages/business/index.html?partner=rssnyt", outline.getAttribute("htmlUrl"));
		Assert.assertEquals("unknown", outline.getAttribute("language"));
		Assert.assertEquals("NYT > Business", outline.getAttribute("title"));
		Assert.assertEquals("rss", outline.getAttribute("type"));
		Assert.assertEquals("RSS2", outline.getAttribute("version"));
		Assert.assertEquals("http://www.nytimes.com/services/xml/rss/nyt/Business.xml", outline.getAttribute("xmlUrl"));

		outline = opml.getBody().getOutlines().get(10);
		Assert.assertEquals("NYT > Technology", outline.getAttribute("text"));
		Assert.assertEquals("http://www.nytimes.com/pages/technology/index.html?partner=rssnyt", outline.getAttribute("htmlUrl"));
		Assert.assertEquals("unknown", outline.getAttribute("language"));
		Assert.assertEquals("NYT > Technology", outline.getAttribute("title"));
		Assert.assertEquals("rss", outline.getAttribute("type"));
		Assert.assertEquals("RSS2", outline.getAttribute("version"));
		Assert.assertEquals("http://www.nytimes.com/services/xml/rss/nyt/Technology.xml", outline.getAttribute("xmlUrl"));

		outline = opml.getBody().getOutlines().get(11);
		Assert.assertEquals("Scripting News", outline.getAttribute("text"));
		Assert.assertEquals("It's even worse than it appears.", outline.getAttribute("description"));
		Assert.assertEquals("http://www.scripting.com/", outline.getAttribute("htmlUrl"));
		Assert.assertEquals("unknown", outline.getAttribute("language"));
		Assert.assertEquals("Scripting News", outline.getAttribute("title"));
		Assert.assertEquals("rss", outline.getAttribute("type"));
		Assert.assertEquals("RSS2", outline.getAttribute("version"));
		Assert.assertEquals("http://www.scripting.com/rss.xml", outline.getAttribute("xmlUrl"));

		outline = opml.getBody().getOutlines().get(12);
		Assert.assertEquals("Wired News", outline.getAttribute("text"));
		Assert.assertEquals("Technology, and the way we do business, is changing the world we know. Wired News is a technology - and business-oriented news service feeding an intelligent, discerning audience. What role does technology play in the day-to-day living of your life? Wired News tells you. How has evolving technology changed the face of the international business world? Wired News puts you in the picture.", outline.getAttribute("description"));
		Assert.assertEquals("http://www.wired.com/", outline.getAttribute("htmlUrl"));
		Assert.assertEquals("unknown", outline.getAttribute("language"));
		Assert.assertEquals("Wired News", outline.getAttribute("title"));
		Assert.assertEquals("rss", outline.getAttribute("type"));
		Assert.assertEquals("RSS", outline.getAttribute("version"));
		Assert.assertEquals("http://www.wired.com/news_drop/netcenter/netcenter.rdf", outline.getAttribute("xmlUrl"));

	}

}