package com.demo.redis04.lock;

import org.junit.BeforeClass;
import org.junit.Test;

import com.demo.redis.util.RedisConnectionPool;

import redis.clients.jedis.Jedis;

/**
 * 分布式锁案例（redis是单线程多路复用，所以redis内部每次只能一个线程进行操作，可以实现分布式锁的思想）
 * 请同时启动 RedisDemo_Lock1 和 RedisDemo_Lock2 来模拟两个服务争抢一个锁资源的场景
 * 当一个服务拿到锁后，另一个服务会循环等待，直到锁被释放后再次争抢锁
 */
public class RedisDemo_Lock2 {
	
private static Jedis jedis=null; 
	
	public static void main(String[] args) {
		
		jedis=RedisConnectionPool.getJedis();
		jedis.select(0);
		
		Runnable runnable=new Runnable() {
			public void run() {
				// TODO Auto-generated method stub
				try {
					String threadName=Thread.currentThread().getName();
					System.out.println(threadName+"~~~~~~start");
					Test1("aa", "RedisDemo_Lock2---"+threadName, 60);
					System.out.println(threadName+"~~~~~~end");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
	
		Thread thread=new Thread(runnable);
		thread.start();
	
		Thread thread2=new Thread(runnable);
		thread2.start();
		
		Thread thread3=new Thread(runnable);
		thread3.start();

		
		Thread thread4=new Thread(runnable);
		thread4.start();


	}


	
	private static void Test1(String lockKey,String requestId,int expireTime)throws Exception{
		while(true){
			if(RedisLockUtil.tryGetDistributedLock(jedis, lockKey, requestId, expireTime)){
				System.out.println(requestId+"-----getLock-----lockKey:"+lockKey);
				
				
				String threadName=Thread.currentThread().getName();
				System.out.println(threadName+"拿到锁后执行自己的程序，需要时间30秒,等待中......");
				Thread.sleep(30000);
				System.out.println(threadName+"程序执行完成");
				
				
				System.out.println(requestId+"-----delLock-----lockKey:"+lockKey);
				RedisLockUtil.releaseDistributedLock(jedis, lockKey, requestId);
				return;
			}
			

			System.out.println(requestId+"没有拿到锁，等待锁释放后再次尝试获得");
			Thread.sleep(2000);
			
		}
	}

}
