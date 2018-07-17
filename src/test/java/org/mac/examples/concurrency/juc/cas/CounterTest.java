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
package org.mac.examples.concurrency.juc.cas;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-06-12 10:46
 **/

public class CounterTest {
    public static void main(String[] args) throws InterruptedException {

        /*Counter[] counters = new Counter[5];

        counters[0] = new Counter.IncorrectCounter();
        counters[1] = new Counter.CounterUseSynchronized();
        counters[2] = new Counter.CounterUseLock();
        counters[3] = new Counter.CounterUseAtomicLong();
        counters[4] = new Counter.CounterUseUnsafe();

        for (int i = 0; i < counters.length; i++) {
            final Counter counter = counters[i];
            new Thread(()->{
                long startTimeMillis = System.currentTimeMillis();
                for (int j=0; j < 1000000;j++) {
                    counter.increment();
                }
                long endTimeMillis = System.currentTimeMillis();
               System.out.printf("The counter [%25s] takes "+(endTimeMillis-startTimeMillis)+" ms under single thread and count result:"+counter.getCountResult()+"\n",counter.getClass().getSimpleName());
            }).start();
        }*/

        ExecutorService service = Executors.newFixedThreadPool(100);
        //final Counter counter = new Counter.IncorrectCounter();
        //final Counter counter = new Counter.CounterUseSynchronized();
        //final Counter counter = new Counter.CounterUseLock();
        //final Counter counter = new Counter.CounterUseAtomicLong();
        final Counter counter = new Counter.CounterUseUnsafe();
        CountDownLatch latch = new CountDownLatch(100);
        for (int j = 0; j < 100; j++) {
            service.submit(()->{
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for (int k=0; k < 1000000;k++) {
                    counter.increment();
                }
            });
            latch.countDown();
        }
        long startTimeMillis = System.currentTimeMillis();
        service.shutdown();
        service.awaitTermination(1, TimeUnit.HOURS);
        long endTimeMillis = System.currentTimeMillis();
        System.out.printf("The counter [%25s] takes "+(endTimeMillis-startTimeMillis)+" ms under multi thread and count result:"+counter.getCountResult()+"\n",counter.getClass().getSimpleName());
    }
}

