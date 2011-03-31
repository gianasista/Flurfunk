package de.iwelt;

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
import org.apache.wicket.util.value.ValueMap;
import org.wicketstuff.rome.FeedResource;

import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * Homepage
 */
public class RumorPage extends WebPage {

	private static final long serialVersionUID = 1L;

	private static List<Rumor> rumorList;

	@Inject @Named("db")
	private RumorDatabase rumorDatabase;

	public RumorPage()
	{
		rumorList = rumorDatabase.getRumors();

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
            rumorDatabase.insertRumor(rumor);
            values.put("text", "");
		}
	}

	public static void clear()
    {
        rumorList.clear();
    }
}
