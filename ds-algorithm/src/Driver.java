import java.util.concurrent.CountDownLatch;

class Driver {
	
	public static void main(String[] args) {
		new Driver().execute();
	}
	
	public void execute() {
		final int workerCount = 5;
		CountDownLatch lunchSignal = new CountDownLatch(workerCount);
		CountDownLatch startSignal = new CountDownLatch(1);
		CountDownLatch doneSignal = new CountDownLatch(workerCount);

		System.out.println("门闩初始化完毕!") ; //  lunch start done");
		for (int i = 0; i < workerCount; ++i) {
			Thread t = new Thread(new Worker(lunchSignal, startSignal,
					doneSignal));
			t.setName("工人" + (i + 1));
			t.start();
		}

		try {
			lunchSignal.await();
			System.out.println("************工头: 所有工人已经预备完毕！\t\t00************" ); // all worker lunched");
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		System.out.println("************工头: 发送信号给所有的工人开始启动!\t01************" ); // send signal to all worker to start work");
		startSignal.countDown();

		try {
			System.out.println("************工头: 等待所有的工人！\t\t\t02************" ) ; // wait for all worker");
			doneSignal.await();
			System.out.println("************工头: 所有工人已完成！\t\t\t03************" ); //all worker finished");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Worker implements Runnable {
	private final CountDownLatch lunchSignal;
	private final CountDownLatch startSignal;
	private final CountDownLatch doneSignal;

	Worker(CountDownLatch lunchSignal, CountDownLatch startSignal,
			CountDownLatch doneSignal) {
		this.lunchSignal = lunchSignal;
		this.startSignal = startSignal;
		this.doneSignal = doneSignal;
	}

	public void run() {
		String name = Thread.currentThread().getName();
		try {
			sleep( 1000 );
			System.out.println("\t" + name + ": 报告工头,预备完成,可以执行！"); // lunched and wait for start signal");
			lunchSignal.countDown();
			lunchSignal.await();

			startSignal.await();
			sleep( 1000 );
			System.out.println("\t\t" + name + ": 开始工作,织毛衣！"); // start to do work");

			doWork();
			sleep( 1000 );
			System.out.println("\t\t\t" + name + ": 毛衣织完，等待其他工人！") ; //finish work and wait for other worker");

			doneSignal.countDown();
			doneSignal.await();
			System.out.println("\t\t\t\t" + name + ": 执行完毕" ); //quit");
		} catch (InterruptedException ex) {
		}
	}

	private void sleep(int i) {
		try {
			Thread.sleep ( i );
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void doWork() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}