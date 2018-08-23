/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.examples.concurrency.juc.utils.Semaphore;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Mac
 * @create 2018-06-16 13:36
 **/

public class SemaphoreLock {
    private final Semaphore semaphore = new Semaphore(1);

    //private Thread lockHolder;

    public void lock() throws InterruptedException {
        semaphore.acquire();
    }

    public void unlock() {
        semaphore.release();
    }
}
