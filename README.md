# Redis_javaAPI_learn_demo
**redis练习，使用 jedis API 实现对 redis 的操作**

主要执行代码在test里面，通过Junit跑起来方便，main中写了一些工具类<br>


## 项目结构：
**src/main/java**
- RedisConnectionPool：redis连接池工具类
- RedisSerializeUtil：序列化工具类

**src/test/java**
- **redis01.simple**：简单方法 和 key的常见操作
- **redis02.String**：String类型的常用方法
- **redis03.ObjectToString**：对象序列化系列
- **redis04.lock**：分布式锁案例
- **redis05.hash**：Hash类型的常用方法

## redis01.simple
简单方法 和 key的常见操作
## redis02.String（常用）
String类型的常用方法
## redis03.ObjectToString
对象序列化一般使用两种：json、Serializable

## redis04.lock（重要）
分布式锁案例使用多线程来对redis key标识进行争抢<br>
Junit是不支持多线程的，所以我这里用的是main方法<br>
我们用 **RedisDemo_Lock1** 和 **RedisDemo_Lock2** 模拟为两个分布式服务，将共同争抢一个锁<br>
- RedisLockUtil：是分布式锁的工具类，包含尝试**获取锁**和**释放锁**
- RedisDemo_Lock1：启动2个线程，线程间隔20秒尝试一次获取锁
- RedisDemo_Lock2：启动4个线程，线程间隔2秒尝试一获取锁

所有线程获取到锁后指定模拟工作时间统一为30秒<br>

模拟分布式多服务端多线程争抢同一线程锁的场景：<br>
**操作顺序**：先启动RedisDemo_Lock1，其中任何一个线程拿到锁后，再启动RedisDemo_Lock2<br>
**预期结果**：由控制台执行结果可得RedisDemo_Lock1中的任何一个线程执行完释放锁后，由于另一个线程此时正在20秒的等待期；而RedisDemo_Lock2的4个线程一直每2秒尝试获取一次，此时看到锁释放后，就会立刻争抢，从而获取到锁。


## redis05.hash（常用）
Hash类型的常用方法
