package com.demo.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class DemoTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void test() {
		try {
			String threadName=Thread.currentThread().getName();
			System.out.println(threadName+"~~~~~~start");
			Thread.sleep(2000);
			System.out.println(threadName+"~~~~~~end");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void test2(){
		
		Runnable runnable=new Runnable() {
			public void run() {
				// TODO Auto-generated method stub
				try {
					String threadName=Thread.currentThread().getName();
					System.out.println(threadName+"~~~~~~start");
					Thread.sleep(2000);
					System.out.println(threadName+"~~~~~~end");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		Thread thread=new Thread(runnable);
		thread.start();

		
	}
	
	@Test
	public void test3(){
		
	
		
		Thread thread=new Thread(new MyRunnable1());
		thread.start();

		
	}
	
	


}
class MyRunnable1 implements Runnable {
    @Override
    public void run() {
    	try {
			String threadName=Thread.currentThread().getName();
			System.out.println(threadName+"~~~~~~start");
			Thread.sleep(2000);
			System.out.println(threadName+"~~~~~~end");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
