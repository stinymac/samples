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
package org.mac.examples.concurrency.volatile_key_word;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-05-22 12:59
 **/

public class VolatileTest {
    //private static volatile int shareValue = 0;
    private static  int shareValue = 0;
    private static final int LIMIT = 5;

    public static void main(String[] args) {

        new Thread(()->{
            int localValue = shareValue;
            while (localValue < LIMIT) {
                if (localValue != shareValue) {
                    System.out.printf("The vale have be updated to [%d]\n",shareValue);
                    localValue = shareValue;
                }
            }
        },"READER").start();

        new Thread(()->{
            int localValue = shareValue;
            while (localValue < LIMIT) {
                System.out.printf("update vale  to [%d]\n",++localValue);
                shareValue = localValue;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("shareValue:"+shareValue);
        },"WRITER").start();
    }
}
