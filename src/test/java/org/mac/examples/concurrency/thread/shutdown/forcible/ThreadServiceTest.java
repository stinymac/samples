/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.examples.concurrency.thread.shutdown.forcible;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author Mac
 * @create 2018-05-08 23:02
 **/

public class ThreadServiceTest {
    public static void main(String[] args) throws InterruptedException {
        ThreadService service = new ThreadService();
        service.execute(new Runnable() {
            public void run() {
                while (true) {

                }
            }
        });
        TimeUnit.SECONDS.sleep(1);
        service.shutdown(1000);
    }
}
