import java.util.ArrayList;
import java.util.List;

public class ThreadMain {
	public static void main(String[] args) {
		long currentTime = System.currentTimeMillis();
		CountTool countTool = new CountTool();
		List<Runnable> taskList = new ArrayList<Runnable>();
		taskList.add(new MayTask(countTool, 1)); //1代表秒
		taskList.add(new MayTask(countTool, 2));
		taskList.add(new MayTask(countTool, 3));
		taskList.add(new MayTask(countTool, 4));
		countTool.start(taskList);
		System.out.println("I am main thread, after others is ok. time is " + (System.currentTimeMillis()-currentTime));
	}
}
/**
 *启动任务，并管理任务是否都已经完成 
 */
class CountTool {
	private Object ob = new Object();
	
	private int countNum;
	private int countNumDest;
	public CountTool() {
	}
	/**
	 *主线程调用，将任务对象放到一个列表中，在方法每个任务创建一个线程执行任务
	 *如果执行完成后，执行结果完成数加1,并且判断是否已经达到执行完成的总数，是则唤醒主线程
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
 * 具体执行任务
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

