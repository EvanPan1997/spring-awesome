package com.example;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {

    @Resource
    private RedisTemplate redisTemplate;

    @Test
    public void testString() {
        String test = (String) redisTemplate.opsForValue().get("test");
        System.out.println(test);
    }

    @Test
    public void testSum() {
        int[] ints = {1, 2};
        for (int anInt : ints) {
            System.out.println(anInt);
        }
        System.out.println(ints.length);
    }

    @Test
    public void testHash() {

    }

    @Test
    public void testList() {

    }

    @Test
    public void testSet() {

    }

    @Test
    public void testSortedSet() {

    }

    @Test
    public void testMidNum() {
        System.out.println(findMedianSortedArrays(new int[]{1,2}, new int[]{3,4}));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int i1=0, i2=0, i=0;
        int[] nums = new int[nums1.length+nums2.length];
        while(i<nums1.length+nums2.length) {
            if (i1 == nums1.length) {
                nums[i++] = nums2[i2++];
                continue;
            }
            if (i2 == nums2.length) {
                nums[i++] = nums1[i1++];
                continue;
            }

            if (nums1[i1]<nums2[i2]) {
                nums[i++] = nums1[i1++];
            } else {
                nums[i++] = nums2[i2++];
            }
        }
        if (i%2==0) {
            return (nums[i/2-1]+nums[i/2])/2.0;
        } else {
            return nums[i/2];
        }
    }
}
