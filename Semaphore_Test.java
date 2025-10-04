package beifen;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Semaphore_Test
{
	private static long start;
	private static int a = 0;
	private static final Semaphore se1 = new Semaphore(1);
	private static final EarthJUC.Semaphore se2 = new EarthJUC.Semaphore(1);
	private static final AtomicInteger exit = new AtomicInteger();

	public static void main(String[] args) throws CloneNotSupportedException, InterruptedException
	{
		start = System.currentTimeMillis();
		for(int i1=0;i1<1000;i1++)
		{
			new Thread()
			{
				public void run()
			    {
			    	for(int i2=0;i2<60000;i2++)//change is ok
					{
			    		se2.acquire();
			    		try{
			    	        ++a;
			    		   }
			    		finally
			    		{
			    			se2.release();
			    		}
			    		
			    		//jdk test
//			    		try{
//							se1.acquire();
//							++a;
//						  }
//		    			catch (InterruptedException e){e.printStackTrace();}
//			    		finally
//			    		{
//			    			se1.release();;
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