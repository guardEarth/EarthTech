# EarthJUC
---------------- English ----------------<br/>
This is an efficient concurrent toolkit, if one day, extraterrestrial civilization invades the earth, this toolkit may be an attempt by the software layer to fight against extraterrestrial civilization<br/>
<br/>
usage method：<br/>
1：Import "EarthJUC.jar" into the project<br/>
2：Run any of the following files<br/>
ArrayList_Test.java<br/>
LinkedList_Test.java<br/>
BlockingList_Test.java<br/>
Lock_Test.java<br/>
SpinLock_Test.java<br/>
Semaphore_Test.java<br/>
HashMap_Test.java<br/>
ThreadPool_Test.java<br/>

JDK 1.9 is required to run, and This package has concurrency performance that is not inferior to JDK Doug Lea JUC<br/>
<p>performance：</p>
<table width="779" height="320"  border="1" bordercolor="aaaaff" cellspacing="0" style="text-indent:6px;  color: #000000; font-size:15px;">
   <tr>
      <td width="236"><b>class1</b></td>
      <td width="236"><b>class2</b></td>
      <td><b>result</b></td>
   <tr>
      <td>EarthJUC.Lock</td>
      <td>ReentrantLock</td>
      <td>>10%</td>
   </tr>
   <tr>
      <td>EarthJUC.SpinLock</td>
      <td>X</td>
      <td>>∞（Java does not have spinlock）</td>
   </tr>
   <tr>
      <td>EarthJUC.ReentrantSpinLock</td>
      <td>ReentrantLock</td>
      <td>>30%</td>
   </tr>
   <tr>
      <td>EarthJUC.RWLock</td>
      <td>StampedLock</td>
      <td>>∞（StampedLock is not secure）</td>
   </tr>
   <tr>
      <td>EarthJUC.Semaphore</td>
      <td>Semaphore</td>
      <td>>10%</td>
   </tr>
   <tr>
      <td>EarthJUC.ArrayList</td>
      <td>CopyOnWriteArrayList</td>
      <td>>50%</td>
   </tr>
   <tr>
      <td>EarthJUC.LinkedList</td>
      <td>ConcurrentLinkedQueue</td>
      <td>>20%</td>
   </tr>
   <tr>
      <td>EarthJUC.BlockingList</td>
      <td>LinkedBlockingQueue</td>
      <td>>5%</td>
   </tr>
   <tr>
      <td>EarthJUC.HashMap</td>
      <td>ConcurrentHashMap</td>
      <td>>10%</td>
   </tr>
   <tr>
      <td>EarthJUC.ThreadPool</td>
      <td>ThreadPoolExecutor</td>
      <td>>10%</td>
   </tr>
</table><br><br>

---------------- 中文 ----------------<br/>
这是一款高效的并发工具包, 如果某一天, 有地外文明侵犯地球, 此包的性能也许能派上用场<br/>
<br/>
使用方式：<br/>
1：把 EarthJUC.jar 导入到项目里<br/>
2：运行以下任意文件即可<br/>
ArrayList_Test.java        //数组<br/>
LinkedList_Test.java      //链表<br/>
BlockingList_Test.java   //堵塞链表<br/>
Lock_Test.java              //互斥锁<br/>
SpinLock_Test.java       //自旋锁<br/>
Semaphore_Test.java   //信号量<br/>
HashMap_Test.java      //哈希表<br/>
ThreadPool_Test.java   //线程池<br/>
<br/>
需要jdk1.9才能运行，此包拥有不输 jdk Doug Lea JUC的并发性能。<br/>
<p>性能对比：</p>
<table width="738" height="320"  border="1" bordercolor="aaaaff" cellspacing="0" style="text-indent:6px;  color: #000000; font-size:15px;">
   <tr>
      <td width="236"><b>类1</b></td>
      <td width="236"><b>类2</b></td>
      <td width="266"><b>结果</b></td>
   <tr>
      <td>EarthJUC.Lock</td>
      <td>ReentrantLock</td>
      <td>>10%</td>
   </tr>
   <tr>
      <td>EarthJUC.SpinLock</td>
      <td>X</td>
      <td>>∞（java没有自旋锁）</td>
   </tr>
   <tr>
      <td>EarthJUC.ReentrantSpinLock</td>
      <td>ReentrantLock</td>
      <td>>30%</td>
   </tr>
   <tr>
      <td>EarthJUC.RWLock</td>
      <td>StampedLock</td>
      <td>>∞（StampedLock是不安全的）</td>
   </tr>
   <tr>
      <td>EarthJUC.Semaphore</td>
      <td>Semaphore</td>
      <td>>10%</td>
   </tr>
   <tr>
      <td>EarthJUC.ArrayList</td>
      <td>CopyOnWriteArrayList</td>
      <td>>50%</td>
   </tr>
   <tr>
      <td>EarthJUC.LinkedList</td>
      <td>ConcurrentLinkedQueue</td>
      <td>>20%</td>
   </tr>
   <tr>
      <td>EarthJUC.BlockingList</td>
      <td>LinkedBlockingQueue</td>
      <td>>5%</td>
   </tr>
   <tr>
      <td>EarthJUC.HashMap</td>
      <td>ConcurrentHashMap</td>
      <td>>10%</td>
   </tr>
   <tr>
      <td>EarthJUC.ThreadPool</td>
      <td>ThreadPoolExecutor</td>
      <td>>10%</td>
   </tr>
</table><br><br>
