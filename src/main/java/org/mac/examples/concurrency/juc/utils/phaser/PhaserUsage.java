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

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-06-17 16:34
 **/

public class PhaserUsage extends Thread{

        private final int no;
        private final Phaser phaser;

        private static final Random RANDOM = new Random(System.currentTimeMillis());

        public PhaserUsage(int no, Phaser phaser) {
            this.no = no;
            this.phaser = phaser;
            this.start();
        }

        @Override
        public void run() {
            try {
                System.out.println("No." + no + " start running...");
                TimeUnit.SECONDS.sleep(RANDOM.nextInt(5));
                System.out.println("No." + no + " end running...");

                phaser.arriveAndAwaitAdvance();

                System.out.println("No." + no + " start bicycle...");
                TimeUnit.SECONDS.sleep(RANDOM.nextInt(5));
                System.out.println("No." + no + " end bicycle...");

                phaser.arriveAndAwaitAdvance();

                System.out.println("No." + no + " start jump...");
                TimeUnit.SECONDS.sleep(RANDOM.nextInt(5));
                System.out.println("No." + no + " end jump...");

                phaser.arriveAndAwaitAdvance();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
}
