package com.demo.redis04.lock;

import java.util.Collections;

import redis.clients.jedis.Jedis;


/**
 * 分布式锁的工具类
 */
public class RedisLockUtil {
	private static final String LOCK_SUCCESS = "OK";
	
	//只在KEY不存在时,才对KEY进行设置操作
    private static final String SET_IF_NOT_EXIST = "NX";
    //只在KEY已经存在时,才对KEY进行设置操作（分布式锁不需要XX）
//    private static final String SET_IF_ALREADY_EXIST = "XX";
    //设置键的过期时间为second秒
    private static final String SET_WITH_EXPIRE_TIME = "EX";
    //设置键的过期时间为millisecond毫秒
    private static final String SET_WITH_P_EXPIRE_TIME = "PX";
    
    /**
     * 尝试获取分布式锁
     * @param jedis Redis客户端
     * @param lockKey 锁
     * @param value 请求标识
     * @param expireTime 超期时间
     * @return 是否获取成功
     */
    public static synchronized boolean tryGetDistributedLock(Jedis jedis, String lockKey, String value, int expireTime) {
    	
    	System.out.println(value+"-----tryGetDistributedLock-----lockKey:"+lockKey);

    	//设置锁机制为key不存在才添加：NX，自动超时单位为秒：EX
    	String result = jedis.set(lockKey, value, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
    	
    	//Key不存在，添加成功
        if (LOCK_SUCCESS.equals(result)) {
        	System.out.println(value+"-----GetDistributedLock-----success-----lockKey:"+lockKey);
            return true;
        }
        
        return false;
    }
//    public static boolean tryGetDistributedLock(Jedis jedis, String lockKey, String value, int expireTime) {
//    	
//    	synchronized (RedisLockUtil.class) {
//    		System.out.println(value+"-----tryGetDistributedLock-----lockKey:"+lockKey);
//        	
//        	//设置锁机制为key不存在才添加：NX，自动超时单位为秒：EX
//        	String result = jedis.set(lockKey, value, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
//        	
//        	//Key不存在，添加成功
//            if (LOCK_SUCCESS.equals(result)) {
//            	System.out.println(value+"-----GetDistributedLock-----lockKey:"+lockKey+"-----success");
//                return true;
//            }
//            return false;
//		}
//    }
//    
    
    private static final Long RELEASE_SUCCESS = 1L;
    /**
     * 释放分布式锁
     * @param jedis Redis客户端
     * @param lockKey 锁
     * @param requestId 请求标识
     * @return 是否释放成功
     */
    public static boolean releaseDistributedLock(Jedis jedis, String lockKey, String requestId) {
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
        if (RELEASE_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }
    

}
