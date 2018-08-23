/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.examples.concurrency.thread.join;

/**
 *
 * @author Mac
 * @create 2018-05-08 20:45
 **/

public class ThreadJoinTest {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            public void run() {
                for(int i = 0 ;i < 10; i ++) {
                    System.out.println(Thread.currentThread()+" run-> " +(i+1));
                }
            }
        });
        t.start();
        t.join();
        System.out.println("---------------------------------------");
        for(int i = 0 ;i < 10; i ++) {
            System.out.println(Thread.currentThread()+" run-> " +(i+1));
        }
    }
}
