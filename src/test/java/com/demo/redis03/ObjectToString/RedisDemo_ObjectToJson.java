package com.demo.redis03.ObjectToString;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.demo.redis.util.RedisConnectionPool;

import redis.clients.jedis.Jedis;


/**
 * 对象转JSON字符串后进行存储
 * （推荐）
 */
public class RedisDemo_ObjectToJson {

	private static Jedis jedis=null; 
	private final static String REDIS_KEY="USER1";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		jedis=RedisConnectionPool.getJedis();
	}

	@Test
	public void demo() {
		jedis.select(2);
		
		//pojo 对象，初始化的时候已经进行了赋值
		UserBean userBean=new UserBean();
		System.out.println("基础数据："+userBean.toString());
		System.out.println("==================================");
		
		//将pojo对象转换为JSON
		String s=JSON.toJSONString(userBean);
		//存储到Redis
		jedis.set(REDIS_KEY, s);
				
		//获取Redis数据
		String gString=jedis.get(REDIS_KEY);
		//将数据转换为pojo对象
		UserBean uBean=JSON.parseObject(gString,UserBean.class);
		System.out.println("uBean:"+uBean);
		System.out.println("name:"+uBean.getName());
	}

}
