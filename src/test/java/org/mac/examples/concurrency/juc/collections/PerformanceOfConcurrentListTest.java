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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-06-19 23:55
 **/

public class PerformanceOfConcurrentListTest {
    public static void main(String[] args) {
        Collection c = new ConcurrentLinkedQueue();
        new PerformanceOfConcurrentList(c,5,5000,false,5).test();
        //new PerformanceOfConcurrentList(c,5,500000,true,5).test();

        new PerformanceOfConcurrentList(new CopyOnWriteArrayList<>(),5,5000,false,5).test();
        //new PerformanceOfConcurrentList(new CopyOnWriteArrayList<>(),5,500000,true,5).test();

        new PerformanceOfConcurrentList(Collections.synchronizedList(new ArrayList<>()),5,5000,false,5).test();
        //new PerformanceOfConcurrentList(Collections.synchronizedList(new ArrayList<>()),5,500000,true,5).test();
    }
}
