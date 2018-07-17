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
package org.mac.examples.concurrency.juc.utils.phaser;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-06-17 16:48
 **/

public class PhaserTest {
    public static void main(String[] args) {
        final Phaser phaser = new Phaser();
        for (int i = 0; i < 5; i++) {
            new Task(phaser);
        }
        phaser.register();
        phaser.arriveAndAwaitAdvance();
        System.out.println("====end====");
    }

    static class Task extends Thread {

        private final Phaser phaser;

        public Task(Phaser phaser) {
            this.phaser = phaser;
            this.phaser.register();
            this.start();
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " running...");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            phaser.arriveAndAwaitAdvance();
        }
    }
}
