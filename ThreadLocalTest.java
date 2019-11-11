package others;
/**
 * ThreadLocal:每个线程自身的存储本地，局部区域
 * 每个线程都自身的数据，更改不会影响其他线程
 * 上下文环境数据，起点。
 * 1,构造器：哪里调用，就是属于哪里
 * 2，run方法：本线程自己的。
 * @author dell
 *
 */

public class ThreadLocalTest {
	//private static ThreadLocal<Integer> threadLocal=new ThreadLocal<Integer>();
	//更改初识值
	/*private static ThreadLocal<Integer> threadLocal=new ThreadLocal<Integer>() {
		protected Integer initialValue() {
			return 200;
		};
	};*/
	private static ThreadLocal<Integer> threadLocal=ThreadLocal.withInitial(()->200);
	private static ThreadLocal<Integer> threadLocal1=InheritableThreadLocal.withInitial(()->20);
	public static class MyRun implements Runnable{
          public MyRun() {
        	  threadLocal.set(-100);
        	  System.out.println(Thread.currentThread().getName()+"--->"+threadLocal.get());
          }
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName()+"--->"+threadLocal.get());
			//设置值
			threadLocal.set(9);
			System.out.println(Thread.currentThread().getName()+"--->"+threadLocal.get());
			
		}
		
	}
	public static void main(String[] args) {
		//获取值
		System.out.println(Thread.currentThread().getName()+"--->"+threadLocal.get());
		//设置值
		threadLocal.set(99);
		System.out.println(Thread.currentThread().getName()+"--->"+threadLocal.get());
		//new Thread(new MyRun()).start();
		System.out.println("--------------------------");
		
		System.out.println(Thread.currentThread().getName()+"--->"+threadLocal1.get());
		new Thread(()->{
			System.out.println(Thread.currentThread().getName()+"--->"+threadLocal1.get());
			threadLocal1.set(7);
			System.out.println(Thread.currentThread().getName()+"--->"+threadLocal1.get());
		}).start();
		System.out.println(Thread.currentThread().getName()+"--->"+threadLocal1.get());
	}

}

