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
import java.util.concurrent.TimeUnit;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-06-16 12:48
 **/

public class ExchengerTest {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        new Thread(()->{
            try {
                String result = exchanger.exchange("from thread A");
                System.out.println(Thread.currentThread().getName()+" get return:" +result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
                String result = exchanger.exchange("from thread B");
                System.out.println(Thread.currentThread().getName()+" get return:"+result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();

        new Thread(()->{
            try {
                String result = exchanger.exchange("from thread C");
                System.out.println(Thread.currentThread().getName()+" get return:"+result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"C").start();
    }
}
