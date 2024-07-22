package guard.earth2;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;
import guard.earth.EarthTech;

public class LinkedList_Test
{
	private static int why = 1;
	private static long start;
	private static Thread t;
	private static final AtomicInteger exit = new AtomicInteger();
	private static final LinkedList<String> list1 = new LinkedList<String>();
	private static final ConcurrentLinkedQueue<String> list2 = new ConcurrentLinkedQueue<String>();
	private static final EarthTech.LinkedList<String> list3 = new EarthTech.LinkedList<String>();

	public static void main(String[] args) throws InterruptedException
	{
		int n = 30000;
		for(int i=0;i<n;i++)
		{
			list1.add(i+"");
			list3.add(i+"");
		}
		System.out.println("=========Object find/数据搜索=========");
		for(int i1=0;i1<2;i1++)
		{
			start = System.currentTimeMillis();
			for(int i2=0;i2<n;i2++)
			{
				if(i1 == 0)
				{
					if(list1.contains(i2+"") == false)
					{
						System.out.println("error1");
					}
				}
				else
				{
					if(list3.contains(i2+"") == false)
					{
						System.out.println("error2");
					}
				}
			}
			if(i1 == 0)
			{
				System.out.println("jdk LinkedList:        time="+(System.currentTimeMillis()-start)+"ms");
			}
			else
			{
				System.out.println("EarthTech.LinkedList:  time="+(System.currentTimeMillis()-start)+"ms");
			}
		}
		list1.clear();
		list3.clear();
		n = 60000;;
		for(int i=0;i<n;i++)
		{
			list1.add(i+"");
			list3.add(i+"");
		}
		
		System.out.println();
		System.out.println("=========Index find/索引定位=========");
		for(int i1=0;i1<2;i1++)
		{
			start = System.currentTimeMillis();
			for(int i3=0;i3<n;i3++)
			{
				if(i1 == 0)
				{
					if(list1.get(i3).equals(i3+"") == false)
					{
						System.out.println("error1");
					}
				}
				else
				{
					if(list3.get(i3).equals(i3+"") == false)
					{
						System.out.println("error2");
					}
				}
			}
			if(i1 == 0)
			{
				System.out.println("jdk LinkedList:        time="+(System.currentTimeMillis()-start)+"ms");
			}
			else
			{
				System.out.println("EarthTech.LinkedList:  time="+(System.currentTimeMillis()-start)+"ms");
			}
		}
		list1.clear();
		list3.clear();
		System.out.println("");
		System.out.println("=========1000 Thread add/poll=========");
		
		t = Thread.currentThread();
	    for(int i=0;i<2;i++)
	    {
	    	start();
	    	LockSupport.park();
	    }
	}

	private static void start() throws InterruptedException
	{
		start = System.currentTimeMillis();
		for(int i1=0;i1<1000;i1++)//1000 Thread
		{
			new Thread()
			{
				public void run()
			    {
			    	for(int i2=0;i2<20000;i2++)
					{
			    		if(why == 1)
			    		{
			    			list2.add("");
				    		if(null == list2.poll())
					    	{
				                System.out.println("error1");
				    		}
			    		}
			    		else
			    		{
				    		list3.add("");
				    		if(null == list3.poll())
				    		{
					    		System.out.println("error2");
				    		}
			    		}
					}
			    	if(exit.incrementAndGet() == 1000)
			    	{
			    		String str1;
			    		if(why == 1)
			    		{
			    			str1 = "jdk ConcurrentLinkedQueue: ";
			    		}
			    		else
			    		{
			    			str1 = "EarthTech.LinkedList:      ";
			    		}
			    		System.out.print(str1+"time="+(System.currentTimeMillis()-start)+"ms");
			    		System.out.println();
			    		++why;
			    		exit.set(0);
			    		LockSupport.unpark(t);
			    	}
			    }
			}.start();
		}
	}
}