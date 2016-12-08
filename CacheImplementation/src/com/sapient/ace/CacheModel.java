package com.sapient.ace;

import java.time.Instant;

public class CacheModel
{

	private Object data;
	private Instant accessTime;

	public CacheModel(Object data, Instant accessTime)
	{
		super();
		this.data = data;
		this.accessTime = accessTime;
	}

	public Object getData()
	{
		return data;
	}

	public void setData(Object data)
	{
		this.data = data;
	}

	public Instant getAccessTime()
	{
		return accessTime;
	}

	public void setAccessTime(Instant accessTime)
	{
		this.accessTime = accessTime;
	}

}
