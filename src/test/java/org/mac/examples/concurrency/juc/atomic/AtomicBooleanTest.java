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

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-06-11 16:23
 **/

public class AtomicBooleanTest {
    public static void main(String[] args) {
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        System.out.println(atomicBoolean);
        atomicBoolean = new AtomicBoolean(true);
        System.out.println(atomicBoolean);

        AtomicBoolean b = new AtomicBoolean(true);

        boolean rst = b.compareAndSet(false,true);
        System.out.println("rst:"+rst);
    }
}
