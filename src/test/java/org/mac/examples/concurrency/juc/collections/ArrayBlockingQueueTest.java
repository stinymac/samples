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

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-06-18 16:43
 **/

public class ArrayBlockingQueueTest {
    public static void main(String[] args) throws InterruptedException {
        //
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(5);

        /*queue.add("A");
        queue.add("B");
        queue.add("C");
        queue.add("D");
        queue.add("E");
        System.out.println("size:5");
        queue.add("F");//java.lang.IllegalStateException: Queue full
        System.out.println("size:6");*/


        /*queue.offer("A");
        queue.offer("B");
        queue.offer("C");
        queue.offer("D");
        queue.offer("E");
        System.out.println("size:5");
        queue.offer("F");//return false*/


       /* queue.put("A");
        queue.put("B");
        queue.put("C");
        queue.put("D");
        queue.put("E");
        System.out.println("size:5");
        queue.put("F");//blocking
        System.out.println("size:6");*/

        queue.put("A");
        queue.put("B");

        System.out.println(queue.poll());//A
        System.out.println(queue.poll());//B
        System.out.println(queue.poll());//null

        queue.put("A");
        queue.put("B");
        System.out.println(queue.peek());//A
        System.out.println(queue.peek());//A
        System.out.println(queue.peek());//A

        System.out.println(queue.take());//A
        System.out.println(queue.take());//B
        System.out.println(queue.take());//blocking

    }
}
