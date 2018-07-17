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
package org.mac.examples.concurrency.juc.utils.count_down_latch;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-06-13 23:47
 **/

public class ClassicUsage {




    public void case1() {
        final int [] data = new int[] {1,2,3,4,5,6,7,8,9,10};
        CountDownLatch latch = new CountDownLatch(data.length);
        ExecutorService executor = Executors.newFixedThreadPool(2);

        for (int i = 0; i < data.length; i++) {
            final int index = i;
            executor.execute(()->{
               data[index] = (data[index]%2 == 0)?data[index]*2:data[index]*30;
                latch.countDown();
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Arrays.toString(data));
        executor.shutdown();
    }

    public void case2() {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        CountDownLatch latch = new CountDownLatch(10);

        for (int i = 0 ; i < 10; i++) {
            new Thread(()->{
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }).start();
            latch.countDown();
        }
        executor.shutdown();

    }
}
