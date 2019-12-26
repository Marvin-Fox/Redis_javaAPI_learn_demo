package com.demo.redis02.String;

import org.junit.BeforeClass;
import org.junit.Test;
import com.demo.redis.util.RedisConnectionPool;
import redis.clients.jedis.Jedis;

/**
 * String类型的常用方法
 */
public class RedisDemo {
	
	private static Jedis jedis=null; 

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		jedis=RedisConnectionPool.getJedis();
	}
	
	/**
	 * get :获取
	 * set :添加
	 * 
	 * mset :同时设置一个或多个 key-value 对
	 * mget :获取所有(一个或多个)给定 key 的值
	 * 
	 * getset :将给定 key 的值设为 value ，并返回 key 的旧值(old value)
	 * 
	 * del :删除
	 */
	@Test
	public void demo1(){
		
		//添加数据
		jedis.set("name", "jerry");
		System.out.println("添加数据操作："+jedis.get("name"));

		//设置多个键值对
		jedis.mset("name","yc","age","22","qq","1933108196");
		jedis.incr("age");//加1操作
		System.out.println("设置多个键值对操作："+"name="+jedis.get("name") + ";" +"age="+ jedis.get("age") + ";" +"qq="+jedis.get("qq"));
		System.out.println("获取所有(一个或多个)给定 key 的值："+jedis.mget("name","age","qq")); 
		
		//修改key对应的value，并且返回修改前的value
		jedis.set("name", "sally");
		String oldValue=jedis.getSet("name", "max");
		System.out.println("返回更新前的值为："+oldValue);
		
		//删除数据
		jedis.del("name");
		System.out.println("删除数据操作："+jedis.get("name"));
	}
	
	/**
	 * append :拼接
	 * strlen :返回键值的长度
	 * 
	 * getrange :获取指定范围内的值
	 * setrange :从索引位置开始设置后面的值
	 * 
	 * incr dect :递增和递减整数值，增减1
	 * incrby、dcrby :递增和递减整数值，可指定增减的数值
	 */
	@Test
	public void demo2(){
		
		jedis.set("name", "jerry");
		//拼接字符串
		jedis.append("name", ".com");
		System.out.println("拼接字符串操作："+jedis.get("name"));
		//返回键值的长度
		System.out.println("返回键值的长度："+jedis.strlen("name"));
		
		
		//获取指定范围内的值
		System.out.println("获取指定范围内的值："+jedis.getrange("name", 0, jedis.strlen("name")));
		//从索引位置开始设置后面的值
		jedis.setrange("name", 2, "aaaa");
		System.out.println("从索引位置开始设置后面的值："+jedis.get("name"));
		
		
		jedis.set("StringNum", "10");
		//+1操作
		jedis.incr("StringNum");
		System.out.println("字符串+1操作："+jedis.get("StringNum"));
		//-1操作
		jedis.decr("StringNum");
		System.out.println("字符串-1操作："+jedis.get("StringNum"));
		//增加指定数值操作
		jedis.incrBy("StringNum", 10);
		System.out.println("字符串增加指定数值操作："+jedis.get("StringNum"));
		//减少指定数值操作
		jedis.decrBy("StringNum", 10);
		System.out.println("字符串减少指定数值操作："+jedis.get("StringNum"));
	}
	
	
	/**
	 * setex(set with expore) :将值 value 关联到 key ，并将 key 的过期时间设为 seconds (以秒为单位)
	 * 
	 * setnx(set if not exist) :只有在 key 不存在时设置 key 的值才会生效，key存在将不会存储或替换KEY值
	 * msetnx :同时设置一个或多个 key-value 对，当且仅当所有给定 key 都不存在时才能存储成功，否则全部存不进去
	 */
	@Test
	public void demo3()throws Exception{
		
		//setex(key,时间单位是秒,value)：设置过期时间，到期后自动过期
		jedis.setex("time",60, "生存时间");
		System.out.println("剩余生存时间为："+jedis.ttl("time")+"；value："+jedis.get("time"));
		Thread.sleep(2000);
		System.out.println("剩余生存时间为："+jedis.ttl("time")+"；value："+jedis.get("time"));
		
		
		//setnx ：只有在 key 不存在时设置 key 的值才会生效，key存在将不会存储或替换KEY值
		Long result=jedis.setnx("token","111");
		System.out.println("是否写入成功（写入成功返回1失败返回0）："+result);
		//msetnx ：同时设置一个或多个 key-value 对，当且仅当所有给定 key 都不存在时才能存储成功，否则全部存不进去
		Long result2=jedis.msetnx("token1","111","token2","222");
		System.out.println("是否写入成功（全部都能写入才返回1）："+result2);
		
	}
}
