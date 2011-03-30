/**
 *
 */
package de.iwelt;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

/**
 * @author gianasista
 *
 * created at: 30.03.2011 12:16:46
 */
public class RumorModule extends AbstractModule
{
	public RumorModule()
	{
		super();
	}

	@Override
	protected void configure()
	{
		bind(RumorDatabase.class).annotatedWith(Names.named("db")).to(RumorDatabase.class);

		Properties databaseProperties = loadProperties("db.properties");
		Names.bindProperties(binder(), databaseProperties);

		//String property = databaseProperties.getProperty("db.library");
		//bindConstant().annotatedWith(Names.named("libraryPath")).to(property);
	}

	private static Properties loadProperties(String name)
	{
		Properties properties = new Properties();
		InputStream is = new Object(){}.getClass().getEnclosingClass().getResourceAsStream(name);

		try
		{
			properties.load(is);
		}
		catch (IOException e)
		{
			if (is != null)
			{
				try
				{
					is.close();
				}
				catch (IOException dontcareExc) {}
			}
		}
		return properties;
	}

}
