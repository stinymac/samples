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

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-06-18 17:19
 **/

public class PriorityBlockingQueueTest {

    public static void main(String[] args) throws InterruptedException {
        //public PriorityBlockingQueue(int initialCapacity,Comparator<? super E> comparator)
        // must implements Comparable or offer a comparator
        PriorityBlockingQueue<String> priorityBlockingQueue = new PriorityBlockingQueue<>();
        // add() == offer() == put()
        priorityBlockingQueue.add("B");
        priorityBlockingQueue.add("C");
        priorityBlockingQueue.add("A");
        priorityBlockingQueue.add("D");
        priorityBlockingQueue.add("D");

        System.out.println(priorityBlockingQueue.peek());
        System.out.println(priorityBlockingQueue.size());//5
        System.out.println(priorityBlockingQueue.element());
        System.out.println(priorityBlockingQueue.size());//5

        System.out.println(priorityBlockingQueue.poll());//A
        System.out.println(priorityBlockingQueue.poll());//B
        System.out.println(priorityBlockingQueue.poll());//C
        System.out.println(priorityBlockingQueue.poll());//D
        System.out.println(priorityBlockingQueue.poll());//D
        System.out.println(priorityBlockingQueue.size());//0

        System.out.println(priorityBlockingQueue.take());//blocking
    }
}
