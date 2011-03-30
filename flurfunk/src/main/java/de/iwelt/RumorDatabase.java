package de.iwelt;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.almworks.sqlite4java.SQLiteConnection;
import com.almworks.sqlite4java.SQLiteException;
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
		SQLiteConnection db = new SQLiteConnection(new File(dbFileName));
	    try
	    {
			db.open(true);

			SQLiteStatement st = db.prepare("SELECT rumorText FROM rumors");

			while (st.step())
			{
		        System.out.println("Column wert: "+st.columnString(0));
		    }

		}
	    catch (SQLiteException e)
		{
			e.printStackTrace();
		}

		return new ArrayList<Rumor>();
	}

	/*
	@Inject
	public void setLibraryPath(@Named("db.library") String path)
	{
		System.out.println("Init Library Path");
		//System.setProperty("sqlite4java.library.path", path);
		libraryPath = path;
	}

	public String getLibraryPath() {
		return libraryPath;
	}
	*/
}
