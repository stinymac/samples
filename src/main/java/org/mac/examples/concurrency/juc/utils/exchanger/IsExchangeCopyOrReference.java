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
package org.mac.examples.concurrency.juc.utils.exchanger;

import java.util.concurrent.Exchanger;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-06-16 13:11
 **/

public class IsExchangeCopyOrReference {

    public void test() {
        Exchanger<Object> exchanger =new Exchanger<>();

        new Thread(()->{
            try {
                Object a = new Object();
                System.out.println("Thread A send Object a:"+a);
                Object r = exchanger.exchange(a);
                System.out.println("Thread "+Thread.currentThread().getName()+" receive:" +r);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();

        new Thread(()->{
            try {
                Object b = new Object();
                System.out.println("Thread B send Object b:"+b);
                Object r = exchanger.exchange(b);
                System.out.println("Thread "+Thread.currentThread().getName()+" receive:"+r);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();
    }
}
