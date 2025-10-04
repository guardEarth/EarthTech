package beifen;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.LockSupport;

public class ThreadPool_Test
{
	private static long start;
	private static final ExecutorService tp1 = Executors.newFixedThreadPool(64);
	private static final EarthJUC.ThreadPool tp2 = new EarthJUC.ThreadPool(64);
	private static final LongAdder a = new LongAdder();
	private static final RunnableTemp runnable = new RunnableTemp();
	private static final AtomicInteger exit = new AtomicInteger();

	public static void main(String[] args) throws CloneNotSupportedException, InterruptedException
	{
		start = System.currentTimeMillis();
		long forNumber = 60000;//change is ok
		for(int i1=0;i1<1000;i1++)
		{
			new Thread()
			{
				public void run()
			    {
			    	for(long i2=0;i2<forNumber;i2++)
					{
			    		tp2.submit(runnable);
			    		
//			    		jdk test
//			    		tp1.submit(runnable);
					}
			    	if(exit.incrementAndGet() == 1000)
			    	{
			    		while(a.sum() != forNumber*1000L)
			    		{
			    			LockSupport.parkNanos(1000000);
			    		}
			    		System.out.print("a="+a.sum()+" ");                 
			    		System.out.println("time="+(System.currentTimeMillis()-start)+"ms");
			    		a.reset();
			    		exit.set(0);
			    		tp1.shutdown();
			    		tp2.clear();
			    		System.exit(0);
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