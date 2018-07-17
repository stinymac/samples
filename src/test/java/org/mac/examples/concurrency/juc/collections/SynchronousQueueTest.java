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

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-06-18 18:20
 **/

public class SynchronousQueueTest {

    public static void main(String[] args) throws InterruptedException {
        // A synchronous queue does not have any
        // internal capacity, not even a capacity of one.
        SynchronousQueue synchronousQueue = new SynchronousQueue();

        // synchronousQueue.put("B");//blocking

        new Thread(() -> {
            try {
                while (true) {
                    String v = (String) synchronousQueue.take();
                    if (v != null) {
                        System.out.println(v);
                        break;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        TimeUnit.SECONDS.sleep(1);
        //  java.lang.IllegalStateException: Queue full
        //  you cannot insert an element (using any method)
        //  unless another thread is trying to remove it
        synchronousQueue.add("A");
    }
}
