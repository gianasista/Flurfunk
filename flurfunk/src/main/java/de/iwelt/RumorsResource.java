/**
 *
 */
package de.iwelt;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.wicketstuff.rome.FeedResource;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;

/**
 * @author vera
 *
 */
public class RumorsResource extends FeedResource {

	@Override
	protected SyndFeed getFeed()
	{
		String feedType = "rss_2.0";

		SyndFeed feed = new SyndFeedImpl();
		feed.setFeedType(feedType);

		feed.setTitle("Developers-Blog.org Feed");
		feed.setLink("http://developers-blog.org");
		feed.setDescription("");
		feed.setPublishedDate(new Date());

		List entries = new ArrayList();
		SyndEntry entry;
		SyndContent description;

		entry = new SyndEntryImpl();
		entry.setTitle("My first RSS example with Rome");
		entry.setLink("http://developers-blog.org/...");
		entry.setPublishedDate(new Date());
		description = new SyndContentImpl();
		description.setType("text/html");
		description.setValue("<p>Haha: nothing here</p>");
		entry.setDescription(description);
		entries.add(entry);

		feed.setEntries(entries);

		return feed;
	}


}
