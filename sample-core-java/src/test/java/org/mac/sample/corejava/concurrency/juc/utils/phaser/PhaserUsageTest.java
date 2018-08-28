/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.sample.corejava.concurrency.juc.utils.phaser;

import java.util.concurrent.Phaser;

/**
 *
 * @author Mac
 * @create 2018-06-17 16:42
 **/

public class PhaserUsageTest {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(5);
        for (int i = 0; i < 5; i++) {
            new PhaserUsage(i,phaser);
        }

    }
}
