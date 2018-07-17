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
package org.mac.examples.concurrency.pattern.active_object;

import java.util.LinkedList;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-06-08 15:14
 **/

public class ActivationQueue {
    private final static int MAX_METHOD_OBJECT_QUEUE_SIZE = 100;

    private final LinkedList<MethodObject> methodQueue;

    public ActivationQueue() {
        methodQueue = new LinkedList<>();
    }

    public synchronized void put(MethodObject methodObject) {
        while (methodQueue.size() >= MAX_METHOD_OBJECT_QUEUE_SIZE) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.methodQueue.addLast(methodObject);
        this.notifyAll();
    }

    public synchronized MethodObject take() {
        while (methodQueue.isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        MethodObject methodObject = methodQueue.removeFirst();
        this.notifyAll();
        return methodObject;
    }
}
