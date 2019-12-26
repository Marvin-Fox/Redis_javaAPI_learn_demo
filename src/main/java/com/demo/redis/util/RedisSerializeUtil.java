package com.demo.redis.util;

import java.io.*;

public class RedisSerializeUtil {
    /**
     * 序列化方法
     */
	public static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
        	// 序列化
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {
            System.err.println("序列化失败" + e.getMessage());
        	e.printStackTrace();
        }
        return null;
    }

    /**
     * 反序列化方法
     */
    public static Object unserialize( byte[] bytes) {
        ByteArrayInputStream bais = null;
        try {
        	// 反序列化
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(bais));
            Object obj = ois.readObject();
            return obj;
        } catch (Exception e) {
            System.err.println("反序列化失败" + e.getMessage());
        	e.printStackTrace();
        }
        return null;
    }


}
