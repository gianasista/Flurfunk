package de.iwelt;

import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.protocol.http.WebApplication;

import com.google.inject.Binder;
import com.google.inject.Module;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 *
 * @see de.iwelt.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{
    /**
     * Constructor
     */
	public WicketApplication()
	{
		System.setProperty("sqlite4java.library.path", "/Users/vera/.m2/repository/com/almworks/sqlite4java/libsqlite4java-osx/0.213");
	}

	@Override
	protected void init()
	{
		super.init();

		addComponentInstantiationListener(new GuiceComponentInjector(this, getModule()));
	}

	private Module getModule() {
		return new Module() {
			public void configure(Binder binder) {
				// binder.bind(IService.class).to(Service.class);
			}
		};
	}

	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	public Class<RumorPage> getHomePage()
	{
		return RumorPage.class;
	}

}
