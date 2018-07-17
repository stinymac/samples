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
package org.mac.examples.concurrency.thread.constructor;

import java.util.concurrent.TimeUnit;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-05-06 18:19
 **/

public class ThreadGroupTest {
    public static void main(String[] args) {
        Thread t = new Thread(){
            @Override
            public void run(){
                try {
                    TimeUnit.SECONDS.sleep(10L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
        ThreadGroup threadGroup = t.getThreadGroup();
        System.out.println(threadGroup);

        Thread[] threads = new Thread[threadGroup.activeCount()];
        threadGroup.enumerate(threads);
        for (Thread thread:threads){
            System.out.println(thread+" is daemon:"+thread.isDaemon());
        }
    }
}
