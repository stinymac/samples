/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.sample.corejava.concurrency.thread.threadpool;

/**
 *
 * @author Mac
 * @create 2018-05-10 21:58
 **/

public class SimpleThreadPoolTest {
    public static void main(String[] args) throws InterruptedException {
        SimpleThreadPool pool = new SimpleThreadPool();
        for (int i = 0; i < 200; i++) {
            pool.submit(new Runnable() {

                public void run() {
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("=============");
                }
            });
        }
        Thread.sleep(10000);
        System.out.println(pool.getPoolThreadNum()+"-"+pool.getWaitingTaskNum());
    }
}
