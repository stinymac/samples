/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.examples.concurrency.juc.collections;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Mac
 * @create 2018-06-19 12:57
 **/

public class PerformanceOfMap {

    private final Map<String,Integer> map;
    private final int nThreads;
    private final long dataSize;
    private final boolean isConcurrentRead;
    private final int testTimes;

    public PerformanceOfMap(Map<String,Integer> map, int nThreads, long dataSize,int testTimes, boolean isConcurrentRead) {
        this.map = map;
        this.nThreads = nThreads;
        this.dataSize = dataSize;
        this.testTimes = testTimes;
        this.isConcurrentRead = isConcurrentRead;
    }

    public void test() {
        String nameOfMap = this.map.getClass().getName();
        System.out.println("Test Map ["+nameOfMap+"] with ["+nThreads + "] Thread and data size is ["+dataSize+"] and do test ["+testTimes+"] times");
        long totalTime = 0;
        for (int i = 0; i < testTimes; i++) {
            ExecutorService executor = Executors.newFixedThreadPool(nThreads);
            long startNanoTime = System.nanoTime();
            for (int j = 0 ; j < nThreads;j++) {
                executor.execute(()->{
                    for (int k = 0; k < dataSize; k++) {
                        int randomNum = (int) Math.ceil(Math.random() * 1000000);
                        String key = String.valueOf(randomNum);
                        map.put(key,randomNum);
                        if (isConcurrentRead)
                            map.get(key);
                    }
                });
            }
            executor.shutdown();
            try {
                executor.awaitTermination(2, TimeUnit.HOURS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long endNanoTime = System.nanoTime();
            long millisTime = (endNanoTime - startNanoTime )/1000000;
            System.out.println("insert or get "+nThreads * dataSize +" data  take [" +millisTime+"] ms");
            totalTime+=millisTime;
        }
        System.out.println("total time is ["+totalTime+"] ms and average take ["+ (totalTime/5) +"] ms");
    }
}
