package com.example;

import org.junit.jupiter.api.Test;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void commonTest() {
        redisTemplate.opsForValue().set("test_name", "111");
        System.out.println(redisTemplate.opsForValue().get("test_name"));
    }

    @Test
    public void redissonClientTest() {
        RLock lock = redissonClient.getLock("test_lock");
        try {
            if (lock.tryLock()) {
                RBucket<Object> bucket = redissonClient.getBucket("redisson_test_str");
                System.out.println(bucket.get());
                bucket.set("222");
                System.out.println(bucket.get());
            } else {
                System.out.println("lock failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
