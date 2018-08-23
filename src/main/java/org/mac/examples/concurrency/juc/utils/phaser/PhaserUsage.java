/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.examples.concurrency.juc.utils.phaser;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
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
