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

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-06-18 23:02
 **/

public class LinkedBlockingDequeTest {

    public static void main(String[] args) throws InterruptedException {

        LinkedBlockingDeque<String> linkedBlockingDequeue = new LinkedBlockingDeque();
        linkedBlockingDequeue.addFirst("A");
        linkedBlockingDequeue.addFirst("D");
        linkedBlockingDequeue.addLast("C");
        linkedBlockingDequeue.addFirst("B");

        Iterator<String> it = linkedBlockingDequeue.iterator();

        while (it.hasNext()) {
            System.out.println(it.next());
        }

        System.out.println(linkedBlockingDequeue.take());
        System.out.println(linkedBlockingDequeue.take());
        System.out.println(linkedBlockingDequeue.take());
        System.out.println(linkedBlockingDequeue.take());
        //System.out.println(linkedBlockingDequeue.take());// blocking

        linkedBlockingDequeue.add("E");//==addLast();
    }
}
