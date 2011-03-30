package de.iwelt;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.wicket.Resource;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.util.file.File;
import org.apache.wicket.util.value.ValueMap;
import org.wicketstuff.rome.FeedResource;

import com.almworks.sqlite4java.SQLiteConnection;
import com.almworks.sqlite4java.SQLiteException;
import com.almworks.sqlite4java.SQLiteStatement;

/**
 * Homepage
 */
public class RumorPage extends WebPage {

	private static final long serialVersionUID = 1L;

	private static final List<Rumor> rumorList = new ArrayList<Rumor>();

	public RumorPage()
	{
		initRumorsFromDb();

		add(new RumorForm("rumorForm"));

		add(new PropertyListView<Rumor>("rumors", rumorList)
		    {
	            @Override
	            public void populateItem(final ListItem<Rumor> listItem)
	            {
	                listItem.add(new Label("date"));
	                listItem.add(new MultiLineLabel("text"));
	            }
	        }).setVersioned(false);

		 add(FeedResource.autodiscoveryLink(new ResourceReference("rumorFeed") {
		      protected Resource newResource() {
		        return new RumorsResource();
		      }
		    }));
	}

	private void initRumorsFromDb()
	{
		SQLiteConnection db = new SQLiteConnection(new File("/Users/vera/projects/privat/Flurfunk/flurfunk/ff_development.db"));
	    try
	    {
			db.open(true);

			SQLiteStatement st = db.prepare("SELECT rumorText FROM rumors");

			while (st.step())
			{
		        System.out.println("Column wert: "+st.columnString(0));
		    }


		} catch (SQLiteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public final class RumorForm extends Form<ValueMap>
	{
		public RumorForm(final String id)
		{
			super(id, new CompoundPropertyModel<ValueMap>(new ValueMap()));
			setMarkupId("rumorForm");

			add(new TextArea<String>("text").setType(String.class));
		}

		@Override
		protected void onSubmit()
		{
			ValueMap values = getModelObject();

            Rumor rumor = new Rumor();
            rumor.setDate(new Date());
            rumor.setText(values.getString("text"));

            rumorList.add(rumor);
            values.put("text", "");
		}
	}

	public static void clear()
    {
        rumorList.clear();
    }
}
