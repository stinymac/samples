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

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-06-19 22:58
 **/

public class ConcurrentLinkedQueueTest {

    public static void main(String[] args) {

        ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();

        for (int i = 0; i < 100000; i++) {
            concurrentLinkedQueue.offer(System.nanoTime());
        }

        long startNanoTime = System.nanoTime();
        /*while (concurrentLinkedQueue.size() > 0) {
            concurrentLinkedQueue.poll();
        }*///18321 ms
        while (!concurrentLinkedQueue.isEmpty()) {
            concurrentLinkedQueue.poll();
        }//7 ms
        //@see #isEmpty(String str)
        System.out.println((System.nanoTime()-startNanoTime)/1000000+ " ms");

    }

    public static boolean isEmpty(String str) {
        //return null == str || "".equals(str);// a bad way
        return null == str || str.length() <= 0;
        //return null == str || str.isEmpty(); // same str.length() <= 0
    }
}
