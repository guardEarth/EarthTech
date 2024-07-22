package guard.earth2;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;
import guard.earth.EarthTech;

public class HashMap_Test
{
	private static int why = 1;
	private static long start;
	private static Thread t;
	private static final AtomicInteger exit = new AtomicInteger();
	private static ConcurrentHashMap<String,String> hm1 = new ConcurrentHashMap<String,String>();
	private static EarthTech.HashMap<String,String> hm2 = new EarthTech.HashMap<String,String>();

	public static void main(String[] args) throws InterruptedException
	{
		t = Thread.currentThread();
	    for(int i=0;i<4;i++)
	    {
	    	start();
	    	LockSupport.park();
	    }
	}

	private static void start() throws InterruptedException
	{
		start = System.currentTimeMillis();
		for(int i1=0;i1<1000;i1++)
		{
			new Thread()
			{
				public void run()
			    {
					final String e = "abc"+Thread.currentThread()+"def";
			    	for(int i2=0;i2<90000;i2++)//change is ok
					{
			    		if(why == 1 || why == 3)
			    		{
				    		if(null != hm1.put(e,e))
				    		{
				    			System.out.println("error");
				    		}
				    		if(e.equals(hm1.get(e)) == false)
				    		{
				    			System.out.println("error");
				    		}
				    		if(e.equals(hm1.remove(e)) == false)
                            {
				    			System.out.println("error");
				    		}
				    		if(null != hm1.get(e))
                            {
				    			System.out.println("error");
				    		}
			    		}
			    		else
			    		{
			    			if(null != hm2.put(e,e))
				    		{
				    			System.out.println("error");
				    		}
				    		if(e.equals(hm2.get(e)) == false)
				    		{
				    			System.out.println("error");
				    		}
				    		if(e.equals(hm2.remove(e)) == false)
                            {
				    			System.out.println("error");
				    		}
				    		if(null != hm2.get(e))
                            {
				    			System.out.println("error");
				    		}
			    		}
					}
			    	if(exit.incrementAndGet() == 1000)
			    	{
			    		String str1;
			    		if(why == 1 || why == 3)
			    		{
			    			str1 = "jdk ConcurrentHashMap: ";
			    		}
			    		else
			    		{
			    			str1 = "EarthTech.HashMap:     ";
			    		}
			    		System.out.println(str1+"time="+(System.currentTimeMillis()-start)+"ms");
			    		if(++why == 3)
			    		{
			    			System.out.println();
			    		}
			    		exit.set(0);
			    		LockSupport.unpark(t);
			    	}
			    }
			}.start();
		}
	}
}