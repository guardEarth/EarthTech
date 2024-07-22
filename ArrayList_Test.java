package guard.earth2;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;
import guard.earth.EarthTech;

public class ArrayList_Test
{
	private static int why = 1;
	private static long start;
	private static Thread t;
	private static final AtomicInteger exit = new AtomicInteger();
	private static final CopyOnWriteArrayList<String> list1 = new CopyOnWriteArrayList<String>();
	private static final EarthTech.ArrayList<String> list2 = new EarthTech.ArrayList<String>();

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
			    	for(int i2=0;i2<3000;i2++)//change is ok
					{
			    		if(why == 1 || why == 3)
			    		{
			    			list1.add("");
				    		if(null == list1.remove(0))
					    	{
				                System.out.println("error1");
				    		}
			    		}
			    		else
			    		{
				    		list2.add("");
				    		if(null == list2.poll())
				    		{
					    		System.out.println("error2");
				    		}
			    		}
					}
			    	if(exit.incrementAndGet() == 1000)
			    	{
			    		String str1;
			    		if(why == 1 || why == 3)
			    		{
			    			str1 = "jdk CopyOnWriteArrayList: ";
			    		}
			    		else
			    		{
			    			str1 = "EarthTech.ArrayList:      ";
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