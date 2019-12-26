package com.demo.redis01.simple;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class RedisDemo1 {
	
	@Test
	public void demo(){
		//连接本地的 Redis 服务(地址和端口号)
		Jedis jedis=new Jedis("127.0.0.1", 6379);
				
		System.out.println("连接成功");
		//查看服务是否运行
		System.out.println("服务正在运行: "+jedis.ping());
		//切换使用第0个库（默认使用0）
		jedis.select(0);
		testString(jedis);
	}
	
	/**
	 * redis操作字符串
	 * @param jedis
	 */
	private void testString(Jedis jedis) {
		//添加数据
		jedis.set("name", "jerry");
		System.out.println("添加数据操作："+jedis.get("name"));
		
		//拼接字符串
        jedis.append("name", ".com");
        System.out.println("拼接字符串操作："+jedis.get("name"));
        
        //删除数据
        jedis.del("name");
        System.out.println("删除数据操作："+jedis.get("name"));
        
        //设置多个键值对
        jedis.mset("name","yc","age","22","qq","1933108196");
        jedis.incr("age");//加1操作
        System.out.println("设置多个键值对操作："+jedis.get("name") + "-" + jedis.get("age") + "-" +jedis.get("qq"));
		
	}

}
