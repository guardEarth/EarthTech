package beifen;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class BlockingList_Test
{
	private static long start;
	private static final AtomicInteger exit = new AtomicInteger();
	private static final LinkedBlockingQueue<String> list1 = new LinkedBlockingQueue<String>();
	private static final EarthJUC.FastList<String> list2 = new EarthJUC.FastList<String>();

	public static void main(String[] args) throws InterruptedException
	{
		for(int i=0;i<10;i++)//add 10 item
		{
			list1.add("");
			list2.add("");
		}
		
		start = System.currentTimeMillis();
		for(int i1=0;i1<1000;i1++)//1000 Thread
		{
			new Thread()
			{
				public void run()
			    {
			    	for(int i2=0;i2<30000;i2++)//change is ok
					{
			    		if(null == list2.take())
			    		{
				    		System.out.println("error");
			    		}
			    		list2.add("");
			    		
			    		//jdk test
//			    		try{ 
//							if(null == list1.take())
//							{
//							    System.out.println("error");
//							}
//						   }
//			    		catch (InterruptedException e) {e.printStackTrace();}
//			    		list1.add("");
					}
			    	if(exit.incrementAndGet() == 1000)
			    	{
			    		System.out.println("time="+(System.currentTimeMillis()-start)+"ms");
			    		exit.set(0);
			    		System.exit(0);
			    	}
			    }
			}.start();
		}
	}
}