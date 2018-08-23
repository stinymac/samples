/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.examples.concurrency.juc.utils.phaser;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
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
