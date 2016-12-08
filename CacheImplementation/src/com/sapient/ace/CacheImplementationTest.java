package com.sapient.ace;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.math.RandomUtils;

public class CacheImplementationTest
{

	Map<Integer, CacheModel> cacheMap=new ConcurrentHashMap<>();
	public static void main(String[] args) throws InterruptedException
	{
		CacheManager cacheManager=CacheManager.getInstance();
		
		for(int i=0;i<40;i++)
		{
			Thread.sleep(RandomUtils.nextInt(2000));
			String obj=new String("Obj "+i);
			System.out.println("Item added in Cache: "+obj);
			cacheManager.putInCache(i,obj);
		}
		Thread.sleep(50000);
		for(int i=0;i<40;i++)
		{
			Thread.sleep(RandomUtils.nextInt(2000));
			String obj=new String("Obj "+i);
			System.out.println("Item added in Cache: "+obj);
			cacheManager.putInCache(i,obj);
		}
	}
	
	public <T>T getObject()
	{
		return null;
		
	}
}
