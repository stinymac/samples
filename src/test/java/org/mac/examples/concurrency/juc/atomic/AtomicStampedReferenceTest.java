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

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-06-11 17:52
 **/

public class AtomicStampedReferenceTest {

    public static void main(String[] args) throws InterruptedException {
        AtomicStampedReference asr = new AtomicStampedReference(100,0);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                boolean success = asr.compareAndSet(100,101,asr.getStamp(),asr.getStamp()+1);
                System.out.println(success);
                success = asr.compareAndSet(101,100,asr.getStamp(),asr.getStamp()+1);
                System.out.println(success);
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int stamp = asr.getStamp();
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(asr.getReference());
                boolean success = asr.compareAndSet(100,105,stamp,stamp+1);
                System.out.println(Thread.currentThread().getName()+":"+ success);
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
