package beifen;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class ArrayList_Test
{
	private static long start;
	private static final AtomicInteger exit = new AtomicInteger();
	private static final CopyOnWriteArrayList<String> list1 = new CopyOnWriteArrayList<String>();
	private static final EarthJUC.ArrayList<String> list2 = new EarthJUC.ArrayList<String>();

	public static void main(String[] args) throws InterruptedException
	{
		start = System.currentTimeMillis();
		for(int i1=0;i1<1000;i1++)//1000 Thread
		{
			new Thread()
			{
				public void run()
			    {
			    	for(int i2=0;i2<6000;i2++)//change is ok
					{
			    		list2.add("");
			    		if(null == list2.poll())
			    		{
				    		System.out.println("error");
			    		}
			    		
			    		//jdk test
//			    		list1.add("");
//			    		if(null == list1.remove(0))
//				    	{
//			                System.out.println("error");
//			    		}
					}
			    	if(exit.incrementAndGet() == 1000)
			    	{
			    		System.out.println("time="+(System.currentTimeMillis()-start)+"ms");
			    		exit.set(0);
			    	}
			    }
			}.start();
		}
	}
}