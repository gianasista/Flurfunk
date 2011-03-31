package de.iwelt;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.almworks.sqlite4java.SQLiteConnection;
import com.almworks.sqlite4java.SQLiteStatement;
import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * @author gianasista
 *
 * created at: 30.03.2011 12:07:34
 */
public class RumorDatabase
{
	@Inject @Named("db.filename")
	private String dbFileName;

	public RumorDatabase()
	{
	}

	@Inject
	public void initSystemProperty(@Named("db.library") String path)
	{
		System.setProperty("sqlite4java.library.path", path);
	}

	public List<Rumor> getRumors()
	{
		List<Rumor> resultList = new ArrayList<Rumor>();
		SQLiteConnection db = new SQLiteConnection(new File(dbFileName));
	    try
	    {
			db.open(true);

			SQLiteStatement st = db.prepare("SELECT rumorText, rumorTimestamp FROM rumors");

			while (st.step())
			{
		        //System.out.println("Column wert: "+st.columnString(0));
		        Object columnValue = st.columnValue(1);
		        Date timestampDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(st.columnString(1));
		        //System.out.println("Timestamp Class: "+columnValue.getClass());
				//System.out.println("Timestamp Wert:"+columnValue);
		        Rumor rumor = new Rumor();
		        rumor.setText(st.columnString(0));
		        rumor.setDate(timestampDate);

		        resultList.add(rumor);
		    }

		}
	    catch (Exception e)
		{
			e.printStackTrace();
		}
	    finally
	    {
	    	db.dispose();
	    }

		return resultList;
	}

	public void insertRumor(Rumor rumor)
	{
		SQLiteConnection db = new SQLiteConnection(new File(dbFileName));
		try
		{
			db.open(true);
			SQLiteStatement st = db.prepare("INSERT INTO rumors (rumorText, rumorTimestamp) VALUES (?, ?)");

			st.bind(1, rumor.getText());
			st.bind(2, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rumor.getDate()));
			st.stepThrough();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			db.dispose();
		}
	}
}
