package de.iwelt;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.wicket.protocol.http.WebResponse;
import org.wicketstuff.rome.FeedResource;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;

/**
 * @author gianasista
 */
public class RumorsResource extends FeedResource
{
	@Inject @Named("db")
	private RumorDatabase rumorDatabase;

	@Override
	public SyndFeed getFeed()
	{
		String feedType = "rss_2.0";

		SyndFeed feed = new SyndFeedImpl();
		feed.setFeedType(feedType);

		feed.setTitle("Flurfunk Feed");
		feed.setLink("http://flurfunk.intern.iwelt.de");
		feed.setDescription("");
		feed.setPublishedDate(new Date());

		List<SyndEntry> entries = new ArrayList<SyndEntry>();

		for(Rumor rumor : rumorDatabase.getRumors())
		{
			entries.add(getEntryFromRumor(rumor));
		}
		feed.setEntries(entries);

		return feed;
	}

	@Override
	public void setHeaders(WebResponse response)
	{
		super.setHeaders(response);
	}

	private SyndEntry getEntryFromRumor(Rumor rumor)
	{
		SyndEntry entry = new SyndEntryImpl();
		entry.setTitle("Rumor from "+rumor.getDate());
		entry.setLink("http://flurfunk.intern.iwelt.de");
		entry.setPublishedDate(rumor.getDate());

		SyndContent description = new SyndContentImpl();
		description.setType("text/plain");
		description.setValue(rumor.getText());
		entry.setDescription(description);

		return entry;
	}
}
