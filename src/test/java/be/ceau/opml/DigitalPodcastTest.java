/*
	Copyright 2020 Marceau Dewilde <m@ceau.be>
	
	Licensed under the Apache License, Version 2.0 (the "License"));
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

public class DigitalPodcastTest {

	@Test
	public void test() throws OpmlParseException {

		Opml opml = SampleReader.parse("/digitalPodcast.opml");

		Assert.assertEquals("1.1", opml.getVersion());
		Assert.assertEquals("Digital Podcast 50 Top Rated Listings", opml.getHead().getTitle());
		Assert.assertEquals("Tue, 05 Feb 2005 13:55:28 GMT", opml.getHead().getDateCreated());
		Assert.assertEquals("Sun, 23 Jul 2017 17:31:17 EDT", opml.getHead().getDateModified());
		Assert.assertTrue(opml.getHead().getExpansionState().isEmpty());
		Assert.assertTrue(1 == opml.getHead().getVertScrollState());
		Assert.assertTrue(20 == opml.getHead().getWindowTop());
		Assert.assertTrue(618 == opml.getHead().getWindowLeft());
		Assert.assertTrue(671 == opml.getHead().getWindowBottom());
		Assert.assertTrue(765 == opml.getHead().getWindowRight());
		
		Assert.assertEquals(50, opml.getBody().getOutlines().size());

		Outline outline = null;

		Iterator<Outline> iterator = opml.getBody().getOutlines().iterator();
		
		outline = iterator.next();
		Assert.assertEquals("The Adam and Reggie Show", outline.getAttribute("text"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://feeds.feedburner.com/theadamandreggieshow/ikUg", outline.getAttribute("url"));

		outline = iterator.next();
		Assert.assertEquals("SportsJAM", outline.getAttribute("text"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://sportsjam.podbean.com/feed/", outline.getAttribute("url"));

		outline = iterator.next();
		Assert.assertEquals("DS London Nintendo Podcast", outline.getAttribute("text"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://feeds.feedburner.com/dslondonpodcast", outline.getAttribute("url"));

		outline = iterator.next();
		Assert.assertEquals("Real Jersey Guys", outline.getAttribute("text"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://rjg.podhoster.com/rss/3310/", outline.getAttribute("url"));

		outline = iterator.next();
		Assert.assertEquals("Elevate E.D.M » Episodes", outline.getAttribute("text"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://feeds.feedburner.com/ElevateEpisodes", outline.getAttribute("url"));

		outline = iterator.next();
		Assert.assertEquals("The Bi-Quarterly Women's Social Club", outline.getAttribute("text"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://feeds.feedburner.com/TheBi-quarterlyWomensSocialClub", outline.getAttribute("url"));

		outline = iterator.next();
		Assert.assertEquals("MerlinCast", outline.getAttribute("text"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://feeds.feedburner.com/merlincast", outline.getAttribute("url"));

		outline = iterator.next();
		Assert.assertEquals("Improve Intimacy with Dr. Fran", outline.getAttribute("text"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://improveintimacy.podomatic.com/rss2.xml", outline.getAttribute("url"));

		outline = iterator.next();
		Assert.assertEquals("West Ridge Academy", outline.getAttribute("text"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://blog.westridgeacademy.com/feed/podcast/", outline.getAttribute("url"));

		outline = iterator.next();
		Assert.assertEquals("Variety Reál", outline.getAttribute("text"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://varietyreal.blogspot.com/feeds/posts/default?alt=rss", outline.getAttribute("url"));

		outline = iterator.next();
		Assert.assertEquals("Moonlight Audio Theatre", outline.getAttribute("text"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://moonlightaudio.libsyn.com/rss", outline.getAttribute("url"));

		outline = iterator.next();
		Assert.assertEquals("Leatherheads of the Gridiron", outline.getAttribute("text"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://www.blogtalkradio.com/leatherheads.rss", outline.getAttribute("url"));

		outline = iterator.next();
		Assert.assertEquals("Thrifty Tuscany's Podcast", outline.getAttribute("text"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://thriftytuscany.podomatic.com/rss2.xml", outline.getAttribute("url"));

		outline = iterator.next();
		Assert.assertEquals("Kareyo - Solarflare", outline.getAttribute("text"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://kareyo.podomatic.com/rss2.xml", outline.getAttribute("url"));

		outline = iterator.next();
		Assert.assertEquals("Heights Beats: The Podcast", outline.getAttribute("text"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://www.hotmilkrecords.com/podcasts/heights/feed.xml", outline.getAttribute("url"));

		outline = iterator.next();
		Assert.assertEquals("DJ Coma Podcast", outline.getAttribute("text"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://feeds.feedburner.com/DjComaWeeklyPodcast", outline.getAttribute("url"));

		outline = iterator.next();
		Assert.assertEquals("Today In Mediocrity » Podcasts", outline.getAttribute("text"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://feeds.feedburner.com/TodayInMediocrity", outline.getAttribute("url"));

		outline = iterator.next();
		Assert.assertEquals("Pizza Games and Zombies podcast", outline.getAttribute("text"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://feeds.feedburner.com/PizzaGamesAndZombiesPodcast", outline.getAttribute("url"));

		outline = iterator.next();
		Assert.assertEquals("Tiny Odd Conversations", outline.getAttribute("text"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://tocpod.libsyn.com/rss", outline.getAttribute("url"));

		outline = iterator.next();
		Assert.assertEquals("Sports Gambling Podcast", outline.getAttribute("text"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://www.seantgreen.com/sgpod/sgpodcast.xml", outline.getAttribute("url"));

		outline = iterator.next();
		Assert.assertEquals("The Green Room with Sean Green", outline.getAttribute("text"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://www.seantgreen.com/thegreenroom/thegreenroom.xml", outline.getAttribute("url"));

		outline = iterator.next();
		Assert.assertEquals("Shutter Time with Sid and Mac - Photography Podcast.", outline.getAttribute("text"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://shuttertimewithsidandmac.com/?feed=podcast", outline.getAttribute("url"));

		outline = iterator.next();
		Assert.assertEquals("Membean Word Root Of the Day", outline.getAttribute("text"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://feeds.feedburner.com/membean/MembeanWROTD", outline.getAttribute("url"));

		outline = iterator.next();
		Assert.assertEquals("The Deb Colitti Show", outline.getAttribute("text"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://feeds.feedburner.com/debcolittishow", outline.getAttribute("url"));

		outline = iterator.next();
		Assert.assertEquals("Stephen Mansfield » Podcast", outline.getAttribute("text"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://mansfieldgroup.com/category/podcast/feed/", outline.getAttribute("url"));

		outline = iterator.next();
		Assert.assertEquals("Daddy Life", outline.getAttribute("text"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://feeds.feedburner.com/DaddyLifePodcast", outline.getAttribute("url"));

		outline = iterator.next();
		Assert.assertEquals("Provocatalk Radio: Hair and Heels", outline.getAttribute("text"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://recordings.talkshoe.com/rss89082.xml", outline.getAttribute("url"));

		outline = iterator.next();
		Assert.assertEquals("The New York City Crime Report with Pat Dixon", outline.getAttribute("text"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://feeds.feedburner.com/TheNewYorkCityCrimeReportWithPatDixon", outline.getAttribute("url"));

		outline = iterator.next();
		Assert.assertEquals("sparkCast | The Spark Hire Podcast", outline.getAttribute("text"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://feeds.feedburner.com/sparkhirepodcast", outline.getAttribute("url"));

		outline = iterator.next();
		Assert.assertEquals("Drematic Podcast", outline.getAttribute("text"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://djillout.podomatic.com/rss2.xml", outline.getAttribute("url"));

		outline = iterator.next();
		Assert.assertEquals("Psycomedia Network", outline.getAttribute("text"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://feeds.feedburner.com/Psycomedia", outline.getAttribute("url"));

		outline = iterator.next();
		Assert.assertEquals("The Automotive Hour, Weekly Podcast of AGCO Automotive Corporation", outline.getAttribute("text"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://www.agcoauto.com/podcast/", outline.getAttribute("url"));

		outline = iterator.next();
		Assert.assertEquals("The Hayseed Report | Conservative Political Commentary, Talk ,and Opinion", outline.getAttribute("text"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://feeds.feedburner.com/TheHayseedReport?format=xml", outline.getAttribute("url"));

		outline = iterator.next();
		Assert.assertEquals("The Pulp Podcast", outline.getAttribute("text"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://feeds.feedburner.com/crosstalknet/thepulp", outline.getAttribute("url"));

		outline = iterator.next();
		Assert.assertEquals("The Barenaked Babies Show", outline.getAttribute("text"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://www.barenakedbabiesshow.com/podcast/index.xml", outline.getAttribute("url"));

		outline = iterator.next();
		Assert.assertEquals("Kansas City Bastards Podcast", outline.getAttribute("text"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://kcbastards.com/?feed=podcast", outline.getAttribute("url"));

		outline = iterator.next();
		Assert.assertEquals("Stories from the Borders of Sleep", outline.getAttribute("text"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://bordersofsleep.libsyn.com/rss", outline.getAttribute("url"));

		outline = iterator.next();
		Assert.assertEquals("TwoFer with Mark &amp; Zach BLodPods", outline.getAttribute("text"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://www.buzzsprout.com/3719.rss", outline.getAttribute("url"));

		outline = iterator.next();
		Assert.assertEquals("Supernatural Science", outline.getAttribute("text"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://savpar.libsyn.com/rss", outline.getAttribute("url"));

		outline = iterator.next();
		Assert.assertEquals("CloudFocus Weekly", outline.getAttribute("text"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://feeds.feedburner.com/cloudfocus", outline.getAttribute("url"));

		outline = iterator.next();
		Assert.assertEquals("Audio Ad", outline.getAttribute("text"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://feeds.feedburner.com/audioadmixtapeshow", outline.getAttribute("url"));

		outline = iterator.next();
		Assert.assertEquals("BOOGIE145'sRADIO/PRODUCT'S OF HARDTIMES RADIO", outline.getAttribute("text"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://boogie145.podomatic.com/rss2.xml", outline.getAttribute("url"));

		outline = iterator.next();
		Assert.assertEquals("swaraaj : independent indian music", outline.getAttribute("text"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://swaraaj.libsyn.com/rss", outline.getAttribute("url"));

		outline = iterator.next();
		Assert.assertEquals("The Daily Brainwave", outline.getAttribute("text"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://feeds.feedburner.com/Dailybrainwave", outline.getAttribute("url"));

		outline = iterator.next();
		Assert.assertEquals("Reviler", outline.getAttribute("text"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://www.reviler.org/feed/podcast", outline.getAttribute("url"));

		outline = iterator.next();
		Assert.assertEquals("Russian podcast", outline.getAttribute("text"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://feeds.feedburner.com/BestRussianPodcasts", outline.getAttribute("url"));

		outline = iterator.next();
		Assert.assertEquals("Another Waste Of A Podcast", outline.getAttribute("text"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://awoapodcast.wordpress.com/feed/", outline.getAttribute("url"));

		outline = iterator.next();
		Assert.assertEquals("No Wire Hangers Podcast", outline.getAttribute("text"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://feeds.feedburner.com/nowirehangerspodcast", outline.getAttribute("url"));

		outline = iterator.next();
		Assert.assertEquals("Across The Airwaves", outline.getAttribute("text"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://acrosstheairwaves.libsyn.com/rss", outline.getAttribute("url"));

		outline = iterator.next();
		Assert.assertEquals("The Brian and Lee Show", outline.getAttribute("text"));
		Assert.assertEquals("link", outline.getAttribute("type"));
		Assert.assertEquals("http://www.podcastrevolution.com/viewpodcast.php?pid=1896", outline.getAttribute("url"));

	}


}
