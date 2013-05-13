package com.alonso.performance.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorsExp {

	private ThreadPoolExecutor synQueuePool = new ThreadPoolExecutor(2, 3, 60,
			TimeUnit.SECONDS, new SynchronousQueue<Runnable>());

	private ThreadPoolExecutor noLimitQueuePool = new ThreadPoolExecutor(2, 3,
			60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1));

	private ThreadPoolExecutor limitQueuePool = new ThreadPoolExecutor(2, 3,
			60, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2));

	public void testSynQueue() {
		addSufficientWorker(synQueuePool);
		System.out.println("add 2 worker finished. " + synQueuePool);
		synQueuePool.execute(new Worker());
		System.out.println("add 3 worker finished. " + synQueuePool);
		try {
			synQueuePool.execute(new Worker());
			System.out.println("add 4 worker finished. " + synQueuePool);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}

	}

	/**
	 * 等待队列设置为 <code>LinkedBlockingQueue<code>的线程池,最大线程数为maximumPoolSize,
	 * 如何添加等待队列数超过maximumPoolSize+capacity，任务会被拒绝
	 * 
	 * @author alonso 2013-5-3下午4:20:31
	 */
	public void testNolimitQueue() {
		addSufficientWorker(noLimitQueuePool);
		System.out.println("add 2 worker finished. " + synQueuePool);
		noLimitQueuePool.execute(new Worker());
		System.out.println("add 3 worker finished. " + synQueuePool);
		noLimitQueuePool.execute(new Worker());
		System.out.println("add 4 worker finished. " + synQueuePool);
		System.out.println("new queue size : "
				+ noLimitQueuePool.getQueue().size() + " thread count"
				+ +noLimitQueuePool.getActiveCount());
		System.exit(0);
	}

	public static void main(String[] args) {
		new ExecutorsExp().testNolimitQueue();
	}

	private void addSufficientWorker(ThreadPoolExecutor pool) {
		for (int i = 0; i < 2; i++) {
			pool.execute(new Worker());
		}
	}
}

class Worker implements Runnable {

	@Override
	public void run() {
		boolean hasPrint = Boolean.FALSE;
		while (true) {
			if (!hasPrint) {
				System.out.println();
			}
			hasPrint = Boolean.TRUE;
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}