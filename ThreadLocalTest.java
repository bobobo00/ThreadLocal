package others;
/**
 * ThreadLocal:ÿ���߳�����Ĵ洢���أ��ֲ�����
 * ÿ���̶߳���������ݣ����Ĳ���Ӱ�������߳�
 * �����Ļ������ݣ���㡣
 * 1,��������������ã�������������
 * 2��run���������߳��Լ��ġ�
 * @author dell
 *
 */

public class ThreadLocalTest {
	//private static ThreadLocal<Integer> threadLocal=new ThreadLocal<Integer>();
	//���ĳ�ʶֵ
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
			//����ֵ
			threadLocal.set(9);
			System.out.println(Thread.currentThread().getName()+"--->"+threadLocal.get());
			
		}
		
	}
	public static void main(String[] args) {
		//��ȡֵ
		System.out.println(Thread.currentThread().getName()+"--->"+threadLocal.get());
		//����ֵ
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

