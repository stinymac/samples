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

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-06-11 15:43
 **/

public class CompareAndSetLock {

    private final AtomicInteger lock = new AtomicInteger(0);

    private Thread lockHolder;

    public void tryLock() throws TryLockFailException {
        boolean success = lock.compareAndSet(0,1);
        if (success) {
            lockHolder = Thread.currentThread();
        } else {
            throw new TryLockFailException(Thread.currentThread().getName()+" get lock fail.");
        }
    }

    public void unlock () {
        if (0 == lock.get()) {
            return;
        }
        if (Thread.currentThread() == lockHolder) {
            lock.compareAndSet(1,0);
        }
    }

    class TryLockFailException extends Exception {
        public TryLockFailException (String msg) {
            super(msg);
        }
    }
}
