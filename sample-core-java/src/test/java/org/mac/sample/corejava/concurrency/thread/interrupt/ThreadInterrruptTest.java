/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.sample.corejava.concurrency.thread.interrupt;

/**
 *
 * @author Mac
 * @create 2018-05-08 21:11
 **/

public class ThreadInterrruptTest {
    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(100);
                        //System.out.println("running...");
                    }
                }catch(InterruptedException e) {
                    System.out.println(Thread.currentThread().getName()+" isInterrupted :"+Thread.currentThread().isInterrupted()+"->"+ e);
                }
            }
        },"t");
        t.start();
        System.out.println(t.isInterrupted());
        t.interrupt();
        System.out.println(t.isInterrupted());
    }
}
