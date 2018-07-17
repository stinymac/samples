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

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-06-16 22:54
 **/

public class ConditionOfLock {

    private final static ReentrantLock LOCk = new ReentrantLock();
    private final static Condition P_CONDITION = LOCk.newCondition();
    private final static Condition C_CONDITION = LOCk.newCondition();

    private final static LinkedList<Long> TIMESTAMP_POOL = new LinkedList<>();

    private static final int MAX_CAPACITY =100;

    public void produce () {
        try {
            LOCk.lock();
            while (TIMESTAMP_POOL.size() > MAX_CAPACITY) {
                P_CONDITION.await();
            }
            long v = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName()+" produce =>"+v);
            TIMESTAMP_POOL.addLast(v);
            C_CONDITION.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            LOCk.unlock();
        }
    }

    public void consume () {
        try {
            LOCk.lock();
            while (TIMESTAMP_POOL.isEmpty()) {
                C_CONDITION.await();
            }
            long v = TIMESTAMP_POOL.getFirst();
            System.out.println(Thread.currentThread().getName()+" consume =>"+v);
            P_CONDITION.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            LOCk.unlock();
        }
    }
}
