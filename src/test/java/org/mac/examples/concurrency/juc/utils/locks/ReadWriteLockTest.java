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
package org.mac.examples.concurrency.juc.utils.locks;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-06-16 21:37
 **/

public class ReadWriteLockTest {

    private static final ReadWriteLock RWLOCk = new ReentrantReadWriteLock();
    private static final Lock RLOCK = RWLOCk.readLock();
    private static final Lock WLOCK = RWLOCk.writeLock();

    private static final List<Long> data = new ArrayList<>();

    public static void main(String[] args) {
        new Thread(()->write()).start();
        new Thread(()->read()).start();

        new Thread(()->write()).start();
        new Thread(()->write()).start();

        new Thread(()->read()).start();
        new Thread(()->read()).start();
    }

    public static void write() {
        try {
            WLOCK.lock();
            long v = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName()+" write:"+v);
            TimeUnit.SECONDS.sleep(1);
            data.add(v);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            WLOCK.unlock();
        }
    }

    public static long read() {
        try {
            RLOCK.lock();
            long t = data.get(0);
            System.out.println(Thread.currentThread().getName()+" read:"+t);
            return t;
        }finally {
            RLOCK.unlock();
        }
    }
}
