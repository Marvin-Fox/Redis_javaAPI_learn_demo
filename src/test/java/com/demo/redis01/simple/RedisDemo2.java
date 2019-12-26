package com.demo.redis01.simple;

import org.junit.Test;

import com.demo.redis.util.RedisConnectionPool;

import redis.clients.jedis.Jedis;

public class RedisDemo2 {

	@Test
	public void demo(){
		Jedis jedis=RedisConnectionPool.getJedis();
		System.out.println("服务正在运行: "+jedis.ping());
		System.out.println("获取当前存储数据的个数: "+jedis.dbSize());
	}
}
