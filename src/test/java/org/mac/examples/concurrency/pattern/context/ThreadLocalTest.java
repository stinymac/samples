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
package org.mac.examples.concurrency.pattern.context;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-06-22 9:40
 **/

public class ThreadLocalTest {

    public static void main(String[] args) {
        ThreadLocal tl = new ThreadLocal();
        /*tl.set(1);
        tl.set(2);
        System.out.println(tl.get());*/
        Object obj = new Object();
        System.out.println(obj);
        new Thread(()->{
            tl.set(obj);
            System.out.println(tl.get());
        }).start();
        new Thread(()->{
            tl.set(obj);
            System.out.println(tl.get());
        }).start();
    }
}
