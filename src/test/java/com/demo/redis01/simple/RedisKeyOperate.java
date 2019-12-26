package com.demo.redis01.simple;

import java.util.Set;

import org.junit.Test;

import com.demo.redis.util.RedisConnectionPool;

import redis.clients.jedis.Jedis;

public class RedisKeyOperate {
	
	@Test
	public void demo() {
		
		Jedis jedis=RedisConnectionPool.getJedis();
		
		/**
         * 示例1： 相当于执行 keys *
         */
		Set<String> keys=jedis.keys("*");
		//这是jdk8 的写法（新特性）
		keys.stream().forEach(System.out::println);
        System.out.println("======================");
        
        /**
         * 示例2：  相当于执行  keys k?
         */
        Set<String> keys1 = jedis.keys("k?");
        keys1.stream().forEach(System.out::println);
        System.out.println("======================");
 
        /**
         * 示例3： 相当于执行  exists k1
         */
        Boolean existsKey = jedis.exists("k1");
        System.out.println("existsKey = " + existsKey);
        System.out.println("======================");
        
        /**
         * 示例4： 相当于执行 del k1
         */
        Long delK1 = jedis.del("k1");
        System.out.println("delK1 = " + delK1);
        System.out.println("======================");
 
        /**
         * 示例5： 相当于执行 type k2
         */
        String typeK2 = jedis.type("k2");
        System.out.println("typek2 = " + typeK2);
        System.out.println("======================");
 
 
        /**
         * 示例6： 相当于执行 rename k2 k22
         */
        String renameK2 = jedis.rename("k2", "k22");
        System.out.println("renameK2 = " + renameK2);
        System.out.println("======================");
 
        /**
         * 示例7: 相当于执行 renamenx t1 t11
         */
        Long renamenxt1 = jedis.renamenx("t1", "t11");
        System.out.println("renamenxt1 = "+renamenxt1);

	}

}
