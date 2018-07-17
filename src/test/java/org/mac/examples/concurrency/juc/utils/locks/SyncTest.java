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

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-06-16 22:28
 **/

public class SyncTest {
    public static void main(String[] args) throws InterruptedException {
        new Thread(()->w()).start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(()->r()).start();
    }

    public synchronized static void w() {
        System.out.println(Thread.currentThread().getName()+" w()");
        for(;;){

        }
    }

    public synchronized static void r() {
        System.out.println(Thread.currentThread().getName()+" r()");
    }
}
