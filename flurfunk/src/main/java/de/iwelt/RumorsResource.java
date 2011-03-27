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
		/*
		SyndFeed feed = new SyndFeedImpl();
        feed.setFeedType( "rss_2.0" );
        feed.setTitle( "My Title" );
        feed.setDescription( "My Description" );
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
        */

		String feedType = "rss_2.0";
		//String fileName = "rss.xml";

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

		/*
		 * klappt
		SyndFeed feed = new SyndFeedImpl();
        feed.setFeedType("rss_1.0");
        feed.setTitle("MyProject Build Results");
        feed.setLink("http://myproject.mycompany.com/continuum");
        feed.setDescription("Continuous build results for the MyProject project");
        feed.setPublishedDate(new Date());
        //feed.setCategory("MyProject");

        SyndEntry entry = new SyndEntryImpl();
        entry.setTitle("BUILD SUCCESSFUL");
        entry.setLink("http://myproject.mycompany.com/continuum/build-results-1");
        entry.setPublishedDate(new Date());

        SyndContent description = new SyndContentImpl();
        description.setType("text/html");
        description.setValue("The build was successful!");
        entry.setDescription(description);
		 */

		/*
	    SyndFeed feed = new SyndFeedImpl(); // create the feed
	    Date publishDate = new Date( System.currentTimeMillis() );

	    feed.setTitle( "Techblog.ph RSS Feeds" );
	    feed.setDescription( "RSS feeds of blog entries from Techblog.ph" );
	    feed.setLanguage( "en-us" );
	    feed.setPublishedDate( publishDate );
	    feed.setFeedType( "rss_2.0" ); // set the type of your feed

	    List<SyndEntry> entries = new ArrayList<SyndEntry>();

	    SyndEntry entry = new SyndEntryImpl(); // create a feed entry
	    entry.setTitle( "Generating RSS Feeds with Rome" );
	    entry.setPublishedDate( publishDate );

	    SyndContent content = new SyndContentImpl(); // create the content of your entry
	    content.setType( "text/plain" );
	    content.setValue( "Want to have RSS feeds in your Java application? Try Rome….." );

	    entry.setDescription( content );
	    entries.add( entry );
	    feed.setEntries( entries ); // you can add multiple entries in your feed
	    */

		return feed;
	}


}
