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
package org.mac.examples.concurrency.juc.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-06-11 21:42
 **/

public class AtomicIntegerFieldUpdaterTest {
    public static void main(String[] args) {
        Simple simple = new Simple();

        AtomicIntegerFieldUpdater<Simple> updater = AtomicIntegerFieldUpdater.newUpdater(Simple.class,"i");

        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                for (int j = 0; j < 20; j++) {
                   int v =  updater.getAndIncrement(simple);
                   System.out.println(Thread.currentThread().getName()+ "->" + v);
                }
            }).start();
        }

    }

    static class Simple {
        volatile int i;
    }
}
