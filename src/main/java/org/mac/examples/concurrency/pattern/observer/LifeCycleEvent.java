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
package org.mac.examples.concurrency.pattern.observer;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-05-23 16:41
 **/

public class LifeCycleEvent {
    private Status status;
    private Thread thread;
    private Throwable cause;

    public LifeCycleEvent(Status status, Thread thread, Throwable cause) {
        this.status = status;
        this.thread = thread;
        this.cause = cause;
    }

    public Status getStatus() {
        return status;
    }

    public Thread getThread() {
        return thread;
    }

    public Throwable getCause() {
        return cause;
    }

}
