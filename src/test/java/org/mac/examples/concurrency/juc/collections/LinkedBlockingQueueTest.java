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

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-06-18 17:34
 **/

public class LinkedBlockingQueueTest {
    // bounds can choice //Integer.MAX_VALUE
    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>();
        linkedBlockingQueue.add("A");

        System.out.println(linkedBlockingQueue.take());
        System.out.println(linkedBlockingQueue.take());//blocking
    }



}
