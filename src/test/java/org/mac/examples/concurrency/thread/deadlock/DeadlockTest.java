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
package org.mac.examples.concurrency.thread.deadlock;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-05-09 14:37
 **/

public class DeadlockTest {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                //do some thing (file、 connection、network )
            }
        }));
        Object lockOne = new Object();
        Object lockOther = new Object();

        final OneTask oneTask = new OneTask(lockOne);
        final OtherTask otherTask = new OtherTask(oneTask,lockOther);
        oneTask.setOtherTask(otherTask);

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    oneTask.one();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    otherTask.other();
                }
            }
        });
        t1.start();
        t2.start();
    }
}
