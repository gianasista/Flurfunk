package de.iwelt;

import java.util.Date;

/**
 * @author gianasista
 */
public class Rumor
{
	private String text;
	private Date date = new Date();

	public Rumor()
	{
		super();
	}

	public Rumor(final Rumor rumor)
	{
		this.text = rumor.text;
		this.date = rumor.date;
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	@Override
	public String toString()
	{
		return String.format("[Rumor date = %s, text = %s]", date, text);
	}
}
