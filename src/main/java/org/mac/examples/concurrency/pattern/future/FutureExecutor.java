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
package org.mac.examples.concurrency.pattern.future;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-05-25 20:44
 **/

public class FutureExecutor {
    public<T> Future<T> execute (FutureRunnable<T> task) {
        AsynFuture<T> aysnFuture = new AsynFuture<T>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                T result = task.call();
                aysnFuture.set(result);
            }
        }).start();
        return aysnFuture;
    }

    public<T> void execute (FutureRunnable<T> task,Callback<T> call) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                T result = task.call();
                call.accept(result);
            }
        }).start();

    }
}
