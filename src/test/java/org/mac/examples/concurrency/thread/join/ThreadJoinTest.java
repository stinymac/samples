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
package org.mac.examples.concurrency.thread.join;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-05-08 20:45
 **/

public class ThreadJoinTest {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            public void run() {
                for(int i = 0 ;i < 10; i ++) {
                    System.out.println(Thread.currentThread()+" run-> " +(i+1));
                }
            }
        });
        t.start();
        t.join();
        System.out.println("---------------------------------------");
        for(int i = 0 ;i < 10; i ++) {
            System.out.println(Thread.currentThread()+" run-> " +(i+1));
        }
    }
}
