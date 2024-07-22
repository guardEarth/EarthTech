package guard.earth2;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.LockSupport;
import guard.earth.EarthTech;

public class SpinLock_Test
{
	private static int why = 1;
	private static long start;
	private static Thread thread;
	private static int a = 0;
	private static final ReentrantLock lock1 = new ReentrantLock(false);
	private static final EarthTech.Lock lock2 = new EarthTech.Lock(false);//Non fair
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
		for(int i=0;i<1000;i++)
		{
			new Thread()
			{
				public void run()
			    {
			    	for(int i1=0;i1<100000;i1++)//change is ok
					{
			    		if(why == 1 || why == 3)
			    		{
			    			lock1.lock();
				    		try{
				    	        ++a;
				    		   }
				    		finally
				    		{
				    			lock1.unlock();
				    		}
			    		}
			    		else
			    		{
			    			lock2.lock();
				    		try{
				    	        ++a;
				    		   }
				    		finally
				    		{
				    			lock2.unlock();
				    		}
			    		}
					}
			    	if(exit.incrementAndGet() == 1000)
			    	{
			    		String str;
			    		if(why == 1 || why == 3)
			    		{
			    			str = "jdk ReentrantLock: ";
			    		}
			    		else
			    		{
			    			str = "EarthTech.Lock:    ";
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