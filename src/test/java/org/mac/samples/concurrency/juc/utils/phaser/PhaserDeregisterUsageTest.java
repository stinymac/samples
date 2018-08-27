/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.samples.concurrency.juc.utils.phaser;

import java.util.concurrent.Phaser;

/**
 *
 * @author Mac
 * @create 2018-06-17 17:11
 **/

public class PhaserDeregisterUsageTest {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(5);
        for (int i = 0; i < 4; i++) {
            new PhaserDeregisterUsage.NormalSport(i,phaser);
        }
        new PhaserDeregisterUsage.UnNormalSport(4,phaser);
    }
}
