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
package org.mac.examples.concurrency.pattern.reading_writing_separation;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-05-24 22:02
 **/

public class ReadWriteSeparationTest {
    public static void main(String[] args) {
        char[] sharedData = new char[10];
        for (int i = 0; i < sharedData.length;i++) {
            sharedData[i] = '*';
        }
        final ReadWriteTask task = new ReadWriteTask(sharedData);

        new ReadWorker(task).start();
        new ReadWorker(task).start();
        new ReadWorker(task).start();
        new ReadWorker(task).start();

        new WriteWorker(task,"some").start();
        new WriteWorker(task,"data").start();
    }
}
