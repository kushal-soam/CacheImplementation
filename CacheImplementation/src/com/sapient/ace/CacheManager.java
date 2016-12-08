package com.sapient.ace;

import java.time.Duration;
import java.time.Instant;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class CacheManager
{

	private static CacheManager cacheManager;
	private static ConcurrentHashMap<Integer, CacheModel> cache = new ConcurrentHashMap<>();
	private static Integer expireInMinutes = 1;

	public static CacheManager getInstance()
	{
		if (cacheManager == null)
		{
			cacheManager = new CacheManager();
			Thread t = new Thread(
					new Runnable()
					{

						@Override
						public void run()
						{
							while (true)
							{
								Iterator<Entry<Integer, CacheModel>> it = cache.entrySet().iterator();
								while (it.hasNext())
								{
									Map.Entry<Integer, CacheModel> map = (Entry<Integer, CacheModel>) it.next();
									removeFromCache(map.getKey());
									try
									{
										Thread.sleep(expireInMinutes);
									} catch (InterruptedException e)
									{
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}

						}
					}
			);
			t.start();
			
		}
		return cacheManager;
	}

	public void putInCache(Integer key, Object data)
	{
		CacheModel object = new CacheModel(data, Instant.now());
		cache.put(key, object);

	}

	public Object getFromCache(Integer key, Object data) throws Exception
	{
		removeFromCache(key);

		cache.get(key).setAccessTime(Instant.now());
		return cache.get(key).getData();
	}

	private static void removeFromCache(Integer key)
	{
		if (cache.get(key).getAccessTime().compareTo(Instant.now().minus(Duration.ofMinutes(expireInMinutes))) <= 0)
		{
			System.out.println("Element removed for key "+key);
			cache.remove(key);
		}
	}
}
