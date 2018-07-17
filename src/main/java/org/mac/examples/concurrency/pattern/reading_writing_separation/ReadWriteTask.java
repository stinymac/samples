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
 * @create 2018-05-24 21:36
 **/

public class ReadWriteTask {

    private final char[] sharedData;
    private final ReadWriteLock lock = new ReadWriteLock();

    public ReadWriteTask(char[] sharedData) {
        this.sharedData = sharedData;
    }

    public char[] read() {
        try {
            lock.readLock();
            return doRead();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                lock.readUnLock();
            } catch (ReadWriteLock.IllegalOperationException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private char[] doRead() {
        char[] copy = new char[sharedData.length];
        for (int i = 0; i < sharedData.length; i++)
            copy[i] = sharedData[i];

        slowly(50);
        System.out.println(Thread.currentThread().getName() + " reads " + String.valueOf(copy));
        return copy;
    }

    public void write (char c) {
        try {

            lock.writeLock();
            doWrite(c);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {

                lock.writeUnLock();
            } catch (ReadWriteLock.IllegalOperationException e) {
                e.printStackTrace();
            }
        }
    }

    private void doWrite(char c) {
        for (int i = 0; i < sharedData.length; i++) {
            sharedData[i] = c;
            slowly(10);
        }
    }

    private void slowly(int mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
        }
    }
}
