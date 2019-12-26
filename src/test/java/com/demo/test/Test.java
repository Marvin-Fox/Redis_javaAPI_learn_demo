package com.demo.test;

public class Test {

	 public static void main(String[] args) {
		  Runnable2 r = new Runnable2();
		  Thread t = new Thread(r);
		  t.start();
	}

	

}

class Runnable2 implements Runnable {
	@Override
	public void run() {
//		// TODO Auto-generated method stub
//		for (int i=0; i<40; i++) {
//			if (i%10 == 0 && i!=0) {
//				try {
//					System.out.println("停顿了 " + 2 + "秒");
//					Thread.sleep(2000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//			System.out.println("No " + i);
//		}
		try {
			String threadName=Thread.currentThread().getName();
			System.out.println(threadName+"~~~~~~start");
			Thread.sleep(2000);
			System.out.println(threadName+"~~~~~~end");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}



