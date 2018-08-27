/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.samples.concurrency.pattern.count_down;

/**
 *
 * @author Mac
 * @create 2018-06-06 15:52
 **/

public class CountDown {

    private final int total;

    private volatile int counter = 0;

    public CountDown(int total) {
        this.total = total;
    }

    public void down () {
        synchronized (this) {
            counter++;
            if (counter == total) {
                notifyAll();
            }
        }
    }

    public void await() throws InterruptedException {
        synchronized (this) {
            while (counter != total) {
                this.wait();
            }
        }
    }
}
