package guard.earth2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.LockSupport;
import guard.earth.EarthTech;

public class ThreadPool_Test
{
	private static int why = 1;
	private static long forNumber = 15000;//change is ok
	private static long start;
	private static Thread thread;
	private static final ExecutorService tp1 = Executors.newFixedThreadPool(64);
	private static final EarthTech.ThreadPool tp2 = new EarthTech.ThreadPool(64);
	private static final LongAdder a = new LongAdder();
	private static final RunnableTemp runnable = new RunnableTemp();
	private static final AtomicInteger exit = new AtomicInteger();

	public static void main(String[] args) throws CloneNotSupportedException, InterruptedException
	{
		thread = Thread.currentThread();
	    for(int i=0;i<4;i++)
	    {
	    	start();
	    	LockSupport.park();
	    }
	    System.exit(0);
	}

	private static void start()
	{
		start = System.currentTimeMillis();
		for(int i1=0;i1<1000;i1++)
		{
			new Thread()
			{
				public void run()
			    {
			    	for(long i2=0;i2<forNumber;i2++)
					{
			    		if(why == 1 || why == 3)
			    		{
			    			tp1.execute(runnable);
			    		}
			    		else
			    		{
			    			tp2.execute(runnable);
			    		}
					}
			    	if(exit.incrementAndGet() == 1000)
			    	{
			    		String str;
			    		while(a.sum() != forNumber*1000L)
			    		{
			    			LockSupport.parkNanos(1000000);
			    		}
			    		if(why == 1 || why == 3)
			    		{
			    			str = "jdk ThreadPool:       ";
			    		}
			    		else
			    		{
			    			str = "EarthTech.ThreadPool: ";
			    		}
			    		if(why == 1 || why == 3) System.out.print("a="+a.sum()+" ");
			    		else                     System.out.print("a="+a.sum()+" ");
			    		System.out.println(str+"time="+(System.currentTimeMillis()-start)+"ms");
			    		if(++why == 3)
			    		{
			    			System.out.println();
			    		}
			    		a.reset();
			    		exit.set(0);
			    		LockSupport.unpark(thread);
			    	}
			    }
			}.start();
		}
	}
	
	private static class RunnableTemp implements Runnable
	{
		public void run()
		{
			a.increment();
		}
	}
}