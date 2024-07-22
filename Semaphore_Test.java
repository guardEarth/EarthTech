package guard.earth2;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;
import guard.earth.EarthTech;

public class Semaphore_Test
{
	private static int why = 1;
	private static long start;
	private static Thread thread;
	private static int a = 0;
	private static final Semaphore se1 = new Semaphore(1);
	private static final EarthTech.Semaphore se2 = new EarthTech.Semaphore(1);
	private static final AtomicInteger exit = new AtomicInteger();

	public static void main(String[] args) throws CloneNotSupportedException, InterruptedException
	{
		thread = Thread.currentThread();
	    for(int i=0;i<4;i++)
	    {
	    	start();
	    	LockSupport.park();
	    }
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
			    	for(int i2=0;i2<100000;i2++)//change is ok
					{
			    		if(why == 1 || why == 3)
			    		{
			    			try{
								se1.acquire();
								++a;
							  }
			    			catch (InterruptedException e){e.printStackTrace();}
				    		finally
				    		{
				    			se1.release();;
				    		}
			    		}
			    		else
			    		{
			    			se2.acquire();
				    		try{
				    	        ++a;
				    		   }
				    		finally
				    		{
				    			se2.release();
				    		}
			    		}
					}
			    	if(exit.incrementAndGet() == 1000)
			    	{
			    		String str;
			    		if(why == 1 || why == 3)
			    		{
			    			str = "jdk Semaphore:       ";
			    		}
			    		else
			    		{
			    			str = "EarthTech.Semaphore: ";
			    		}
			    		if(why == 1 || why == 3) System.out.print("a="+a+" ");
			    		else                     System.out.print("a="+a+" ");
			    		System.out.println(str+"time="+(System.currentTimeMillis()-start)+"ms");
			    		if(++why == 3)
			    		{
			    			System.out.println();
			    		}
			    		a = 0;
			    		exit.set(0);
			    		LockSupport.unpark(thread);
			    	}
			    }
			}.start();
		}
	}
}