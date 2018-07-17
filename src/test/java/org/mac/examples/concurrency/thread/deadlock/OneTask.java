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

public class OneTask {

    private final  Object oneLock;

    private OtherTask otherTask;

    public OneTask(Object oneLock) {

        this.oneLock = oneLock;

    }

    public void setOtherTask(OtherTask otherTask) {
        this.otherTask = otherTask;
    }

    public void one(){
        synchronized (oneLock) {

            otherTask.m();
            System.out.println(Thread.currentThread()+" hold two lock. ");
        }
    }

    public void m() {
        synchronized (oneLock) {
            //System.out.println("->");
        }
    }
}
