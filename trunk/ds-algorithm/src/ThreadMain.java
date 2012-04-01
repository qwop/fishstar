import java.util.ArrayList;
import java.util.List;

public class ThreadMain {
	public static void main(String[] args) {
		long currentTime = System.currentTimeMillis();
		CountTool countTool = new CountTool();
		List<Runnable> taskList = new ArrayList<Runnable>();
		taskList.add(new MayTask(countTool, 1)); //1������
		taskList.add(new MayTask(countTool, 2));
		taskList.add(new MayTask(countTool, 3));
		taskList.add(new MayTask(countTool, 4));
		countTool.start(taskList);
		System.out.println("I am main thread, after others is ok. time is " + (System.currentTimeMillis()-currentTime));
	}
}
/**
 *�������񣬲����������Ƿ��Ѿ���� 
 */
class CountTool {
	private Object ob = new Object();
	
	private int countNum;
	private int countNumDest;
	public CountTool() {
	}
	/**
	 *���̵߳��ã����������ŵ�һ���б��У��ڷ���ÿ�����񴴽�һ���߳�ִ������
	 *���ִ����ɺ�ִ�н���������1,�����ж��Ƿ��Ѿ��ﵽִ����ɵ����������������߳�
	 * @param <T>
	 * @param task
	 */
	public void start(List<Runnable> taskList) {
		countNum = taskList.size();
		synchronized (ob) {
			for (Runnable task : taskList) {
				new Thread(task).start();
			}
			try {
				ob.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void oneOk() {
		synchronized (ob) {
			if (++countNumDest >= countNum) {
			    ob.notify();
			}
		}
	}
}
/**
 * ����ִ������
 */
class MayTask implements Runnable{
	private CountTool countTool;
	private int sleepTime;
	public MayTask(CountTool countTool, int sleepTime) {
		this.countTool = countTool;
		this.sleepTime = sleepTime * 1000;
	}
	public void run() {
		try {
			System.out.println("Current thread name is " + Thread.currentThread().getName() + ",i will sleep " + sleepTime);
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		countTool.oneOk();
	}
}

