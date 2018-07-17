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

import java.util.*;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-05-09 21:14
 **/

public class SimpleCustomLock implements Lock{

    private volatile boolean lockIsOccupied = false;
    private Collection<Thread> waitSet = new ArrayList<Thread>();
    private Thread lockHolder;

    public synchronized void lock() throws InterruptedException {
        Thread currentThread = Thread.currentThread();
        while (lockIsOccupied) {
            waitSet.add(currentThread);
            this.wait();
        }
        lockIsOccupied = true;
        lockHolder = currentThread;
        waitSet.remove(currentThread);
    }

    public synchronized void unlock() throws IllegalOperationException {
        Thread currentThread = Thread.currentThread();
        if(currentThread != lockHolder) {
            throw new IllegalOperationException("The lock only be released by itself.");
        }
        lockIsOccupied = false;
        this.notifyAll();
    }

    public synchronized void lock(long millis) throws TimeoutException, InterruptedException {
        if (millis <=0) {
            lock();
        }

        long beginTimeMillis = System.currentTimeMillis();
        long remainingMillis = millis;
        long endTimeMillis = beginTimeMillis + millis;

        Thread currentThread = Thread.currentThread();

        while (lockIsOccupied) {
            if (remainingMillis <= 0) {
                waitSet.remove(currentThread);
                throw new TimeoutException("get lock time out.");
            }
            waitSet.add(currentThread);
            this.wait(millis);

            remainingMillis = endTimeMillis - System.currentTimeMillis();
            //System.out.println("remainingMillis:"+remainingMillis);
        }
        lockIsOccupied = true;
        lockHolder = currentThread;
        waitSet.remove(currentThread);
    }

    public Collection<Thread> getBlockedThreads() {
        return Collections.unmodifiableCollection(waitSet);
    }

    public int getBlockedThreadNum(){
        return waitSet.size();
    }
}
