package org.spring.springboot.util;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
public class RedissonUtils{

    @Autowired
    private RedissonClient redisson;
    /**
     * 获取字符串对象
     * @param objectName
     * @return
     */
    public static <T> RBucket<T> getRBucket(RedissonClient redissonClient, String objectName){
        RBucket<T> bucket=redissonClient.getBucket(objectName);
        return bucket;
    }

    /**
     * 获取Map对象
     * @param objectName
     * @return
     */
    public static <K,V> RMap<K, V> getRMap(RedissonClient redissonClient, String objectName){
        RMap<K, V> map=redissonClient.getMap(objectName);
        return map;
    }

    /**
     * 获取有序集合
     * @param objectName
     * @return
     */
    public static <V> RSortedSet<V> getRSortedSet(RedissonClient redissonClient, String objectName){
        RSortedSet<V> sortedSet=redissonClient.getSortedSet(objectName);
        return sortedSet;
    }

    /**
     * 获取集合
     * @param objectName
     * @return
     */
    public static <V> RSet<V> getRSet(RedissonClient redissonClient, String objectName){
        RSet<V> rSet=redissonClient.getSet(objectName);
        return rSet;
    }

    /**
     * 获取列表
     * @param objectName
     * @return
     */
    public static <V> RList<V> getRList(RedissonClient redissonClient,String objectName){
        RList<V> rList=redissonClient.getList(objectName);
        return rList;
    }

    /**
     * 获取队列
     * @param objectName
     * @return
     */
    public static <V> RQueue<V> getRQueue(RedissonClient redissonClient,String objectName){
        RQueue<V> rQueue=redissonClient.getQueue(objectName);
        return rQueue;
    }

    /**
     * 获取双端队列
     * @param objectName
     * @return
     */
    public static <V> RDeque<V> getRDeque(RedissonClient redissonClient,String objectName){
        RDeque<V> rDeque=redissonClient.getDeque(objectName);
        return rDeque;
    }

    /**
     * 获取锁
     * @param objectName
     * @return
     */
    public static RLock getRLock(RedissonClient redissonClient,String objectName){
        RLock rLock=redissonClient.getLock(objectName);
        return rLock;
    }

    /**
     * 获取原子数
     * @param objectName
     * @return
     */
    public static RAtomicLong getRAtomicLong(RedissonClient redissonClient,String objectName){
        RAtomicLong rAtomicLong=redissonClient.getAtomicLong(objectName);
        return rAtomicLong;
    }

    /**
     * 获取记数锁
     * @param objectName
     * @return
     */
    public static RCountDownLatch getRCountDownLatch(RedissonClient redissonClient,String objectName){
        RCountDownLatch rCountDownLatch=redissonClient.getCountDownLatch(objectName);
        return rCountDownLatch;
    }

    /**
     * 获取消息的Topic
     * @param objectName
     * @return
     */
    public static <M> RTopic<M> getRTopic(RedissonClient redissonClient,String objectName){
        RTopic<M> rTopic=redissonClient.getTopic(objectName);
        return rTopic;
    }


}
