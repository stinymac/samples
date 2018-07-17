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
package org.mac.examples.concurrency.thread.interrupt;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-05-08 21:11
 **/

public class ThreadInterrruptTest {
    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(100);
                        //System.out.println("running...");
                    }
                }catch(InterruptedException e) {
                    System.out.println(Thread.currentThread().getName()+" isInterrupted :"+Thread.currentThread().isInterrupted()+"->"+ e);
                }
            }
        },"t");
        t.start();
        System.out.println(t.isInterrupted());
        t.interrupt();
        System.out.println(t.isInterrupted());
    }
}
