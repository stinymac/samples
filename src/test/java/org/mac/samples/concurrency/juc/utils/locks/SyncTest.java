 /*
  *       ___        |
  *      /_\ `*      |.===.         ,,,,,
  *     (o o)        {}o o{}       /(o o)\
  * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
  *
  * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
  */
package org.mac.samples.concurrency.juc.utils.locks;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author Mac
 * @create 2018-06-16 22:28
 **/

public class SyncTest {
    public static void main(String[] args) throws InterruptedException {
        new Thread(()->w()).start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(()->r()).start();
    }

    public synchronized static void w() {
        System.out.println(Thread.currentThread().getName()+" w()");
        for(;;){

        }
    }

    public synchronized static void r() {
        System.out.println(Thread.currentThread().getName()+" r()");
    }
}
