/*
 * Copyright 2018-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.mac.examples.concurrency.juc.collections;

import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-06-19 23:49
 **/

public class PerformanceOfConcurrentList {

    private final Collection<Integer> list;
    private final int nThreads;
    private final long dataSize;
    private final boolean isConcurrentRead;
    private final int testTimes;

    public PerformanceOfConcurrentList(Collection<Integer> list, int nThreads, long dataSize, boolean isConcurrentRead, int testTimes) {
        this.list = list;
        this.nThreads = nThreads;
        this.dataSize = dataSize;
        this.isConcurrentRead = isConcurrentRead;
        this.testTimes = testTimes;
    }

    public void test() {
        String nameOfList = this.list.getClass().getName();
        System.out.println("Test List ["+nameOfList+"] with ["+nThreads + "] Thread and data size is ["+dataSize+"] and do test ["+testTimes+"] times and isConcurrentRead ["+isConcurrentRead+"]");
        long totalTime = 0;
        for (int i = 0; i < testTimes; i++) {
            ExecutorService executor = Executors.newFixedThreadPool(nThreads);
            long startNanoTime = System.nanoTime();
            for (int j = 0 ; j < nThreads;j++) {
                executor.execute(()->{
                    for (int k = 0; k < dataSize; k++) {
                        int randomNum = (int) Math.ceil(Math.random() * 1000000);
                        //String key = String.valueOf(randomNum);
                        list.add(randomNum);
                        //if (isConcurrentRead)
                            //list.remove((int) Math.ceil(Math.random() * 1000000));
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
