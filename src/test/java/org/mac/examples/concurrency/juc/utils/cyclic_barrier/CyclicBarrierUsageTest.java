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
package org.mac.examples.concurrency.juc.utils.cyclic_barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-06-16 12:25
 **/

public class CyclicBarrierUsageTest {
    static class CyclicBarrierUsage{
        public void demo () {
            ExecutorService executor = Executors.newFixedThreadPool(10);

            CyclicBarrier barrier = new CyclicBarrier(10);
            // 9--一直等待
            for (int i = 0 ; i < 9; i++) {
                new Thread(()->{
                    try {
                        barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName());
                }).start();
            }
            executor.shutdown();

        }

    }

    public void demo2 (){
        CyclicBarrier barrier = new CyclicBarrier(10,()->{
            System.out.println("call back");
        });

    }

    public static void main(String[] args) {
        CyclicBarrierUsage usage = new CyclicBarrierUsage();

        usage.demo();
    }
}
