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

		System.out.println("���ų�ʼ�����!") ; //  lunch start done");
		for (int i = 0; i < workerCount; ++i) {
			Thread t = new Thread(new Worker(lunchSignal, startSignal,
					doneSignal));
			t.setName("����" + (i + 1));
			t.start();
		}

		try {
			lunchSignal.await();
			System.out.println("************��ͷ: ���й����Ѿ�Ԥ����ϣ�\t\t00************" ); // all worker lunched");
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		System.out.println("************��ͷ: �����źŸ����еĹ��˿�ʼ����!\t01************" ); // send signal to all worker to start work");
		startSignal.countDown();

		try {
			System.out.println("************��ͷ: �ȴ����еĹ��ˣ�\t\t\t02************" ) ; // wait for all worker");
			doneSignal.await();
			System.out.println("************��ͷ: ���й�������ɣ�\t\t\t03************" ); //all worker finished");
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
			System.out.println("\t" + name + ": ���湤ͷ,Ԥ�����,����ִ�У�"); // lunched and wait for start signal");
			lunchSignal.countDown();
			lunchSignal.await();

			startSignal.await();
			sleep( 1000 );
			System.out.println("\t\t" + name + ": ��ʼ����,֯ë�£�"); // start to do work");

			doWork();
			sleep( 1000 );
			System.out.println("\t\t\t" + name + ": ë��֯�꣬�ȴ��������ˣ�") ; //finish work and wait for other worker");

			doneSignal.countDown();
			doneSignal.await();
			System.out.println("\t\t\t\t" + name + ": ִ�����" ); //quit");
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