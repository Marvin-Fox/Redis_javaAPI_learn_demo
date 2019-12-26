package com.demo.redis05.hash;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import com.demo.redis.util.RedisConnectionPool;

import redis.clients.jedis.Jedis;


/**
 * hash比较好记，都是H开头的
 * @author Administrator
 *
 */
public class RedisDemo {

	private static Jedis jedis=null; 

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		jedis=RedisConnectionPool.getJedis();
		jedis.select(1);
	}

	
	/**
	 * hset :设置单个值
	 * hget :获取单个值
	 * 
	 * hmset :同时将多个 field-value (域-值)对设置到哈希表 key 中。
	 * hmget :获取所有给定字段的值
	 * 
	 * hgetall :获取在哈希表中指定 key 的所有 字段 和 值
	 * 
	 * hdel :删除一个或多个哈希表字段
	 */
	@Test
	public void demo() {
		String key="pojo";
		
		//添加数据
		jedis.hset(key, "name", "jerry");
		System.out.println("添加数据操作："+jedis.hget(key,"name"));
		
		
		//设置多个键值对
		Map<String, String> map=new HashMap<String, String>();
		map.put("age", "22");
		map.put("qq","1933108196");
		map.put("number","10");
		String executeResult=jedis.hmset(key,map);
		System.out.println("设置多个键值对操作："+("OK".equalsIgnoreCase(executeResult) ? "成功" : "失败"));
		
		
		//获取所有给定字段的值
		List<String> list=jedis.hmget(key, "name","age");
		StringBuffer sBuffer=new StringBuffer();
		for(String s:list){
			sBuffer.append(s+",");
		}
		System.out.println("获取Key下多个键值对："+sBuffer.toString());
		
		
		//获取在哈希表中指定 key 的所有 字段 和 值
		Map<String, String> mapResult=jedis.hgetAll(key);
		StringBuffer sBuffer2=new StringBuffer();
		for(Entry<String, String> entry : mapResult.entrySet()){
			sBuffer2.append(entry.getKey()+":"+entry.getValue()+"; ");
		}
		System.out.println("获取Key下所有键值对："+sBuffer2.toString());
		
				
		//删除数据
		jedis.hdel(key,"qq");
		System.out.println("删除数据操作："+jedis.hgetAll(key).toString());
	}
	
	/**
	 * hlen :获取哈希表中字段的数量
	 * 
	 * hkeys :获取所有哈希表中的字段(field)
	 * hvals :获取哈希表中所有值(value)
	 */
	@Test
	public void demo2() {
		
		String key="pojo";
		
		//获取哈希表中字段的数量
		System.out.println("获取哈希表中字段的数量："+jedis.hlen(key));
		
		//获取所有哈希表中的字段(field)
		System.out.println("获取所有哈希表中的字段(field)："+jedis.hkeys(key).toString());
		//获取哈希表中所有值(value)
		System.out.println("获取哈希表中所有值(value)："+jedis.hvals(key).toString());
	}
	
	
	/**
	 * hexists :查看哈希表 key 中，指定的字段是否存在
	 *
	 * hsetnx :只有在字段 field 不存在时，设置哈希表字段的值
	 * 
	 * hincrby :为哈希表 key 中的指定字段的整数值加上增量 increment 
	 * hincrbyfloat :为哈希表 key 中的指定字段的浮点数值加上增量 increment 
	 */
	@Test
	public void demo3() {
		
		String key="pojo";
		
		//查看哈希表 key 中，指定的字段是否存在
		System.out.println("查看哈希表 key 中，指定的字段是否存在："+jedis.hexists(key, "name"));
		
		//只有在字段 field 不存在时，设置哈希表字段的值
		System.out.println("只有在字段 field 不存在时，设置哈希表字段的值："+jedis.hsetnx(key,"name","sally"));
		
		//为哈希表 key 中的指定字段的整数值加上增量 increment 
		System.out.println("为哈希表 key 中的指定字段的整数值加上增量 increment："+jedis.hincrBy(key, "age", 1));
		
		//为哈希表 key 中的指定字段的浮点数值加上增量 increment 
		System.out.println("为哈希表 key 中的指定字段的浮点数值加上增量 increment ："+jedis.hincrByFloat(key, "number", 20.5));
	}
	
	

}
