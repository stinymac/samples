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

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-06-18 23:33
 **/

public class LinkedTransferQueueTest {

    public static void main(String[] args) throws InterruptedException {

        // make sure data consumed

        LinkedTransferQueue<String> linkedTransferQueue = new LinkedTransferQueue<>();

        System.out.println(linkedTransferQueue.add("X"));//true
        System.out.println(linkedTransferQueue.size());//1
        System.out.println("=============");

        linkedTransferQueue.clear();

        System.out.println(linkedTransferQueue.tryTransfer("A"));//false

        System.out.println(linkedTransferQueue.size());//0

        System.out.println(linkedTransferQueue.peek());//null


        new Thread(()->{
            try {
                linkedTransferQueue.transfer("C");//blocking
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        TimeUnit.MILLISECONDS.sleep(100);

        new Thread(()->{
            while (true) {
                try {
                    System.out.println("take:"+linkedTransferQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        TimeUnit.MILLISECONDS.sleep(100);
        linkedTransferQueue.add("B");
        System.out.println(linkedTransferQueue.size());

    }
}
