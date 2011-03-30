package de.iwelt;

import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.protocol.http.WebApplication;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 *
 * @see de.iwelt.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{
	public WicketApplication()
	{
	}

	@Override
	protected void init()
	{
		super.init();

		addComponentInstantiationListener(new GuiceComponentInjector(this, new RumorModule()));
	}

	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	public Class<RumorPage> getHomePage()
	{
		return RumorPage.class;
	}

}
