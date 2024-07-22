package guard.earth2;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;
import guard.earth.EarthTech;

public class BlockingList_Test
{
	private static int why = 1;
	private static long start;
	private static Thread t;
	private static final AtomicInteger exit = new AtomicInteger();
	private static final LinkedBlockingQueue<String> list1 = new LinkedBlockingQueue<String>();
	private static final EarthTech.BlockingList<String> list2 = new EarthTech.BlockingList<String>();

	public static void main(String[] args) throws InterruptedException
	{
		t = Thread.currentThread();
		list1.add("");//1000 Thread get 1 element
		list2.add("");//1000 Thread get 1 element
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
			    	for(int i2=0;i2<30000;i2++)//change is ok
					{
			    		if(why == 1 || why == 3)
			    		{
				    		try{
								if(null == list1.take())
								{
								    System.out.println("error1");
								}
							   }
				    		catch (InterruptedException e) {e.printStackTrace();}
				    		list1.add("");
			    		}
			    		else
			    		{
				    		if(null == list2.take())
				    		{
					    		System.out.println("error2");
				    		}
				    		list2.add("");
			    		}
					}
			    	if(exit.incrementAndGet() == 1000)
			    	{
			    		String str1;
			    		if(why == 1 || why == 3)
			    		{
			    			str1 = "jdk LinkedBlockingQueue: ";
			    		}
			    		else
			    		{
			    			str1 = "EarthTech.BlockingList:  ";
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