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
 * @create 2018-05-09 14:22
 **/

public class OtherTask {


    private final  Object otherLock;

    private final OneTask oneTask;

    public OtherTask(OneTask oneTask,Object otherLock) {
        this.oneTask = oneTask;

        this.otherLock = otherLock;
    }

    public void other(){
        synchronized (otherLock) {

            oneTask.m();
            System.out.println(Thread.currentThread()+" hold two lock. ");
        }
    }

    public void m() {
        synchronized (otherLock) {
            System.out.println("->");
        }
    }
}
