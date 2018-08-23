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
