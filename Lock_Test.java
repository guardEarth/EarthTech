package beifen;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Lock_Test
{
	private static long start;
	private static int a = 0;
	private static final ReentrantLock lock1 = new ReentrantLock(false);
	private static final EarthJUC.SpinLock lock2 = new EarthJUC.SpinLock();
	private static final AtomicInteger exit = new AtomicInteger();

	public static void main(String[] args) throws CloneNotSupportedException, InterruptedException
	{
		start = System.currentTimeMillis();
		for(int i1=0;i1<1000;i1++)//1000 Thread
		{
			new Thread()
			{
				public void run()
			    {
			    	for(int i2=0;i2<200000;i2++)//change is ok
					{
			    		lock2.lock();
			    		try{
			    	        ++a;
			    		   }
			    		finally
			    		{
			    			lock2.unlock();
			    		}
			    		
			    		//jdk test
//			    		lock1.lock();
//			    		try{
//			    	        ++a;
//			    		   }
//			    		finally
//			    		{
//			    			lock1.unlock();
//			    		}
					}
			    	if(exit.incrementAndGet() == 1000)
			    	{
			    		System.out.print("a="+a+" ");                 
			    		System.out.println("time="+(System.currentTimeMillis()-start)+"ms");
			    		a = 0;
			    		exit.set(0);
			    	}
			    }
			}.start();
		}
	}
}