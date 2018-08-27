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
 * @create 2018-06-06 15:59
 **/

public class CountDownClient {
    public static void main(String[] args) throws InterruptedException {
        CountDown countDown = new CountDown(5);

        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("working...");
                        Thread.sleep(300);
                        countDown.down();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        countDown.await();
        System.out.println("finished.");
    }
}
