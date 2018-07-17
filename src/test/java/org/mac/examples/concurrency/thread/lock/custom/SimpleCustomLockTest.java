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
package org.mac.examples.concurrency.thread.lock.custom;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-05-09 22:15
 **/

public class SimpleCustomLockTest {
    public static void main(String[] args) throws InterruptedException, Lock.IllegalOperationException {
        final SimpleCustomLock lock = new SimpleCustomLock();
        for (int i = 0; i < 5;i++) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        lock.lock();
                        System.out.println(Thread.currentThread().getName() + " get lock.");
                        Thread.sleep(8000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            lock.unlock();
                            System.out.println(Thread.currentThread().getName() + " release lock.");
                        } catch (Lock.IllegalOperationException e) {
                            e.printStackTrace();
                        }
                    }
                }
            },"T"+(i+1)).start();
        }
        Thread.sleep(100);
        try {
            System.out.println("-----");
            lock.lock(500);
            System.out.println(Thread.currentThread().getName() + " get lock.");
        } catch (Lock.TimeoutException e) {
            System.out.println(Thread.currentThread().getName() + ":"+ e.getMessage());
        } finally {
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " release lock.");
        }
    }
}
