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

import java.util.Random;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-05-24 22:17
 **/

public class WriteWorker extends Thread{
    private static final Random random = new Random(System.currentTimeMillis());
    private final ReadWriteTask readWriteTask;
    private final String filler;
    private int index = 0;

    public WriteWorker(ReadWriteTask readWriteTask, String filler) {
        this.readWriteTask = readWriteTask;
        this.filler = filler;
    }

    @Override
    public void run() {
        try {
            while (true) {

                char c = nextChar();
                readWriteTask.write(c);
                Thread.sleep(random.nextInt(1000));

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private char nextChar() {
        char c = filler.charAt(index);
        index++;
        if (index >= filler.length())
            index = 0;
        return c;
    }
}
