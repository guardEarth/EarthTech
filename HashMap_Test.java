package beifen;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class HashMap_Test
{
	private static long start;
	private static final AtomicInteger exit = new AtomicInteger();
	private static ConcurrentHashMap<String,String> hm1 = new ConcurrentHashMap<String,String>();
	private static EarthJUC.HashMap<String,String> hm2 = new EarthJUC.HashMap<String,String>();

	public static void main(String[] args) throws InterruptedException
	{
		start = System.currentTimeMillis();
		for(int i1=0;i1<1000;i1++)//1000 Thread
		{
			new Thread()
			{
				public void run()
			    {
					final String e = "abc"+Thread.currentThread()+"def";
			    	for(int i2=0;i2<20000;i2++)//change is ok
					{
			    		if(null != hm2.put(e,e)) System.out.println("error");
			    		if(e.equals(hm2.get(e)) == false) System.out.println("error");
			    		if(e.equals(hm2.remove(e)) == false) System.out.println("error");
			    		if(null != hm2.get(e)) System.out.println("error");
			    		
			    		//jdk test
//			    		if(null != hm1.put(e,e)) System.out.println("error");
//			    		if(e.equals(hm1.get(e)) == false) System.out.println("error");
//			    		if(e.equals(hm1.remove(e)) == false) System.out.println("error");
//			    		if(null != hm1.get(e)) System.out.println("error");
					}
			    	if(exit.incrementAndGet() == 1000)
			    	{
			    		System.out.println("time="+(System.currentTimeMillis()-start)+"ms");
			    		exit.set(0);
			    	}
			    }
			}.start();
		}
	}
}