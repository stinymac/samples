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
 * @create 2018-06-17 17:05
 **/

public class PhaserDeregisterUsage {
    private static final Random RANDOM = new Random(System.currentTimeMillis());

    static class NormalSport extends Thread {

        private final int no;
        private final Phaser phaser;


        public NormalSport(int no, Phaser phaser) {
            this.no = no;
            this.phaser = phaser;
            this.start();
        }

        @Override
        public void run() {
            try {
                doSport(phaser,"No." + no + " start running...", "No." + no + " end running...");
                doSport(phaser,"No." + no + " start bicycle...", "No." + no + " end bicycle...");
                doSport(phaser,"No." + no + " start jump...", "No." + no + " end jump...");

        } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    static class UnNormalSport extends Thread {

        private final int no;
        private final Phaser phaser;


        public UnNormalSport(int no, Phaser phaser) {
            this.no = no;
            this.phaser = phaser;
            this.start();
        }

        @Override
        public void run() {
            try {
                doSport(phaser,"No." + no + " start running...", "No." + no + " end running...");
                doSport(phaser,"No." + no + " start bicycle...", "No." + no + " end bicycle...");

                System.out.println( " i am injured and exit");
                phaser.arriveAndDeregister();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void doSport(Phaser phaser,String x, String x2) throws InterruptedException {
        System.out.println(x);
        TimeUnit.SECONDS.sleep(RANDOM.nextInt(5));
        System.out.println(x2);
        phaser.arriveAndAwaitAdvance();
    }
}
