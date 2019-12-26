package com.demo.redis03.ObjectToString;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.demo.redis.util.RedisSerializeUtil;
import com.demo.redis.util.RedisConnectionPool;

import redis.clients.jedis.Jedis;

/**
 * 对象序列化字符串后进行存储
 * （不推荐）
 */
public class RedisDemo_ObjectToSerialize {

	private static Jedis jedis=null; 
	private final static String REDIS_KEY="USER2";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		jedis=RedisConnectionPool.getJedis();
	}

	@Test
	public void demo() {
		jedis.select(2);

		UserBean userBean=new UserBean();
		System.out.println("基础数据："+userBean.toString());
		System.out.println("==================================");

		//将pojo对象序列化为byte[]
		byte[] oByte=RedisSerializeUtil.serialize(userBean);
		//存储到Redis
		jedis.set(REDIS_KEY.getBytes(), oByte);

		//获取Redis数据
		byte[] bytes=jedis.get(REDIS_KEY.getBytes());
		//将数据反序列化为pojo对象
		UserBean obj=(UserBean)RedisSerializeUtil.unserialize(bytes);
		System.out.println("序列化还原的数据："+obj.toString());
	}

}
