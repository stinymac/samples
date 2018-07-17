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

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-06-16 21:01
 **/

public class ReentrantLockTest {

    private final static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
       // new Thread(()->useLock()).start();
       // new Thread(()->useLock()).start();

        /*Thread t1 = new Thread(()->useLockInterruptibly());
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        Thread t2 = new Thread(()->useLockInterruptibly());
        t2.start();
        TimeUnit.SECONDS.sleep(1);

        t2.interrupt();*/

        Thread t1 = new Thread(()->useTryLock());
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        Thread t2 = new Thread(()->useTryLock());
        t2.start();


    }

    public static void useLock() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName()+" running...");
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void useLockInterruptibly() {
        try {
            lock.lockInterruptibly();
            System.out.println(Thread.currentThread().getName()+" running...");
            while (true) {

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void useTryLock() {
        if (lock.tryLock()) {
            try {
                System.out.println(Thread.currentThread().getName()+" running...");
                while (true) {

                }
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("I ["+Thread.currentThread().getName()+"] can not get lock.");
        }
    }
}
