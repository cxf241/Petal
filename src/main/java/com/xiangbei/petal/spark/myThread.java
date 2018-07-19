package com.xiangbei.petal.spark;

/**
 * @ClassName: myThread
 * @Description: TODO
 * @author: tyrion
 * @date: 2018年7月16日 下午6:52:25
 */

class MyThread extends Thread {

	private int i = 0;

	@Override
	public void run() {
		for (i = 0; i < 5; i++) {
			System.out.println(Thread.currentThread().getName() + " " + i);
		}
	}
}
public class myThread {

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			System.out.println(Thread.currentThread().getName() + " " + i);
			if (i == 3) {
				Thread myThread1 = new MyThread(); // 创建一个新的线程 myThread1 此线程进入新建状态
				Thread myThread2 = new MyThread(); // 创建一个新的线程 myThread2 此线程进入新建状态
				myThread1.start(); // 调用start()方法使得线程进入就绪状态
				myThread2.start(); // 调用start()方法使得线程进入就绪状态
			}
		}
	}

}
